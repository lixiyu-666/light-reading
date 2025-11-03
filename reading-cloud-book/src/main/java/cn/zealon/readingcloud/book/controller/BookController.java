package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.dto.BookQuery;
import cn.zealon.readingcloud.book.service.BookService;
import cn.zealon.readingcloud.book.vo.BookVO;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 图书接口
 * @author: zealon
 * @since: 2019/4/3
 */
@Api(description = "图书查询接口")
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询图书基本信息" , httpMethod = "GET")
    @GetMapping("/{bookId}")
    public Result<Book> getBookById(@PathVariable String bookId){

        return bookService.getBookById(bookId);
    }

    @ApiOperation(value = "获取图书详情" , httpMethod = "GET")
    @GetMapping("/details")
    public Result<BookVO> getBookDetails(@RequestParam String bookId){

        return bookService.getBookDetails(bookId);
    }

    /**
     * 分页查询图书列表
     */
    @ApiOperation(value = "分页查询图书列表", httpMethod = "GET")
    @GetMapping("/page")
    public Result<IPage<Book>> getBookPage(BookQuery query) {
        return bookService.getBookPage(query);
    }
}