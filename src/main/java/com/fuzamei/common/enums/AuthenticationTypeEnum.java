package com.fuzamei.common.enums;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/3 11:44
 * @Description:
 */
public enum AuthenticationTypeEnum {

    NOTAuthentication(0,"未认证"),
    Authentication(1,"已认证"),
    ;
    private Integer code;
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    AuthenticationTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (AuthenticationTypeEnum authenticationTypeEnum : AuthenticationTypeEnum.values()) {
            if (authenticationTypeEnum.getCode().equals(code)) {
                return authenticationTypeEnum.getText();
            }
        }
        return "";
    }

}
