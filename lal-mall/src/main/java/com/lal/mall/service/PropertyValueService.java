package com.lal.mall.service;

import com.lal.mall.entity.PropertyValue;
import com.lal.mall.util.PageUtil;

import java.util.List;

public interface PropertyValueService {
    boolean add(PropertyValue propertyValue);
    boolean addList(List<PropertyValue> propertyValueList);
    boolean update(PropertyValue propertyValue);
    boolean delete(Integer[] propertyValue_id_list);
    List<PropertyValue> getListByProductId(Integer productId, PageUtil pageUtil);
    List<PropertyValue> getList(PropertyValue propertyValue, PageUtil pageUtil);
    PropertyValue get(Integer propertyValue_id);
    Integer getTotal(PropertyValue propertyValue);
}
