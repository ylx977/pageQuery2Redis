package com.fuzamei.common.constants;

/**
 * @author ylx
 */
public class RegexConstant {
	private RegexConstant(){
		throw new AssertionError("can not be instaniated");
	}

	/**
	 * 校验密码
	 */
	public static final String PWD_REGEX="^[a-zA-Z0-9]{6,16}$";

	/**
	 * 校验验证码
	 */
	public static final String CODE_REGEX="^[0-9]{4,20}$";

	/**
	 * 校验用户名
	 */
	public static final String USERNAME_REGEX="^\\w{5,20}$";

	/**
	 * 校验人名
	 */
	public static final String NAME_REGEX="^[\\u4e00-\\u9fa50-9A-Za-z]{1,30}$";

	/**
	 * 钱的数据格式
	 */
	public static final String MONEY = "^[\\d]+\\.[\\d]{0,2}|[\\d]+$";

	/**
	 * 身份证号码格式
	 */
	public static final String ID_CARD = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

	/**
	 * 手机号正则表达式
	 */
	public static final String PHONE_REGEX = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";

	/**
	 * 邮箱的正则表达式
	 */
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

	/**
	 * 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）
	 */
	public static final String CONTACT_REGEX = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
}
