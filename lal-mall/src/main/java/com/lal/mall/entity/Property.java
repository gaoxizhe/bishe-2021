package com.lal.mall.entity;

import lombok.Data;

import java.util.List;

@Data
public class Property {
    private Integer property_id;
    private Integer property_category_id;
    private String property_name;
    private Category property_category;
    private List<PropertyValue> propertyValueList;

    public Property() {
    }

    @Override
    public String toString() {
        return "Property{" +
                "property_id=" + property_id +
                ", property_name='" + property_name + '\'' +
                ", property_category=" + property_category +
                ", propertyValueList=" + propertyValueList +
                '}';
    }

    public Property(Integer property_id, String property_name, Category property_category, List<PropertyValue> propertyValueList) {
        this.property_id = property_id;
        this.property_name = property_name;
        this.property_category = property_category;
        this.propertyValueList = propertyValueList;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public Property setProperty_id(Integer property_id) {
        this.property_id = property_id;
        return this;
    }

    public Integer getProperty_category_id() {
        return property_category_id;
    }

    public void setProperty_category_id(Integer property_category_id) {
        this.property_category_id = property_category_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public Property setProperty_name(String property_name) {
        this.property_name = property_name;
        return this;
    }

    public Category getProperty_category() {
        return property_category;
    }

    public Property setProperty_category(Category property_category) {
        this.property_category = property_category;
        return this;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public Property setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
        return this;
    }
}
