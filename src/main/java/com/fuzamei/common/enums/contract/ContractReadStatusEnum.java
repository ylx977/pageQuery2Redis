package com.fuzamei.common.enums.contract;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/4 19:15
 * @Description:
 */
public enum ContractReadStatusEnum {

    READ(0, "已读"),
    NOT_READ(1, "未读"),;


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

    ContractReadStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (ContractReadStatusEnum contractReadStatusEnum : ContractReadStatusEnum.values()) {
            if (contractReadStatusEnum.getCode().equals(code)) {
                return contractReadStatusEnum.getText();
            }
        }
        return "";
    }
}
