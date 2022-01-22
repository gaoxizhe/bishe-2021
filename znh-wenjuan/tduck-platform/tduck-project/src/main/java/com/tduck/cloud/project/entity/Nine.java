package com.tduck.cloud.project.entity;

import lombok.Data;

@Data
public class Nine {
    //1型完美型
    private double oneTotal;
    private double oneSelect;
    private Double oneScale ;
    //2型助人型
    private double twoTotal;
    private double twoSelect;
    private Double twoScale ;
    //3型成就型
    private double threeTotal;
    private double threeSelect;
    private Double threeScale ;


    //4型自我型
    private double fourTotal;
    private double fourSelect;
    private Double fourScale ;

    //5型理智型
    private double fiveTotal;
    private double fiveSelect;
    private Double fiveScale ;

    //6型怀疑型
    private double sixTotal;
    private double sixSelect;
    private Double sixScale ;



    //7型活跃型
    private double sevenTotal;
    private double sevenSelect;
    private Double sevenScale ;

    //8型领袖型
    private double eightTotal;
    private double eightSelect;
    private Double eightScale ;

    //9型和平型
    private double nineTotal;
    private double nineSelect;
    private Double nineScale ;

    public Double getOneScale() {
        if (oneTotal == 0) {
            return 0D;
        }
        return (double) ((oneSelect / oneTotal) * 10);
    }

    public Double getTwoScale() {
        if (twoTotal == 0) {
            return 0D;
        }
        return (double) ((twoSelect / twoTotal) * 10);
    }

    public Double getThreeScale() {
        if (threeTotal == 0) {
            return 0D;
        }
        return (double) ((threeSelect / threeTotal) * 10);
    }

    public Double getFourScale() {
        if (fourTotal == 0) {
            return 0D;
        }
        return (double) ((fourSelect / fourTotal) * 10);
    }

    public Double getFiveScale() {
        if (fiveTotal == 0) {
            return 0D;
        }
        return (double) ((fiveSelect / fiveTotal) * 10);
    }

    public Double getSixScale() {
        if (sixTotal == 0) {
            return 0D;
        }
        return (double) ((sixSelect / sixTotal) * 10);
    }

    public Double getSevenScale() {
        if (sevenTotal == 0) {
            return 0D;
        }
        return (double) ((sevenSelect / sevenTotal) * 10);
    }

    public Double getEightScale() {
        if (eightTotal == 0) {
            return 0D;
        }
        return (double) ((eightSelect / eightTotal) * 10);
    }

    public Double getNineScale() {
        if (nineTotal == 0) {
            return 0D;
        }
        return (double) ((nineSelect / nineTotal) * 10);
    }
}
