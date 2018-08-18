package com.fuzamei.common.enums.auth;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/10.
 */
@Getter
public enum AccountTypeEnum {

    PERSON(1,"个人"),
    ENTERPRISE(2,"企业"),
    LAWYER(3,"律师"),
    FINANCIAL(4,"金融主体"),
    STAFF(5,"企业员工账号");

    private Integer id;
    private String typeName;

    AccountTypeEnum(Integer id,String typeName){
        this.id = id;
        this.typeName = typeName;
    }

}
