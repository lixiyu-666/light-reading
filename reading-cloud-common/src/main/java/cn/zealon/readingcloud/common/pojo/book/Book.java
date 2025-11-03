package cn.zealon.readingcloud.common.pojo.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书
 */
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 作者
     */
    @TableField("author_id")
    private Integer authorId;

    /**
     * 分类
     */
    @TableField("dic_category")
    private Integer dicCategory;

    /**
     * 频道id:0全部,1男生,2女生,3出版物
     */
    @TableField("dic_channel")
    private Integer dicChannel;

    /**
     * 连载状态
     */
    @TableField("dic_serial_status")
    private Integer dicSerialStatus;

    /**
     * 状态：0下架，1上架
     */
    @TableField("online_status")
    private Boolean onlineStatus;

    /**
     * 图书id
     */
    @TableField("book_id")
    private String bookId;

    /**
     * 图书名称
     */
    @TableField("book_name")
    private String bookName;

    /**
     * 图书评分
     */
    @TableField("book_score")
    private Integer bookScore;

    /**
     * 关键词
     */
    @TableField("key_word")
    private String keyWord;

    /**
     * 封面
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 作者名称
     */
    @TableField("author_name")
    private String authorName;

    /**
     * 简介
     */
    private String introduction;

    /**
     * isbn
     */
    private String isbn;

    /**
     * 字数
     */
    @TableField("word_count")
    private Integer wordCount;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新者
     */
    private String updater;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getDicCategory() {
        return dicCategory;
    }

    public void setDicCategory(Integer dicCategory) {
        this.dicCategory = dicCategory;
    }

    public Integer getDicChannel() {
        return dicChannel;
    }

    public void setDicChannel(Integer dicChannel) {
        this.dicChannel = dicChannel;
    }

    public Integer getDicSerialStatus() {
        return dicSerialStatus;
    }

    public void setDicSerialStatus(Integer dicSerialStatus) {
        this.dicSerialStatus = dicSerialStatus;
    }

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookScore() {
        return bookScore;
    }

    public void setBookScore(Integer bookScore) {
        this.bookScore = bookScore;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}