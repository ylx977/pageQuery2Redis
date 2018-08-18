/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.fuzamei.controller;


import com.alibaba.fastjson.JSON;
import com.fuzamei.common.enums.http.CodeStatus;
import com.fuzamei.utils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求基类
 */
@ControllerAdvice
@ResponseBody
public class BaseController {

    @ExceptionHandler(value = Exception.class)
    public JsonResult handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return new JsonResult(CodeStatus.ILLEGAL,e);
    }

    public static final boolean retrunJson(HttpServletResponse response, JsonResult jsonResult) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        writer.append(JSON.toJSONString(jsonResult));
        return false;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(CodeStatus.SUCCESS, data);
    }

    public static JsonResult success() {
        return new JsonResult(CodeStatus.SUCCESS);
    }

    public static JsonResult sysError(Exception e) {
        return new JsonResult(CodeStatus.SYS_ERROR,e);
    }

    public static JsonResult valiError(Exception e) {
        return new JsonResult(CodeStatus.VALI_ERROR,e);
    }

    public static JsonResult loginSuccess(Object data) {
        return new JsonResult(CodeStatus.LOGIN_SUCCESS,data);
    }

    public static JsonResult loginSuccess() {
        return new JsonResult(CodeStatus.LOGIN_SUCCESS);
    }

    public static JsonResult loginError(Exception e) {
        return new JsonResult(CodeStatus.LOGIN_ERROR,e);
    }

    public static JsonResult noUserError() {
        return new JsonResult(CodeStatus.NULL_USER);
    }

    public static JsonResult loginFail() {
        return new JsonResult(CodeStatus.WRONG_USER_PWD);
    }

    public static JsonResult sysBusy() {
        return new JsonResult(CodeStatus.SYS_BUSY);
    }

    public static JsonResult authError(Exception e) {
        return new JsonResult(CodeStatus.NO_AUTH);
    }

    public static JsonResult tokenError(Exception e) {
        return new JsonResult(CodeStatus.TOEKN_FAIL);
    }

    public static JsonResult freezeError(Exception e) {
        return new JsonResult(CodeStatus.USER_FREEZE,e);
    }

    public static JsonResult authenticationError() {
        return new JsonResult(CodeStatus.NO_AUTHENTICATION_TYPE);
    }

    public static JsonResult authenticationFail() {
        return new JsonResult(CodeStatus.UNAUTHENTICATED);
    }

    public static JsonResult error(CodeStatus codeStatus) {
        return new JsonResult(codeStatus.getCode(), codeStatus.getMessage(), null);
    }

    public static JsonResult RESULT(CodeStatus codeStatus) {
        return new JsonResult(codeStatus.getCode(), codeStatus.getMessage(), null);
    }

    public static JsonResult error(String code, String message) {
        return new JsonResult(code, message, null);
    }

    public static JsonResult PARM() {
        return new JsonResult(CodeStatus.PARAM_ERROR.getCode(), CodeStatus.PARAM_ERROR.getMessage(), null);
    }

}
