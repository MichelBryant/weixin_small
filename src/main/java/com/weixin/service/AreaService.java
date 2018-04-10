package com.weixin.service;

import com.weixin.entity.Area;

import java.util.List;

/**
 * Created by liumingzhong on 2018-2-6.
 */
public interface AreaService {
    /**
     * 获取所有区域列表
     * @return
     */
    List<Area> findAreaList();

    /**
     * 根据区域ID获取某一区域
     * @param areaId
     * @return
     */
    Area findAreaById(int areaId);

    /**
     * 添加区域
     * @param area
     * @return
     */
    boolean insertArea(Area area);

    /**
     * 修改区域
     * @param area
     * @return
     */
    boolean updateArea(Area area);

    /**
     * 删除区域
     * @param areaId
     * @return
     */
    boolean deleteArea(int areaId);
}
