package com.weixin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import freemarker.template.TemplateException;
import org.springframework.core.io.ClassPathResource;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;


public class HtmlToPdfUtil {
	public static File toPdf(HttpServletRequest request,String uri,String id) throws IOException, TemplateException, DocumentException {
		String basePath = request.getContextPath();
		if(basePath==null||"".equals(basePath)){
	       try {
	    	    URL url = HtmlToPdfUtil.class.getClassLoader().getResource("/");
	    	    File a = new File(url.toURI()).getParentFile().getParentFile();
	    	    basePath=a.getAbsolutePath();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		File pdf = new File(basePath+File.separator + "temp"+File.separator+id+".pdf");
		if(!pdf.getParentFile().exists()){
            boolean b = pdf.getParentFile().mkdirs();
            if (!b) {
                throw new RuntimeException("创建文件夹失败");
            }
        }
        if(!pdf.exists()){
            boolean b = pdf.createNewFile();
            if (!b) {
                throw new RuntimeException("创建文件失败");
            }
        }
        OutputStream os = null;
        try{
        	os = new FileOutputStream(pdf);
			ITextRenderer renderer = new ITextRenderer();
			try {
				renderer.setDocument(uri);
			} catch (Exception e) {
				if(os!=null){
					os.flush();
					os.close();
				}
				System.out.println("！！！！！！！！！！转换出错的链接：    "+uri);
				e.printStackTrace();
			} 
			// 解决中文问题
			ITextFontResolver fontResolver = renderer.getFontResolver();
	        String fonts=basePath+"/common/fonts/simsun.ttc";
				fontResolver.addFont(fonts,BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.layout();
			renderer.createPDF(os);
		}catch(IOException e){
	    	e.printStackTrace();
	    }finally{
	    	if(os!=null){
	    		os.close();
	    	}
	    }
		return pdf;
	}
	public static File toSignPdf(HttpServletRequest request,String uri,String id) throws IOException, TemplateException, DocumentException {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		if(basePath==null||"".equals(basePath)){
	       try {
	    	    URL url = HtmlToPdfUtil.class.getClassLoader().getResource("/");
	    	    File a = new File(url.toURI()).getParentFile().getParentFile();
	    	    basePath=a.getAbsolutePath();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		File pdf = new File(basePath+File.separator + "temp"+File.separator+id+"sign.pdf");
		if(!pdf.getParentFile().exists()){
            boolean b = pdf.getParentFile().mkdirs();
            if (!b) {
                throw new RuntimeException("创建文件夹失败");
            }
        }
        if(!pdf.exists()){
            boolean b = pdf.createNewFile();
            if (!b) {
                throw new RuntimeException("创建文件失败");
            }
        }
        OutputStream os = null;
        try{
	        os = new FileOutputStream(pdf);
			ITextRenderer renderer = new ITextRenderer();
			try {
				renderer.setDocument(uri);
			} catch (Exception e) {
				if(os!=null){
					os.flush();
					os.close();
				}
				System.out.println("！！！！！！！！！！转换出错的链接：    "+uri);
				e.printStackTrace();
			} 
			// 解决中文问题
			ITextFontResolver fontResolver = renderer.getFontResolver();
	        String fonts=basePath+"/common/fonts/simsun.ttc";
			fontResolver.addFont(fonts,BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.layout();
			renderer.createPDF(os);
        }catch(IOException e){
        	e.printStackTrace();
        }finally{
        	if(os!=null){
				os.close();
			}
        }
		return pdf;
	}
}
