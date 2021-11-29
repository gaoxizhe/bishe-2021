package com.lal.mall.service;

import com.lal.mall.entity.User;
import com.lal.mall.util.OrderUtil;
import com.lal.mall.util.PageUtil;

import java.util.List;

public interface UserService {
    boolean add(User user);

    boolean update(User user);

    boolean delete(Integer[] user_id_list);

    List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil);

    User get(Integer user_id);

    User login(String user_name, String user_password);

    Integer getTotal(User user);
}
