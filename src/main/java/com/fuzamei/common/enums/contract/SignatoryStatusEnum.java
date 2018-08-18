package com.fuzamei.common.enums.contract;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/2 17:22
 * @Description: 签署状态
 */
public enum SignatoryStatusEnum {

    CREATE(0, "创建"),
    FINISHED(1, "已签"),
    NOT_FINISH(2, "未签"),
    REFUSE(3, "拒签"),;


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

    SignatoryStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static String getText(Integer code) {
        if (code == null) {
            return "";
        }
        for (SignatoryStatusEnum signatoryStatusEnum : SignatoryStatusEnum.values()) {
            if (signatoryStatusEnum.getCode().equals(code)) {
                return signatoryStatusEnum.getText();
            }
        }
        return "";
    }
}
