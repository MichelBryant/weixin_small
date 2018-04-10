package com.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author liumingzhong
 * @date 2018-3-5
 */
@Controller
public class IndexController {

    @RequestMapping("index.action")
    public String index(){

        return "index";
    }
    @RequestMapping("showImg.action")
    public String showImg(HttpServletResponse response){
        FileInputStream fis = null;
        OutputStream fos = null;
        byte[] buf= new byte[1024];
        try{

            fis = new FileInputStream("E:\\banner_1.jpg");
            fos = response.getOutputStream();
            int count = 0;
            while((count = fis.read(buf))!=-1){
                fos.write(buf,0,count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "ok";
    }
}
