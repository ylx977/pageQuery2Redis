package com.fuzamei.common.enums;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/9 16:25
 * @Description: 操作记录类型
 */
public enum OperationTypeEnum {

    CREATE(1, "创建合同"),
    SIGN(2, "签署合同"),
    REFUSED(3, "拒绝合同"),
    AUTHENTICATION(4, "身份认证"),
    ADD(5, "添加子账号"),
    EDIT(6, "编辑子账号"),
    ENABLE(7, "启用子账号"),
    DISABLE(8, "停用子账号"),;


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

    OperationTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (OperationTypeEnum operationTypeEnum : OperationTypeEnum.values()) {
            if (operationTypeEnum.getCode().equals(code)) {
                return operationTypeEnum.getText();
            }
        }
        return "";
    }
}
