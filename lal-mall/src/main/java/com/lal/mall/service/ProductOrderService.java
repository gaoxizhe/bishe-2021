package com.lal.mall.service;

import com.lal.mall.entity.OrderGroup;
import com.lal.mall.entity.ProductOrder;
import com.lal.mall.util.OrderUtil;
import com.lal.mall.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface ProductOrderService {
    boolean add(ProductOrder productOrder);

    boolean update(ProductOrder productOrder);

    Integer update1(ProductOrder productOrder);

    boolean deleteList(Integer[] productOrder_id_list);

    List<ProductOrder> getList(ProductOrder productOrder, Byte[] productOrder_status_array, OrderUtil orderUtil, PageUtil pageUtil);

    List<ProductOrder> getListByUserId(Integer user_id, PageUtil pageUtil);

    List<OrderGroup> getTotalByDate(Date beginDate, Date endDate);

    ProductOrder get(Integer productOrder_id);

    ProductOrder getByCode(String productOrder_code);

    Integer getTotal(ProductOrder productOrder, Byte[] productOrder_status_array);

    int paysuccess(String order_code);
}
