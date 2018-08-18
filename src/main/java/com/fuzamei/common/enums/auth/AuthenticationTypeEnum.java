package com.fuzamei.common.enums.auth;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/10.
 */
@Getter
public enum AuthenticationTypeEnum {

    AUTHENTICATED(1,"已认证"),
    UNAUTHENTICATED(0,"未认证"),
    ;

    private Integer id;
    private String typeName;

    AuthenticationTypeEnum(Integer id,String typeName){
        this.id = id;
        this.typeName = typeName;
    }

}
