package com.fuzamei.common.enums.auth;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/7/8.
 */
@Getter
public enum RoleEnum {

    PERSON(1L,"个人"),
    ENTERPRISE(2L,"企业"),
    LAWYER(3L,"律师"),
    FINANCIAL(4L,"金融主体"),
    OPERATOR(5L,"经办人"),
    ;

    private Long roleId;
    private String roleName;

    RoleEnum(Long roleId,String roleName){
        this.roleId = roleId;
        this.roleName = roleName;
    }

}
