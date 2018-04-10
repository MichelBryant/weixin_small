package com.weixin.dao;

import com.weixin.entity.Area;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author liumingzhong
 * @date 2018/2/6
 */
public interface AreaDao {

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
     * 多个异常类
     */
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    boolean insertArea(Area area);

    /**
     * 修改区域
     * @param area
     * @return
     * 多个异常类
     */
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    boolean updateArea(Area area);

    /**
     * 删除区域
     * @param areaId
     * @return
     * 单一异常类
     */
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    boolean deleteArea(int areaId);
}
