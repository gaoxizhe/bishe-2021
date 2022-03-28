package com.ms.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.common.Result;
import com.ms.entity.User;
import com.ms.service.UserService;
import com.ms.utils.CacheUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/info")
    public Result<User> info(String token){
        User user = userService.getById(token);
        if (user != null){
            user.setId("");
            user.setPassword("");
        }
        return Result.success(user);
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestParam String username,@RequestParam String password,@RequestParam String passwordOld){
        userService.changePassword(username,password,passwordOld);
        return Result.success();
    }

    @PostMapping("/changePasswordByAdmin")
    public Result changePasswordByAdmin(@RequestParam String username,@RequestParam String password){
        userService.changePassword(username,password);
        return Result.success();
    }


    @PostMapping
    public Result<?> save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    @PostMapping("/deleteUser")
    public Result<?> delete(@RequestParam String username) {
        userService.deleteByUserName(username);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping
    public Result<List<User>> findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/page")
    public Result<IPage<User>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(userService.page(new Page<>(pageNum, pageSize), Wrappers.<User>lambdaQuery().like(User::getUsername, name).orderByDesc(User::getId)));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<User> all = userService.list();
        for (User user : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("名称", user.getUsername());
            row1.put("手机", user.getPhone());
            row1.put("邮箱", user.getEmail());
            list.add(row1);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

    @Resource
    private CacheUtil cacheUtil;

    @GetMapping("/reloadUserCache")
    public Result reloadUserCache(){
        //TODO
        return Result.success();
    }

}
