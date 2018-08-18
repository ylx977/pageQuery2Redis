package com.fuzamei.common.enums.auth;

import lombok.Getter;

/**
 * 用于王涛CFCA
 * @author ylx
 * Created by fuzamei on 2018/7/19.
 */
@Getter
public enum IdentityTypeEnum {

    ID_CARD("0","居民身份证"),
    PASSPORT("1","护照"),
    MILITARY("2","军人身份证"),
    COMMERCIAL("3","工商登记证"),
    TAX("4","税务登记证"),
    STOCK("5","股东代码证"),
    SOCIAL_SECURE("6","社会保障卡"),
    ORGANIZATION("7","组织机构代码证"),
    ENTERPRISE_RUN("8","企业营业执照"),
    LEGAL_CARD("9","法人代码证"),
    WARRIOR_ID_CARD("A","武装警察身份证件"),
    HK_PASS("B","港澳居民来往内地通行证"),
    TAIWAN_PASS("C","台湾居民来往大陆通行证"),
    HOUSEHOLD_REGISTER("E","户口簿"),
    TEMP_ID_CARD("F","临时居民身份证"),
    POLICE("G","警察(警官)证"),
    INSTITUTION_LEGAL("H","事业单位法人证书"),
    SOCIAL_GROUP("J","社会团体登记证书"),
    PRIVATE_NON_ENTERPRISE("K","民办非企业登记证书"),
    FOREIGNER_AGENCY("L","外国(地区)企业常驻代表机构"),
    GOVERNMENT_APPROVE("M","政府批文"),
    SOCIAL_UNIFIED_CREDIT("N","统一社会信用代码证"),
    FOREIGNER_LONG_STAY("P","外国人永久居留证"),
    OTHER("Z","其它"),

    ;

    private String code;
    private String name;
    IdentityTypeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }
}
