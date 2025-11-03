package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.book.dto.BookQuery;
import cn.zealon.readingcloud.common.pojo.book.Book;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 图书
 * @author: zealon
 * @since: 2020/3/16
 */
public interface BookMapper extends BaseMapper<Book> {

    IPage<Book> findPageWithResult(Page<Book> page, @Param("query") BookQuery query);
    Book selectBookById(@Param("bookId") String bookId);
}
