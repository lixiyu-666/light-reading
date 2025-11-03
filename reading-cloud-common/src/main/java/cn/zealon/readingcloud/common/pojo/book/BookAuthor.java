package cn.zealon.readingcloud.common.pojo.book;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书作者
 * @author: tangyl
 * @since: 2020/3/14
 */
public class BookAuthor implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    protected Integer id;

    /**
     * 作者名称
     */
    private String name;

    /**
     * 作者简介
     */
    private String introduction;

    /**
     * 头像附件id
     */
    private String headImgUrl;

    /** 创建人 */
    protected String creater;
    /** 创建时间 */
    protected Date createTime;
    /** 修改人 */
    protected String updater;
    /** 修改时间 */
    protected Date updateTime;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}