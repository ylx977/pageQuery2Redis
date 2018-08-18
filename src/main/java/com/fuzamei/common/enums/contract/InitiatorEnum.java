package com.fuzamei.common.enums.contract;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/4 19:11
 * @Description:
 */
public enum InitiatorEnum {

    ACCEPT(0, "合同接受者"),
    SEND(1, "合同发起者"),;


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

    InitiatorEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (InitiatorEnum initiatorEnum : InitiatorEnum.values()) {
            if (initiatorEnum.getCode().equals(code)) {
                return initiatorEnum.getText();
            }
        }
        return "";
    }
}
