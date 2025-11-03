package cn.zealon.readingcloud.book.domain;

import java.io.Serializable;

/**
 * 图书前后章节节点
 * <p>
 *     图书章节数据结构，为了读取当前章时，能够方便的进入上一章、下一章，
 *     这里每个章节节点数据划分分为当前章、上一章、下一章，形成一个基础的双向链表
 * </p>
 * @author: zealon
 * @since: 2020/5/6
 */
public class BookPreviousAndNextChapterNode implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 章节ID */
    private Integer id;

    /** 章节名称 */
    private String name;

    /** 上一章 */
    private BookPreviousAndNextChapterNode pre;

    /** 下一章 */
    private BookPreviousAndNextChapterNode next;

    public BookPreviousAndNextChapterNode() {}

    public BookPreviousAndNextChapterNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookPreviousAndNextChapterNode(BookPreviousAndNextChapterNode chapterNode) {
        if (chapterNode != null) {
            this.id = chapterNode.getId();
            this.name = chapterNode.getName();
        }
    }

    // Getter 和 Setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookPreviousAndNextChapterNode getPre() {
        return pre;
    }

    public void setPre(BookPreviousAndNextChapterNode pre) {
        this.pre = pre;
    }

    public BookPreviousAndNextChapterNode getNext() {
        return next;
    }

    public void setNext(BookPreviousAndNextChapterNode next) {
        this.next = next;
    }
}