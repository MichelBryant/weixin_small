package com.weixin.service.impl;

import com.weixin.dao.AreaDao;
import com.weixin.entity.Area;
import com.weixin.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * @author liumingzhong
 * @date 2018-2-6
 */
@Service
public class AreaServiceImpl implements AreaService {
    Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaDao areaDao;

    @Override
    @Cacheable(value = "redisDemo",keyGenerator="wiselyKeyGenerator")
    public List<Area> findAreaList() {
        logger.info("查询全部区域信息------");
        return areaDao.findAreaList();
    }

    @Override
    public Area findAreaById(int areaId) {
        return areaDao.findAreaById(areaId);
    }

    @Override
    public boolean insertArea(Area area) {
        boolean flag =false;
        if(area.getAreaName()!=null && !"".equals(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setModifyTime(new Date());
            try{
                flag = areaDao.insertArea(area);
                if(flag){
                    return true;
                }else{
                    throw new RuntimeException("插入区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    public boolean updateArea(Area area) {
        boolean flag =false;
        if(area.getAreaId()!=null && !"".equals(area.getAreaId())){
            area.setCreateTime(new Date());
            area.setModifyTime(new Date());
            try{
                flag = areaDao.updateArea(area);
                if(flag){
                    return true;
                }else{
                    throw new RuntimeException("更新区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("更新区域信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    public boolean deleteArea(int areaId) {
        boolean flag =false;
        if(areaId>0){
            try{
                flag = areaDao.deleteArea(areaId);
                if(flag){
                    return true;
                }else{
                    throw new RuntimeException("删除区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("删除区域信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("区域ID不能为空！");
        }
    }
}
