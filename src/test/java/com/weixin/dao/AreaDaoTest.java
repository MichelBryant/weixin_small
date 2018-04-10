package com.weixin.dao;

import com.weixin.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by liumingzhong on 2018/2/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    @Ignore
    public void findAreaList() throws Exception {
        List<Area> areaList = areaDao.findAreaList();
        assertEquals(2,areaList.size());
    }

    @Test
    @Ignore
    public void findAreaById() throws Exception {
        Area area = areaDao.findAreaById(1);
        assertEquals("东苑",area.getAreaName());
    }

    @Test
    @Ignore
    public void insertArea() throws Exception {
        Area area = new Area();
        area.setAreaName("北苑");
        area.setPriority(1);
        Boolean flag  = areaDao.insertArea(area);
        assertEquals(true,flag);
    }

    @Test
    @Ignore
    public void updateArea() throws Exception {
        Area area =areaDao.findAreaById(2);
        area.setPriority(2);
        boolean flag= areaDao.updateArea(area);
        assertEquals(true,flag);
    }

    @Test
    @Ignore
    public void deleteArea() throws Exception {
        boolean flag = areaDao.deleteArea(2);
        assertEquals(true,flag);
    }

}