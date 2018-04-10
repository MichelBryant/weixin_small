package com.weixin.controller;

import com.weixin.entity.Area;
import com.weixin.service.AreaService;
import com.weixin.util.HtmlToPdfUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author liumingzhong
 * @date 2018-2-6
 */
@RestController
@RequestMapping("admin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listArea",method = RequestMethod.GET)
    public Map<String,Object> findAreaList(){
        Map<String,Object> map = new HashMap<String,Object>();
       List<Area> areaList =  areaService.findAreaList();
        map.put("areaList",areaList);
      return map;
    }

    /**
     * 查询功能demo
     * @param areaId
     * @return
     */
    @RequestMapping(value = "/getAreaById",method = RequestMethod.GET)
    public Map<String,Object> getAreaById(Integer areaId){
        Map<String,Object> map = new HashMap<String,Object>();
        Area area = areaService.findAreaById(areaId);
        map.put("area",area);
        return map;
    }

    /**
     * 插入功能demo
     * @param area
     * @return
     */
    @RequestMapping(value = "/insertArea",method = RequestMethod.POST)
    public Map<String,Object>  insertArea(@RequestBody Area area){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = areaService.insertArea(area);
        map.put("success",flag);
        return map;
    }

    /**
     * 更新功能demo
     * @param area
     * @return
     */
    @RequestMapping(value = "/updateArea",method = RequestMethod.POST)
    public Map<String,Object>  updateArea(@RequestBody Area area){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = areaService.updateArea(area);
        map.put("success",flag);
        return map;
    }

    /**
     * 删除功能demo
     * @param areaId
     * @return
     */
    @RequestMapping(value = "/deleteArea",method = RequestMethod.GET)
    public Map<String,Object>  deleteArea(Integer areaId){
        Map<String,Object> map = new HashMap<String,Object>(1);
        boolean flag = areaService.deleteArea(areaId);
        map.put("success",flag);
        return map;
    }

    /**
     * word文档下载
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadFile")
    public void downLoadDocument(HttpServletRequest request, HttpServletResponse response){
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream("F:\\ANHUICNS.doc"));
            byte[] buf = new byte[1024];
            int count = 0;
            DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = sdf.format(new Date()) + ".doc";
            response.setContentType("application/x-download;");
            response.setHeader("Content-disposition", "attachment; filename=" + dateStr);
           // response.setCharacterEncoding("UTF-8");
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            while ((count=bufferedInputStream.read(buf))>0){
                bufferedOutputStream.write(buf,0,count);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }

    /**
     * 下载并动态编辑word文档demo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadAndEditFile")
    public String downLoadAndEditDocument(HttpServletRequest request, HttpServletResponse response){
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
             bufferedInputStream = new BufferedInputStream(new FileInputStream("F:\\ANHUICNS.doc"));

            HWPFDocument hdt = new HWPFDocument(bufferedInputStream);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            Range range = hdt.getRange();
            range.replaceText("entName", "数据库接电话飞机上的");
            range.replaceText("orgName", "XXX工商管理局");
            range.replaceText("address", "交换机圣诞节发布时间打发斯蒂芬");
            range.replaceText("owner", "刘德华");
            range.replaceText("cardNumber", "273623652356235");
            range.replaceText("phoneNumb", "1234567890");
            range.replaceText("useModel", "工业");
            range.replaceText("provideUse", "无偿使用");

            range.replaceText("${year}", year + "");
            range.replaceText("${month}", month + "");
            range.replaceText("${day}", day + "");
            hdt.write(out);

            long fileLength = out.size();
            DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = sdf.format(new Date()) + ".doc";
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + dateStr);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    @RequestMapping(value = "toPdf")
    public String toPdf(HttpServletRequest request, HttpServletResponse response){
        PrintWriter printWriter = null;
        try {
           // File pdf = HtmlToPdfUtil.toPdf(request, "http://127.0.0.1:8081/wx_boot/index.action", "12345");
           //  printWriter = new PrintWriter(new FileWriter(pdf));
            printWriter = response.getWriter();
            printWriter.print("Hello World !");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            printWriter.flush();
            printWriter.close();
        }
       return null;
    }
}
