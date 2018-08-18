package com.fuzamei.common.enums.auth;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/10.
 */
@Getter
public enum FreezeEnum {

    UNFREEZE(1,"启用"),
    FREEZE(0,"冻结");

    private Integer code;
    private String name;
    FreezeEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }
}
