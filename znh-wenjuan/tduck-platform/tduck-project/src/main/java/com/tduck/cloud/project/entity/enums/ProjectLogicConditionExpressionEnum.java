package com.tduck.cloud.project.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author : smalljop
 * @description :逻辑条件
 * @create : 2020-12-04 13:35
 **/
@Getter
@AllArgsConstructor
public enum ProjectLogicConditionExpressionEnum {

    EQ("eq", "等于"),
    NE("ne", "不等于");


    @EnumValue
    @JsonValue
    private String value;

    private String desc;


    /**
     * 枚举入参注解
     *
     * @param value
     * @return
     */
    @JsonCreator
    public static ProjectLogicConditionExpressionEnum getByValue(Integer value) {
        for (ProjectLogicConditionExpressionEnum typeEnum : values()) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }
}
