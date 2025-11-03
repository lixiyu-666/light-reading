package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.book.dto.BookQuery;
import cn.zealon.readingcloud.book.vo.BookVO;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 图书服务
 * @author: zealon
 * @since: 2019/7/4
 */
public interface BookService {

    /**
     * 查询图书基本信息
     * @param bookId
     * @return
     */
    Result getBookById(String bookId);

    /**
     * 获取图书详情
     * @param bookId
     * @return
     */
    Result<BookVO> getBookDetails(String bookId);

    /**
     * 分页查询图书列表
     * @param query
     * @return
     */
    Result<IPage<Book>> getBookPage(BookQuery query);
}
