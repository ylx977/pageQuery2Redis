package com.fuzamei.common.enums;

import lombok.Getter;

/**
 * @Author: ryh
 * @Create: 2018/7/23 15:29
 * @Description: 后台认证状态枚举类
 */
@Getter
public enum AuthenticationStatusEnum {
    PENDING_REVIEW(0, "待审核"),
    AUTHENTICATED(1, "已认证"),
    NOT_PASS(2, "未通过")
    ;

    private Integer id;
    private String typeName;

    AuthenticationStatusEnum(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }
}
