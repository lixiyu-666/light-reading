package cn.zealon.readingcloud.common.pojo.index;

import java.io.Serializable;
import java.util.Date;

/**
 * 主页书单配置
 * @author: zealon
 * @since: 2020/4/6
 */
public class IndexBooklist implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 显示数量
     */
    private Integer showNumber;

    /**
     * 书单样式：1.横版(2*4),2.竖版(1*3),3.1+4,4.竖版more
     */
    private Integer style;

    /**
     * 更多类型：1.无,2.更多,3.换一换
     */
    private Integer moreType;

    /**
     * 更多链接
     */
    private String moreUrl;

    /**
     * 图书ids
     */
    private String bookIds;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者
     */
    private String updater;

    /** 是否显示喜欢数 */
    private Boolean showLikeCount;

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

    public Integer getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Integer showNumber) {
        this.showNumber = showNumber;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getMoreType() {
        return moreType;
    }

    public void setMoreType(Integer moreType) {
        this.moreType = moreType;
    }

    public String getMoreUrl() {
        return moreUrl;
    }

    public void setMoreUrl(String moreUrl) {
        this.moreUrl = moreUrl;
    }

    public String getBookIds() {
        return bookIds;
    }

    public void setBookIds(String bookIds) {
        this.bookIds = bookIds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Boolean getShowLikeCount() {
        return showLikeCount;
    }

    public void setShowLikeCount(Boolean showLikeCount) {
        this.showLikeCount = showLikeCount;
    }
}