package com.fuzamei.common.enums.http.msg;

import lombok.Getter;

/**
 * @author ylx
 * Created by fuzamei on 2018/5/11.
 */
@Getter
public enum ErrorMsg {

    LESS_THAN("最大值应该小于", "maximum should be lesser than "),
    LESS_THAN_OR_EQUAL("最大值应该小于等于", "maximum should be lesser than or equal "),
    GREATER_THAN("最小值应该大于", "minimum should be greater than "),
    GREATER_THAN_OR_EQUAL("最小值应该大于等于", "minimum should be greater than or equal "),
    NULL_POINT("对象为null", "the object is null"),
    NUMBER_PARSE_EXCEPTION("数据类型解析异常", "parsing data occurs exception"),
    WRONG_FORMAT("与指定格式不符", "wrong input format"),
    CAST_STRING_EXCEPTION("强转String异常", "cast to String type occurs exception"),
    EMPTY_STRING("字符串不能为空", "string can not be empty"),
    CAST_INTEGER_EXCEPTION("强转Inetegr异常", "cast to integer type occurs exception"),
    CAST_LONG_EXCEPTION("强转Long异常", "cast to long type occurs exception"),
    NULL_INTEGER("integer为空", "integer can not be null"),
    PARAMETER_LOST("请求参数丢失", "parameter in request lost"),
    PARAMETER_ERROR("参数错误", "parameter domain"),

    NULL_AUTH("Authorization为空", "you have lost your Authorization in your request header"),
    TOKEN_FAIL("TOKEN验证失败，请重新失败", "token validation failed, please login again"),
    TOKEN_TIMEOUT("token信息超时，请重新登录", "token is now unavailable, please login again"),
    NO_AUTH("用户无权操作", "users have no authority to operate"),
    INSERT_TOKEN_FAIL("保存token失败", "fail to insert new token"),
    UPDATE_TOKEN_FAIL("更新token失败", "fail to update new token"),
    ACCOUNT_FREEZED("账号被冻结", "fail to update new token"),
    UPDATE_FAIL("更新数据失败", "fail to update"),
    VERIFY_CODE_FAIL("验证码验证失败", "fail to verify the code"),
    NULL_ACCOUNT("账号不存在", "the account doesn't exists"),
    REGISTER_FAIL("注册失败", "fail to register"),
    HAS_PASSWORD("密码已经存在", "password exists already"),
    NO_PASSWORD("还未设置过初始密码", "password doesn't exists yet"),
    NOT_SEAL("用户没有签章", "user has not seal"),
    NOT_SIGN("未添加签署方", "No signature was added"),
    BLOCK_CHIAN_ERROR("区块链发生错误: ", "blockchain occurs domain: "),
    CFCA_ERROR("CFCA发生错误: ", "CFCA occurs domain: "),
    CONTRACT_IS_NOT_INVALID("合同状态不是未生效", "CONTRACT_IS_NOT_INVALID "),
    CONTRACT_IS_NOT_CREATOR("不是合同发起者", "CONTRACT_IS_NOT_CREATOR "),
    DUPLICATE_ACCOUNT("账号名重复", "duplicate account name"),

    ;

    private String messageCN;
    private String messageEN;

    ErrorMsg(String messageCN, String messageEN) {
        this.messageCN = messageCN;
        this.messageEN = messageEN;
    }

}
