package cn.zealon.readingcloud.book.dto;

import lombok.Data;

/**
 * 图书查询请求dto
 */
@Data
public class BookQuery {
    private Integer dicCategory;
    private Integer dicChannel;
    private Integer dicSerialStatus;
    private Integer onlineStatus;
    private Integer authorId;
    private String bookId;
    private String bookName;

    // 分页参数
    private Integer pageNumber = 1;
    private Integer pageSize = 10;
}
