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
public enum ProjectLogicExpressionEnum {

    ALL(1, "全部"),
    ANY(2, "任意");


    @EnumValue
    @JsonValue
    private Integer value;

    private String desc;


    /**
     * 枚举入参注解
     *
     * @param value
     * @return
     */
    @JsonCreator
    public static ProjectLogicExpressionEnum getByValue(Integer value) {
        for (ProjectLogicExpressionEnum typeEnum : values()) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }
}
