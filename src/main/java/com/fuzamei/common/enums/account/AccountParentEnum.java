package com.fuzamei.common.enums.account;

/**
 * @author: lingjun.jlj
 * @date: 2018/7/26 14:18
 * @description: account表pid是0的为父账号
 */
public enum  AccountParentEnum {

    PARENT(0L,"父账号"),;

    private Long code;
    private String text;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    AccountParentEnum(Long code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Long code) {
        if (code == null) return "";
        for (AccountParentEnum accountParentEnum : AccountParentEnum.values()) {
            if (accountParentEnum.getCode().equals(code)) {
                return accountParentEnum.getText();
            }
        }
        return "";
    }
}
