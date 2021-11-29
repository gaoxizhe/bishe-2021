package entity;

import lombok.Data;

/**
 * @program: uzy-ssm-mall-master
 * @description:
 * @author: Mr.Gao
 * @create: 2021-11-25 17:15
 **/
@Data
public class ItemListBD {
    private String id;
    private String category;
    private String qid;
    private int sv_cr;
    private String jumpUrl;
    private String jUrl;
    private String videoTime;
    private String picUrl;
    private String printinfo;
    private String printfclick;
    private String from;
    private String corrFrom;
    private String price;
    private String pCurrency;
    private int minValue;
    private String unit;
    private String view_times;
    private String inquiry_times;
    private String fullName;
    private String fullProviderName;
    private String location;
    private int advert;
    private String adnew;
    private int timeSign;
    private int cpaMember;
    private String cpaDuration;
    private int isPioneerOfPop;
    private String show_flags;
    private String lginfo;
    private int spotCertify;
    private String seniorRealType;
    private String verifyList;
    private String dedup_id;
    private int store_type;
    private String store_name;
    private String subtitle;
    private String sku_score;
}
