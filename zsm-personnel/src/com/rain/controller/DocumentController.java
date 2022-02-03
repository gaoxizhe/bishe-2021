package com.rain.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.rain.dao.EmployeeDao;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.rain.domain.Document;
import com.rain.service.RainService;

@Controller
public class DocumentController {
    String baseUrl = "c://file";
    @Autowired
    @Qualifier("RainService")
    private RainService rainservice;

    @Resource
    private EmployeeDao employeeDao;

    // 如果在目录下输入为空，则跳转到指定链接
    @RequestMapping(value = "/document/")
    public ModelAndView index2(ModelAndView mv) {
        mv.setViewName("document/list");
        return mv;
    }

    @RequestMapping(value = "/download")
    public void downloadFile(@RequestParam("name") String name, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = baseUrl + "//" + name;
        File f = new File(fileName);

       // 3. 设置想办法让浏览器能够支持下载我们需要的东西
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(name,"UTF-8"));
//        4. 获取下载文件的输入流
        FileInputStream fi = new FileInputStream(f);
//        5. 创建缓冲区
        int len =0;
        byte[] buffer =new byte[1024];
//        6. 获取OutputStream对象
        ServletOutputStream out =resp.getOutputStream();
//        7. 将FileOutputStream流写入到buffer缓冲区
        while ((len=fi.read(buffer))>0){
            out.write(buffer,0,len);
        }
        fi.close();
        out.close();


//        HashMap<String, String> res = new HashMap<>();
//        res.put("code", "0");
//        res.put("msg", "success");
//        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "/employee/uploadFile/")
    @ResponseBody
    public String uploadFile(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
        System.out.println(file.getSize());
        String s = UUID.randomUUID().toString() + file.getOriginalFilename();
        String fileName = baseUrl + "//" + s;
        File f = new File(fileName);
        f.mkdirs();
        try {
            file.transferTo(f);
            employeeDao.updateFile(s, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> res = new HashMap<>();
        res.put("code", "0");
        res.put("msg", "success");
        return JSON.toJSONString(res);
    }

    // 如果在目录下输入任何不存在的参数，则跳转到list
    @RequestMapping(value = "/document/{formName}")
    public String index2(@PathVariable String formName) {

        String blank = "/document/list";
        return blank;
    }

    @RequestMapping(value = "/document/list", method = RequestMethod.GET)
    public String index(Model model, String content) {
        List<Document> job_list = rainservice.get_DocumentList();
        if (content != null) {
            job_list = rainservice.get_DocumentLikeList(content);
        }
        model.addAttribute("list", job_list);
        return "document/list";
    }

    @RequestMapping(value = "/document/add", method = RequestMethod.GET)
    public String add(Model model, Integer id) {
        if (id != null) {
            Document job = rainservice.get_DocumentInfo(id);
            model.addAttribute("job", job);
        }
        return "/document/add";
    }

    @RequestMapping(value = "/document/add", method = RequestMethod.POST)
    public ModelAndView add(ModelAndView mv, @ModelAttribute Document document, Integer id, HttpSession session
    )
            throws Exception {
        System.out.println(id);
        if (id != null) {
            rainservice.update_DocumentInfo(document);
        } else {
            /**
             * 上传文件
             */
            String path = session.getServletContext().getRealPath("/upload/");
            String filename = document.getFile().getOriginalFilename();
            path = "C://file";
            File tempFile = new File(path + File.separator + filename);
            tempFile.createNewFile();
            document.getFile().transferTo(tempFile);
            document.setFilename(filename);
            rainservice.insert_DocumentInfo(document);
        }
        mv.setViewName("redirect:/document/list");
        return mv;
    }

    @RequestMapping(value = "/document/delete", method = RequestMethod.GET)
    public void delete(Integer id) {
        System.out.println(id);
        if (id != null) {
            rainservice.delete_DocumentInfo(id);
        }
    }
}
