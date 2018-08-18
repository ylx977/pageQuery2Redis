package com.fuzamei.common.enums.contract;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/10 17:03
 * @Description:
 */
public enum HandlerTaskEnum {

    CREATE(0, "创建"),
    SIGN(1, "签署"),
    REFUSED(3, "拒签"),;


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

    HandlerTaskEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (HandlerTaskEnum handlerTaskEnum : HandlerTaskEnum.values()) {
            if (handlerTaskEnum.getCode().equals(code)) {
                return handlerTaskEnum.getText();
            }
        }
        return "";
    }
}
