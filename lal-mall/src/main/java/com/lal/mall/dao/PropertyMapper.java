package com.lal.mall.dao;

import com.lal.mall.entity.Property;
import com.lal.mall.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropertyMapper {
    Integer insertOne(@Param("property") Property property);

    Integer insertList(@Param("property_list") List<Property> propertyList);

    Integer updateOne(@Param("property") Property property);

    Integer delete(@Param("property_id_list") Integer[] property_id_list);

    List<Property> select(@Param("property") Property property, @Param("pageUtil") PageUtil pageUtil);

    Property selectOne(@Param("property_id") Integer property_id);

    Integer selectTotal(@Param("property") Property property);

    Property selectNameAndId(@Param("name") String name,@Param("category_id") Integer category_id);

}
