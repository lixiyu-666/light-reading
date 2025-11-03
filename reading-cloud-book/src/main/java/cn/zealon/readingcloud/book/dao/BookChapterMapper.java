package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.book.BookChapter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 图书章节
 * @author: zealon
 * @since: 2020/3/18
 */
public interface BookChapterMapper {

    BookChapter selectById(@Param("id") Integer id);

    List<BookChapter> findPageWithResult(@Param("bookId") Integer bookId);

    int findPageWithCount(@Param("bookId") Integer bookId);

    /**
     * 查询上一章节ID
     * @param bookId
     * @param currentSortNumber
     * @return
     */
    Integer selectPreChapterId(@Param("bookId") Integer bookId,
                               @Param("currentSortNumber") Integer currentSortNumber);

    /**
     * 查询下一章节ID
     * @param bookId
     * @param currentSortNumber
     * @return
     */
    Integer selectNextChapterId(@Param("bookId") Integer bookId,
                                @Param("currentSortNumber") Integer currentSortNumber);

}
