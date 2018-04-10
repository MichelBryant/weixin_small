package com.weixin.entity;

import java.util.Date;

/**
 *
 * @author liumingzhong
 * @date 2018/2/6
 */
public class Area {

    private Integer areaId;//区域id
    private String areaName;//区域名
    private Integer priority;//优先级（优先级越高，越前排）
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
