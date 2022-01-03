package com.web.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns="/uploadPic")
public class UploadPicController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        
        // 构造路径来存储上传的文件，这个路径相对当前应用的目录
        String uploadPath = req.getServletContext().getRealPath("/") + "client\\img\\drink";
        
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        // 存入数据库中的路径
        String PicAddres = "";
        
        try {
        	// 解析请求的内容提取文件数据
        	@SuppressWarnings("unchecked")
        	List<FileItem> formItems = upload.parseRequest(req);
        	if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 使用item.isFormField()方法判断FileItem类对象封装的数据是否为普通文本表单字段，还是文件表单字段
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        // 新的图片名称
                        String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                        PicAddres = "drink/" + newFileName;
                       
                        String filePath = uploadPath + File.separator + newFileName;
                        
                        File storeFile = new File(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	String json = "{\"state\": \"fail\"}";
        	resp.getWriter().write(json);
        	return;
        }
        String json = "{\"state\": \"success\", \"PicAddres\": \"" + PicAddres + "\"}";
    	resp.getWriter().write(json);
	}
}
