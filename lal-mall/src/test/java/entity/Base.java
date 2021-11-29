package entity;

import lombok.Data;

/**
 * @program: uzy-ssm-mall-master
 * @description:
 * @author: Mr.Gao
 * @create: 2021-11-25 17:12
 **/
@Data
public class Base {
    private String status;
    private entity.Data data;
    private String logId;
}
