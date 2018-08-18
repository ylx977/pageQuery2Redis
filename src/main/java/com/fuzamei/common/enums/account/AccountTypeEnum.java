package com.fuzamei.common.enums.account;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/2 18:22
 * @Description:
 */
public enum AccountTypeEnum {

    PERSON(1, "个人"),
    ENTERPRISE(2, "企业"),
    LAWYER(3, "律师"),
    FINANCIAL(4, "金融主体"),
    STAFF(5,"企业员工账号"),;


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

    AccountTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            if (accountTypeEnum.getCode().equals(code)) {
                return accountTypeEnum.getText();
            }
        }
        return "";
    }
}
