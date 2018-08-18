package com.fuzamei.common.enums.auth;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/11.
 */
@Getter
public enum FinanceTypeEnum {

    FIVE_BANK(1,"五大行"),
    STOCK_BANK(2,"股份制银行"),
    COMMERCIAL_BANK(3,"城市商业银行"),
    RURAL_CREDIT(4,"农信社"),
    FOREIGN_BANK(5,"外资银行"),
    SECURITIES_FUTURES(6,"证券期货公司"),
    TRUSTEE(7,"信托公司"),
    INSURANCE(8,"保险公司"),
    LOAN(9,"贷款公司"),
    OTHERS(10,"其它金融主体"),

    ;

    private Integer id;
    private String typeName;
    FinanceTypeEnum(Integer id,String typeName){
        this.id = id;
        this.typeName = typeName;
    }
}
