package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.dao.BookChapterMapper;
import cn.zealon.readingcloud.book.domain.BookPreviousAndNextChapterNode;
import cn.zealon.readingcloud.book.service.BookChapterService;
import cn.zealon.readingcloud.book.service.BookService;
import cn.zealon.readingcloud.book.vo.BookChapterListVO;
import cn.zealon.readingcloud.book.vo.BookChapterReadVO;
import cn.zealon.readingcloud.book.vo.BookChapterVO;
import cn.zealon.readingcloud.common.cache.RedisBookKey;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.pojo.book.BookChapter;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 图书章节服务
 * @author: zealon
 * @since: 2019/9/25
 */
@Service
public class BookChapterServiceImpl implements BookChapterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookChapterServiceImpl.class);

    @Autowired
    private BookChapterMapper bookChapterMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisService redisService;

    @Override
    public Result getBookChapterListByBookId(String bookId) {
        Book book = (Book) bookService.getBookById(bookId).getData();
        if (null == book) {
            return ResultUtil.notFound().buildMessage("该书不存在于本系统哦！");
        }

        String key = RedisBookKey.getBookChapterListKey(bookId);
        List<BookChapterListVO> chapterVOs = this.redisService.getCacheForList(key, BookChapter.class);
        if (null == chapterVOs || chapterVOs.isEmpty()) {
            List<BookChapter> chapters = this.bookChapterMapper.findPageWithResult(book.getId());
            if (!chapters.isEmpty()) {
                chapterVOs = new ArrayList<>();
                for (int i = 0; i < chapters.size(); i++) {
                    BookChapterListVO vo = new BookChapterListVO();
                    BeanUtils.copyProperties(chapters.get(i), vo);
                    chapterVOs.add(vo);
                }
                this.redisService.setExpireCache(key, chapterVOs, RedisExpire.HOUR);
            }
        }
        return ResultUtil.success(chapterVOs);
    }

    @Override
    public Result<BookChapter> getChapterById(String bookId, Integer chapterId) {
        BookChapter chapter;
        String key = RedisBookKey.getBookChapterKey(bookId);
        String field = chapterId.toString();
        chapter = this.redisService.getHashVal(key, field, BookChapter.class);
        if (chapter == null) {
            chapter = this.bookChapterMapper.selectById(chapterId);
            if (chapter != null) {
                this.redisService.setHashValExpire(key, field, chapter, RedisExpire.HOUR);
            } else {
                return ResultUtil.notFound().buildMessage("章节不存在！");
            }
        }
        return ResultUtil.success(chapter);
    }

    @Override
    public Result<BookChapterReadVO> readChapter(String bookId, Integer chapterId) {
        Book book = (Book) bookService.getBookById(bookId).getData();
        if (null == book) {
            return ResultUtil.notFound().buildMessage("该书不存在于本系统哦！");
        }

        BookChapterReadVO result = new BookChapterReadVO();
        String field = chapterId.toString();
        if (chapterId == 0) {
            field = "first";
        } else if (chapterId == -1) {
            field = "last";
        }

        BookPreviousAndNextChapterNode chapterNode = this.getChapterNodeData(book.getId(), field);
        if (chapterNode == null) {
            // 获取不到节点数据查询首章节
            field = "first";
            chapterNode = this.getChapterNodeData(book.getId(), field);
            if (chapterNode == null) {
                return ResultUtil.notFound().buildMessage("本书还没有任何章节内容哦！");
            }
        }

        // 获取当前章信息、内容
        String content = this.getChapterContent(bookId, chapterNode.getId());
        if (content == null) {
            return ResultUtil.notFound().buildMessage("章节内容不存在！");
        }

        BookChapterVO current = new BookChapterVO(chapterNode.getId(), chapterNode.getName(), content);

        // 上一章、下一章
        BookChapterVO pre = null;
        BookChapterVO next = null;
        if (chapterNode.getPre() != null) {
            pre = new BookChapterVO(chapterNode.getPre().getId(), chapterNode.getPre().getName(), "");
        }
        if (chapterNode.getNext() != null) {
            next = new BookChapterVO(chapterNode.getNext().getId(), chapterNode.getNext().getName(), "");
        }

        result.setCurrent(current);
        result.setPre(pre);
        result.setNext(next);
        return ResultUtil.success(result);
    }

    /**
     * 获取前后章节节点数据链表
     * @param bookId 图书ID
     * @param field 字段标识
     * @return 章节节点数据
     */
    private BookPreviousAndNextChapterNode getChapterNodeData(final Integer bookId, final String field) {
        // 缓存获取
        String key = RedisBookKey.getBookChapterNodeKey(bookId);
        BookPreviousAndNextChapterNode chapterNode = this.redisService.getHashObject(key, field, BookPreviousAndNextChapterNode.class);
        if (chapterNode != null) {
            return chapterNode;
        }

        // 章节列表
        List<BookChapter> chapterList = this.bookChapterMapper.findPageWithResult(bookId);
        if (chapterList.isEmpty()) {
            return null;
        }

        HashMap<String, BookPreviousAndNextChapterNode> map = new HashMap<>();
        BookPreviousAndNextChapterNode pre = null;

        try {
            List<BookChapter> availableChapters = new ArrayList<>();

            // 过滤掉锁定的章节
            for (BookChapter chapter : chapterList) {
                if (!chapter.getLockStatus()) {
                    availableChapters.add(chapter);
                }
            }

            if (availableChapters.isEmpty()) {
                return null;
            }

            // 构建链表
            for (int i = 0; i < availableChapters.size(); i++) {
                BookChapter chapter = availableChapters.get(i);
                BookPreviousAndNextChapterNode curr = new BookPreviousAndNextChapterNode(chapter.getId(), chapter.getName());

                if (pre != null) {
                    curr.setPre(new BookPreviousAndNextChapterNode(pre));
                    pre.setNext(new BookPreviousAndNextChapterNode(curr));
                    map.put(pre.getId().toString(), pre);
                }

                // 首章节
                if (i == 0) {
                    map.put("first", curr);
                }

                // 存储节点数据
                map.put(curr.getId().toString(), curr);
                pre = curr;

                // 最后一章节
                if (i == availableChapters.size() - 1) {
                    map.put("last", curr);
                }
            }

            this.redisService.setHashValsExpire(key, map, RedisExpire.HOUR_FOUR);
        } catch (Exception e) {
            LOGGER.error("生成章节节点数据异常：{}", e.getMessage(), e);
        }

        return map.get(field);
    }

    /**
     * 获取章节内容
     * @param bookId 图书ID
     * @param chapterId 章节ID
     * @return 章节内容
     */
    private String getChapterContent(String bookId, Integer chapterId) {
        Result<BookChapter> chapterResult = this.getChapterById(bookId, chapterId);
        // 检查Result的code来判断是否成功，通常200表示成功
        if (chapterResult.getCode() == 200 && chapterResult.getData() != null) {
            return chapterResult.getData().getContent();
        }
        return null;
    }
}