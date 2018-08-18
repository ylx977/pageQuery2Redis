package com.fuzamei.common.enums.http;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/5 10:00
 * @Description:
 */
public enum CodeStatus {


	SUCCESS("200","成功"),
    SYS_ERROR("500","系统内部错误: "),
    VALI_ERROR("300","参数校验失败: "),
    ILLEGAL("400","参数非法"),
    LOGIN_SUCCESS("200","登录成功"),
    LOGIN_ERROR("201","登录出错"),
    NULL_USER("202","用户不存在"),
    WRONG_USER_PWD("203","用户名或密码错误"),
    NO_AUTH("204","无权操作"),
    TOEKN_FAIL("205","token验证失败"),
    USER_FREEZE("206","账号处于冻结状态"),
    NO_AUTHENTICATION_TYPE("207","用户无认证类型"),
    UNAUTHENTICATED("208","用户还未认证，无权操作"),
    SYS_BUSY("209","系统繁忙，请稍后再试"),


    PARAM_ERROR("301", "参数错误"),
    USER_NOT_FOUND("302", "用户不存在"),
    USER_FOUNDED("303", "账号已注册"),

    NOT_AUTHENTICAION("305", "账号未认证"),
    NOT_SET_PWD("306", "未设置密码"),
    VERIFY_ERROR("307", "验证码错误"),
    CONTRACT_INVALID("311", "合同已作废"),
    PHONE_NOT_EMPTY("312", "手机号不能为空"),
    CODE_ERROR("313", "验证码错误"),
    NOT_ADMIN("314", "不是管理员"),
    Contract_IS_EFFECTIVE("315", "合同已生效"),
    Contract_IS_CREATER("316", "不是合同发起方"),
    NOT_SEAL("317", "没有签章"),
    CONTRACT_IS_OPERATION("318", "该合同正在操作"),

    MAILBOX_UNVERIFIED("324", "原邮箱未验证"),
    MAILBOX_BOUNDINGS("325", "该邮箱已绑定账号"),
    MAILBOX_EXIST("326", "已存在绑定邮箱"),
    MAILBOX_NOTBOUND("327", "该账号无绑定邮箱"),
    ORIGINAL_PWDERROR("328", "原密码输入错误"),
    NEWPWD_ATYPISM("329", "两次输入密码不一致"),
    NEWPWD_EQUALOLDPWD("330", "新密码不能和原密码一样"),

	PHONE_BINDING("341","该用户已绑定手机"),
	PHONE_EXIST("342","该手机号码已存在"),
	NOT_ORIGINAL_PHONE("343","非原手机号码"),
	ORIGINAL_PHONE_UNVERIFIED("344","原手机号未验证"),
	PHONE_UNBOUND("345","该用户尚未绑定手机"),
	ACCOUNT_TYPE_ERROR("346","不存在该类型账户"),
	ACCOUNT_AUTHENTICATED("347","该账户已认证"),
	IDENTITY_AUTHENTICATED("348","该身份信息已被认证"),
    ACCOUNT_TYPE_MISMATCHING("348","账户类型不匹配"),
    BANK4_CHECK_ERROR("349","银行四要素检验不通过"),
    PROCESS_ERROR("350", "处理流程异常"),


    SQL_INNER_ERROR("520", "数据库内部错误");


    private String code;

    private String message;

    CodeStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
