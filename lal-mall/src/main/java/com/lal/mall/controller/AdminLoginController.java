package com.lal.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.lal.mall.entity.Admin;
import com.lal.mall.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 后台管理-登录页
 */
@Slf4j
@Controller
@RequestMapping("/admin/login")
public class AdminLoginController {
    @Autowired
    private AdminService adminService;

    //转到后台管理-登录页
    @GetMapping()
    public String goToPage() {
        return "admin/loginPage";
    }

    //登陆验证-ajax
    @PostMapping("/doLogin")
    @ResponseBody
    public JSONObject checkLogin(HttpSession session,
                             @RequestParam String username,
                             @RequestParam String password) {
        JSONObject object = new JSONObject();
        object.put("success", false);
        // 判断输入的用户名
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            log.warn("参数有误");
        }

        Admin admin = adminService.login(username, password);
        if (admin != null) {
            session.setAttribute("ADMIN_SESSION", admin);
            session.setAttribute("ADMIN_ID", admin.getAdmin_id());
            object.put("success", true);
        }
        return object;
    }

    //退出管理员账号
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("ADMIN_SESSION");
        if (admin != null) {
            session.removeAttribute("ADMIN_SESSION");
            session.removeAttribute("ADMIN_ID");
            session.invalidate();
        }
        return "redirect:/admin/login";
    }

    //获取管理员头像路径-ajax
    @ResponseBody
    @GetMapping("/profile_picture")
    public JSONObject getAdminProfilePicture(@RequestParam String username) {
        log.info("根据用户名获取管理员头像路径");
        Admin admin = adminService.get(username, null);

        JSONObject object = new JSONObject();
        if (admin == null) {
            object.put("success", false);
        } else {
            object.put("success", true);
            object.put("srcString", admin.getAdmin_profile_picture_src());
        }
        return object;
    }
}
