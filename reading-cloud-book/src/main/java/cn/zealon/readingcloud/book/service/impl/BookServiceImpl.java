package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.dao.BookMapper;
import cn.zealon.readingcloud.book.dto.BookQuery;
import cn.zealon.readingcloud.book.service.BookService;
import cn.zealon.readingcloud.book.vo.BookVO;
import cn.zealon.readingcloud.common.cache.RedisBookKey;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.constant.CategoryConstant;
import cn.zealon.readingcloud.common.enums.BookSerialStatusEnum;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 图书服务
 * @author: tangyl
 * @since: 2019/7/4
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询图书基本信息
     * @param bookId
     * @return
     */
    @Override
    public Result<Book> getBookById(String bookId) {
        String key = RedisBookKey.getBookKey(bookId);
        Book book = this.redisService.getCache(key, Book.class);
        //如果缓存命中，直接返回
        if (book != null) {
            return ResultUtil.success(book);
        }
        //如果未命中，查询数据库
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getBookId, bookId);
        book = this.bookMapper.selectOne(wrapper);
        //写入缓存
        if (null != book) {
            this.redisService.setExpireCache(key, book,RedisExpire.HOUR);
        }
        return ResultUtil.success(book);
    }

    /**
     * 获取图书详情
     * @param bookId
     * @return
     */
    @Override
    public Result<BookVO> getBookDetails(String bookId) {
        Book book = this.getBookById(bookId).getData();
        if (book == null) {
            return ResultUtil.notFound().buildMessage("找不到"+bookId+"这本书哦！");
        }

        BookVO vo = new BookVO();
        BeanUtils.copyProperties(book, vo);
        // 分类
        String categoryName = CategoryConstant.categorys.get(book.getDicCategory());
        vo.setCategoryName(categoryName);
        // 连载状态
        String serialStatusName = BookSerialStatusEnum.values()[book.getDicSerialStatus() - 1].getName();
        vo.setSerialStatusName(serialStatusName);
        return ResultUtil.success(vo);
    }


    /**
     * 分页查询图书列表
     *
     * @param query
     * @return
     */
    @Override
    public Result<IPage<Book>> getBookPage(BookQuery query) {
        // 创建分页对象
        Page<Book> page = new Page<>(query.getPageNumber(), query.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();

        if (query.getDicCategory() != null) {
            wrapper.eq(Book::getDicCategory, query.getDicCategory());
        }
        if (query.getDicChannel() != null) {
            wrapper.eq(Book::getDicChannel, query.getDicChannel());
        }
        if (query.getDicSerialStatus() != null) {
            wrapper.eq(Book::getDicSerialStatus, query.getDicSerialStatus());
        }
        if (query.getOnlineStatus() != null) {
            wrapper.eq(Book::getOnlineStatus, query.getOnlineStatus());
        }
        if (query.getAuthorId() != null) {
            wrapper.eq(Book::getAuthorId, query.getAuthorId());
        }
        if (StringUtils.hasText(query.getBookId())) {
            wrapper.eq(Book::getBookId, query.getBookId());
        }
        if (StringUtils.hasText(query.getBookName())) {
            wrapper.like(Book::getBookName, query.getBookName());
        }

        // 排序
        wrapper.orderByDesc(Book::getCreateTime);

        // 执行分页查询
        IPage<Book> result = bookMapper.selectPage(page, wrapper);
        return ResultUtil.success(result);
    }
}
