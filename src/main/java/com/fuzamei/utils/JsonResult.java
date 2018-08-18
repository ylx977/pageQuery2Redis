package com.fuzamei.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fuzamei.common.enums.http.CodeStatus;
import lombok.Data;

/**
 * 【接口请求统一JSON格式响应】
 * @author ylx
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult {

    private String code;

    private String message;

    private Object data;

    public JsonResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResult(CodeStatus codeStatus){
        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage();
    }

    public JsonResult(CodeStatus codeStatus, Exception e){
        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage()+e.getMessage();
    }

    public JsonResult(CodeStatus codeStatus, Object data){
        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage();
        this.data = data;
    }

}