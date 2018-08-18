package com.fuzamei.common.enums.contract;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/19 16:56
 * @Description: 是否现在前世
 */
public enum NowSignStatusEnum {

    LATER(0, "以后"),
    NOW(1, "现在");

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

    NowSignStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (NowSignStatusEnum nowSignStatusEnum : NowSignStatusEnum.values()) {
            if (nowSignStatusEnum.getCode().equals(code)) {
                return nowSignStatusEnum.getText();
            }
        }
        return "";
    }
}
