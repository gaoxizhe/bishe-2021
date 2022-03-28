package com.ms.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_system_param")
public class SystemParam extends Model<SystemParam> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
      * key值 
      */
    private String paramKey;

    /**
      * value值 
      */
    private String paramValue;

    /**
      * 创建人 
      */
    private String gmtCreate;

    /**
      * 修改时间 
      */
    private Date gmtUpdateTime;

    /**
      * 修改人 
      */
    private String gmtUpdate;

    /**
      * 创建时间 
      */
    private Date gmtCreateTime;

}