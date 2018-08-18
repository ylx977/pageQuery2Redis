package com.fuzamei.common.enums.contract;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/2 17:08
 * @Description: 合同状态
 */
public enum ContractStatusEnum {

    LATER(0, "发起方未签署"),
    NOT_EXECUTED(1, "未生效"),
    EXECUTED(2, "已生效"),
    INVALID(3, "作废"),;


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

    ContractStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) return "";
        for (ContractStatusEnum contractStatusEnum : ContractStatusEnum.values()) {
            if (contractStatusEnum.getCode().equals(code)) {
                return contractStatusEnum.getText();
            }
        }
        return "";
    }
}
