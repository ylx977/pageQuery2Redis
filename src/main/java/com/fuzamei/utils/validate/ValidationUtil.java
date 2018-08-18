package com.fuzamei.utils.validate;


import com.fuzamei.common.enums.http.msg.ErrorMsg;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ylx
 * @describe: 针对hibernate-validator的实体类进行数据校验的工具类
 * @describe: 针对该项目中其它各类数据校验的工具类
 * @Date: 2017-12-26
 */
@Component
public class ValidationUtil {

	/**
	 * @Title: checkRangeOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的范围,超出范围的也会报异常.
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkRangeOfInt(final Object obj, int min, int max, final String... patterns) {
		String number;
		try {
			if (obj == null) {
				throw new NullPointerException(ErrorMsg.PARAMETER_LOST.getMessageCN());
			}
			number = String.class.cast(obj);
			try {
				Integer num = Integer.parseInt(number);
				if (num < min) {
					throw new RuntimeException(ErrorMsg.GREATER_THAN.getMessageCN() + min);
				}
				if(num > max){
					throw new RuntimeException(ErrorMsg.LESS_THAN.getMessageCN() + max);
				}
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (e instanceof OutOfRangeException) {
				throw new OutOfRangeException(e.getMessage());
			}
			Integer num = null;
			try {
				num = Integer.class.cast(obj);
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (num < min) {
				throw new RuntimeException(ErrorMsg.GREATER_THAN.getMessageCN()+min);
			}
			if(num > max){
				throw new RuntimeException(ErrorMsg.LESS_THAN.getMessageCN()+max);
			}
		}
		if(patterns.length!=0){//格式校验
			for (String pattern : patterns) {
				if(String.valueOf(obj).matches(pattern)){
					return true;
				}
			}
			throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
		}
		return true;
	}


	/**
	 * @Title: checkMinOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最小值,小于最小值的也会报异常.
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMinOfInt(final Object obj, int min,final String...patterns) {
		return checkRangeOfInt(obj, min, Integer.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkMinOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 限定数值的最小值,小于最小值会报异常，	如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMinOfInt(final Integer intValue, int min,final String...patterns) {
		return checkRangeOfInt(intValue, min, Integer.MAX_VALUE, patterns);
	}

	/**
	 * @Title: checkMaxOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,大于最大值的也会报异常.
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMaxOfInt(final Object obj, int max,final String...patterns) {
		return checkRangeOfInt(obj, Integer.MIN_VALUE, max,patterns);
	}

	/**
	 * @Title: checkMaxOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 限定数值的最大值,大于最大值的也会报异常，如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMaxOfInt(final Integer intValue, int max,final String...patterns) {
		return checkRangeOfInt(intValue, Integer.MIN_VALUE, max,patterns);
	}

	/**
	 * @Title: checkOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			针对整个Integer范围内的数据校验(也就是能被解析成Integer的String还是Integer就能校验成功返回true)
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkOfInt(final Object obj,final String...patterns) {
		return checkRangeOfInt(obj, Integer.MIN_VALUE, Integer.MAX_VALUE,patterns);
	}

	/**
	 * 针对多个参数的一个格式的校验
	 * @param pattern
	 * @param objs
	 * @return
	 */
	public static final boolean checkOfMultiInt(final String pattern, final Object... objs){
		for (Object obj : objs){
			if(!checkOfInt(obj,pattern)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 针对多个参数的一个格式的校验
	 * @param pattern
	 * @param ints
	 * @return
	 */
	public static final boolean checkOfMultiInt(final String pattern, final Integer... ints){
		for (Integer obj : ints){
			if(!checkOfInt(obj,pattern)){
				return false;
			}
		}
		return true;
	}

	/**
	 * @Title: checkOfInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 针对整个Integer范围内的数据校验(也就是能被解析成Integer的String还是Integer就能校验成功返回true)
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkOfInt(final Integer intValue,final String...patterns) {
		return checkRangeOfInt(intValue, Integer.MIN_VALUE, Integer.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkRangeOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的范围,超出范围的也会报异常.
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkRangeOfLong(final Object obj, long min, long max, final String...patterns) {
		String number = null;
		try {
			if (obj == null) {
				throw new NullPointerException(ErrorMsg.PARAMETER_LOST.getMessageCN());
			}
			number = String.class.cast(obj);
			try {
				Long num = Long.parseLong(number);
				if (num < min) {
					throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
				}
				if(num > max){
					throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
				}
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (e instanceof OutOfRangeException) {
				throw new OutOfRangeException(e.getMessage());
			}
			Long num = null;
			try {
				num=Long.parseLong(String.valueOf(obj));
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (num < min) {
				throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
			}
			if(num > max){
				throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
			}
		}
		if(patterns.length!=0){//格式校验
			for (String pattern : patterns) {
				if(String.valueOf(obj).matches(pattern)){
					return true;
				}
			}
			throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
		}
		return true;
	}
	
	/**
	 * @Title: checkMinOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最小值,小于最小值的也会报异常.
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMinOfLong(final Object obj, long min,final String...patterns) {
		return checkRangeOfLong(obj, min, Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkMinOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 限定数值的最小值,小于最小值的也会报异常,如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMinOfLong(final Long longValue, long min,final String...patterns) {
		return checkRangeOfLong(longValue, min, Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkMaxOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,大于最大值的也会报异常.
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMaxOfLong(final Object obj, long max, final String...patterns) {
		return checkRangeOfLong(obj, Long.MIN_VALUE, max,patterns);
	}

	/**
	 * @Title: checkMaxOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 限定数值的最大值,大于最大值的也会报异常,如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkMaxOfLong(final Long longValue, long max, final String...patterns) {
		return checkRangeOfLong(longValue, Long.MIN_VALUE, max, patterns);
	}

	/**
	 * @Title: checkOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			这个针对Long范围内类型数据的校验
	 * 			如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkOfLong(final Object obj,final String...patterns) {
		return checkRangeOfLong(obj, Long.MIN_VALUE, Long.MAX_VALUE,patterns);
	}

	/**
	 * 针对多个参数一个格式的校验
	 * @param pattern
	 * @param objs
	 * @return
	 */
	public static final boolean checkOfMultiLong(final String pattern,final Object... objs){
		for(Object obj : objs){
			if(!checkOfLong(obj,pattern)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 针对多个参数一个格式的校验
	 * @param pattern
	 * @param longs
	 * @return
	 */
	public static final boolean checkOfMultiLong(final String pattern,final Long... longs){
		for(Long obj : longs){
			if(!checkOfLong(obj,pattern)){
				return false;
			}
		}
		return true;
	}

	/**
	 * @Title: checkOfLong
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 针对Long范围内类型数据的校验,如果一切都解析正常,返回一个true的布尔类型
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验
	 * @return boolean
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final boolean checkOfLong(final Long longValue,final String...patterns) {
		return checkRangeOfLong(longValue, Long.MIN_VALUE, Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkRangeAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的范围,超出范围的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkRangeAndAssignInt(final Object obj, int min, int max, final String...patterns) {
		String number = null;
		try {
			if (obj == null) {
				throw new NullPointerException();
			}
			number = String.class.cast(obj);
			try {
				Integer num = Integer.parseInt(number);
				if (num < min) {
					throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
				}
				if(num > max){
					throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
				}
				if(patterns.length!=0){//格式校验
					for (String pattern : patterns) {
						if(number.matches(pattern)){
							return num;
						}
					}
					throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
				}
				return num;
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (e instanceof OutOfRangeException) {
				throw new OutOfRangeException(e.getMessage());
			}
			Integer num = null;
			try {
				num = Integer.class.cast(obj);
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (num < min) {
				throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN() + min);
			}
			if(num > max){
				throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN() + max);
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(String.valueOf(obj).matches(pattern)){
						return num;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return num;
		}
	}
	
	/**
	 * @Title: checkRangeAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 限定数值的范围,超出范围的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkRangeAndAssignInt(final Integer intValue, int min, int max, final String...patterns) {
		if(intValue == null){
			throw new RuntimeException(ErrorMsg.PARAMETER_LOST.getMessageCN());
		}
		if (intValue < min) {
			throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
		}
		if(intValue > max){
			throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
		}
		if(patterns.length!=0){//格式校验
			for (String pattern : patterns) {
				if(String.valueOf(intValue).matches(pattern)){
					return intValue;
				}
			}
			throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
		}
		return intValue;
	}

	/**
	 * @Title: checkMinAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最小值,小于最小值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkMinAndAssignInt(final Object obj, int min,final String...patterns) {
		return checkRangeAndAssignInt(obj,min,Integer.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkMinAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 限定数值的最小值,小于最小值的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkMinAndAssignInt(final Integer intValue, int min,final String...patterns) {
		return checkRangeAndAssignInt(intValue,min,Integer.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkMaxAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,大于最大值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkMaxAndAssignInt(final Object obj, int max, final String...patterns) {
		return checkRangeAndAssignInt(obj,Integer.MIN_VALUE,max,patterns);
	}

	/**
	 * @Title: checkMaxAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 限定数值的最大值,大于最大值的也会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkMaxAndAssignInt(final Integer intValue, int max, final String...patterns) {
		return checkRangeAndAssignInt(intValue,Integer.MIN_VALUE,max,patterns);
	}

	/**
	 * @Title: checkAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			这个针对Integer范围内类型数据的校验和赋值
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkAndAssignInt(final Object obj, final String...patterns) {
		return checkRangeAndAssignInt(obj,Integer.MIN_VALUE,Integer.MAX_VALUE,patterns);
	}

	/**
	 * 针对多个参数一个格式的校验
	 * @param pattern
	 * @param objs
	 * @return
	 */
	public static final int[] checkAndAssignMultiInt(final String pattern,final Object...objs){
		int[] ints = new int[objs.length];
		for(int i = 0; i < objs.length; i++){
			ints[i] = checkAndAssignInt(objs[i],pattern);
		}
		return ints;
	}
	/**
	 * 针对多个参数一个格式的校验
	 * @param pattern
	 * @param integers
	 * @return
	 */
	public static final int[] checkAndAssignMultiInt(final String pattern,final Integer...integers){
		int[] ints = new int[integers.length];
		for(int i = 0; i < integers.length; i++){
			ints[i] = checkAndAssignInt(integers[i],pattern);
		}
		return ints;
	}

	/**
	 * @Title: checkAndAssignInt
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 针对Integer范围内类型数据的校验和赋值,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以int类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final int checkAndAssignInt(final Integer intValue, final String...patterns) {
		return checkRangeAndAssignInt(intValue, Integer.MIN_VALUE, Integer.MAX_VALUE, patterns);
	}

	/**
	 * @Title: checkBlankIntegerAndAssignNullIfIsBlank
	 * @Description: 针对传入的参数进行校验--->Integer类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Integer类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			这个针对Integer范围内类型数据的校验和赋值
	 * 			如果传入的参数为null，空串""或空长串" "均返回null
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以Integer类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return int
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final Integer checkBlankIntegerAndAssignNullIfIsBlank(final Object obj ,final String...patterns) {
		String number = null;
		try {
			if (obj == null) {
				throw new NullPointerException();
			}
			number = String.class.cast(obj);
			if("".equals(number.trim())) {
				throw new NullPointerException();
			}
			try {
				Integer num = Integer.parseInt(number);
				if (num < Integer.MIN_VALUE) {
					throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+Integer.MIN_VALUE);
				}
				if(num > Integer.MAX_VALUE){
					throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+Integer.MAX_VALUE);
				}
				if(patterns.length!=0){//格式校验
					for (String pattern : patterns) {
						if(number.matches(pattern)){
							return num;
						}
					}
					throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
				}
				return num;
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				return null;
			}
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			if (e instanceof OutOfRangeException) {
				throw new OutOfRangeException(e.getMessage());
			}
			Integer num = null;
			try {
				num = Integer.class.cast(obj);
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (num < Integer.MIN_VALUE) {
				throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+Integer.MIN_VALUE);
			}
			if(num > Integer.MAX_VALUE){
				throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+Integer.MAX_VALUE);
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(String.valueOf(obj).matches(pattern)){
						return num;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return num;
		}
	}

	
	
	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Double类型的数据,而且String类型还必须是能被解析成double的,否则会抛出异常.
	 * 			而且还要限定数值的范围,超出范围的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkRangeAndAssignDouble(final Object obj, double min, double max,final String...patterns) {
		String number = null;
		try {
			if (obj == null) {
				throw new NullPointerException();
			}
			number = String.class.cast(obj);
			try {
				Double num = Double.parseDouble(number);
				if (num < min) {
					throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
				}
				if(num > max){
					throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
				}
				if(patterns.length!=0){//格式校验
					for (String pattern : patterns) {
						if(number.matches(pattern)){
							return num;
						}
					}
					throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
				}
				return num;
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (e instanceof OutOfRangeException) {
				throw new OutOfRangeException(e.getMessage());
			}
			Double num = null;
			try {
				num = Double.parseDouble(String.valueOf(obj));
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (num < min) {
				throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
			}
			if(num > max){
				throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(String.valueOf(obj).matches(pattern)){
						return num;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return num;
		}
	}
	
	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 限定数值的范围,超出范围的也会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkRangeAndAssignDouble(final Double doubleValue, double min, double max,final String...patterns) {
		if(doubleValue==null){
			throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
	}
		if (doubleValue < min) {
			throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
		}
		if(doubleValue > max){
			throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
		}
		if(patterns.length!=0){//格式校验
			for (String pattern : patterns) {
				if(String.valueOf(doubleValue).matches(pattern)){
					return doubleValue;
				}
			}
			throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
		}
		return doubleValue;
	}
	
	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Double类型的数据,而且String类型还必须是能被解析成double的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,超出最大值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkMinAndAssignDouble(final Object obj, double min, final String...patterns) {
		return checkRangeAndAssignDouble(obj,min,Double.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 限定数值的最大值,超出最大值的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkMinAndAssignDouble(final Double doubleValue, double min,final String...patterns) {
		return checkRangeAndAssignDouble(doubleValue,min,Double.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Double类型的数据,而且String类型还必须是能被解析成double的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,超出最大值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkMaxAndAssignDouble(final Object obj,double max,final String...patterns) {
		return checkRangeAndAssignDouble(obj,Double.MIN_VALUE,max,patterns);
	}

	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 限定数值的最大值,超出最大值的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkMaxAndAssignDouble(final Double doubleValue,double max,final String...patterns) {
		return checkRangeAndAssignDouble(doubleValue,Double.MIN_VALUE,max,patterns);
	}

	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Double类型的数据,而且String类型还必须是能被解析成double的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,超出最大值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkAndAssignDouble(final Object obj, final String...patterns) {
		return checkRangeAndAssignDouble(obj,Double.MIN_VALUE,Double.MAX_VALUE,patterns);
	}

	/**
	 * @Title: checkRangeAndAssignDouble
	 * @Description: 针对传入的参数进行校验--->Double类型
	 * @Detail: 限定数值的最大值,超出最大值的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以double类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return double
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final double checkAndAssignDouble(final Double doubleValue, final String...patterns) {
		return checkRangeAndAssignDouble(doubleValue,-Double.MAX_VALUE,Double.MAX_VALUE,patterns);
	}

	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的范围,超出范围的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkRangeAndAssignLong(final Object obj, long min, long max, final String...patterns) {
		String number = null;
		try {
			if (obj == null) {
				throw new NullPointerException();
			}
			number = String.class.cast(obj);
			try {
				Long num = Long.parseLong(number);
				if (num < min) {
					throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
				}
				if(num > max){
					throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
				}
				if(patterns.length!=0){//格式校验
					for (String pattern : patterns) {
						if(number.matches(pattern)){
							return num;
						}
					}
					throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
				}
				return num;
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (e instanceof OutOfRangeException) {
				throw new OutOfRangeException(e.getMessage());
			}
			Long num = null;
			try {
				num=Long.parseLong(String.valueOf(obj));
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			if (num < min) {
				throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
			}
			if(num > max){
				throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(String.valueOf(obj).matches(pattern)){
						return num;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return num;
		}
	}
	
	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 限定数值的范围,超出范围的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkRangeAndAssignLong(final Long longValue, long min, long max,final String...patterns) {
		if(longValue ==null){
			throw new RuntimeException(ErrorMsg.PARAMETER_LOST.getMessageCN());
		}
		if (longValue < min) {
			throw new RuntimeException(ErrorMsg.GREATER_THAN_OR_EQUAL.getMessageCN()+min);
		}
		if(longValue > max){
			throw new RuntimeException(ErrorMsg.LESS_THAN_OR_EQUAL.getMessageCN()+max);
		}
		if(patterns.length!=0){//格式校验
			for (String pattern : patterns) {
				if(String.valueOf(longValue).matches(pattern)){
					return longValue;
				}
			}
			throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
		}
		return longValue;
	}

	
	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最小值,小于最小值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkMinAndAssignLong(final Object obj, long min, final String...patterns) {
		return checkRangeAndAssignLong(obj,min,Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 限定数值的最小值,小于最小值的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkMinAndAssignLong(final Long longValue, long min, final String...patterns) {
		return checkRangeAndAssignLong(longValue,min,Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			而且还要限定数值的最大值,大于最大值的也会报异常.
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkMaxAndAssignLong(final Object obj, long max, final String...patterns) {
		return checkRangeAndAssignLong(obj,Long.MIN_VALUE,max,patterns);
	}

	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 限定数值的最大值,大于最大值的会报异常,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkMaxAndAssignLong(final Long longValue, long max, final String...patterns) {
		return checkRangeAndAssignLong(longValue,Long.MIN_VALUE,max,patterns);
	}

	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 参数类型虽然是Object,但是要求数据类型是String或者是Long类型的数据,而且String类型还必须是能被解析成整数的,否则会抛出异常.
	 * 			这个针对Long范围内类型数据的校验和赋值
	 * 			如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static long checkAndAssignLong(final Object obj,String...patterns) {
		return checkRangeAndAssignLong(obj,Long.MIN_VALUE,Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: range
	 * @Description: 针对传入的参数进行校验--->Long类型
	 * @Detail: 针对Long范围内类型数据的校验和赋值,如果一切都解析正常,说明解析没有问题,结果将解析后的数值以long类型返回
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @Usage: 适用于数据判断的校验和赋值同时进行
	 * @return long
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final long checkAndAssignLong(final Long longValue, final String...patterns) {
		return checkRangeAndAssignLong(longValue,Long.MIN_VALUE,Long.MAX_VALUE,patterns);
	}

	/**
	 * @Title: range
	 * @Target: 针对空(null)
	 * @Description: TODO(针对传入的参数进行非空(null)校验并返回string数据类型--->只针对String类型，校验和赋值同时进行，同时返回真实的String值)
	 * @Attention: 保证传入的数据是字符串，且不能为空(null)，否则报异常，正常就返回一个正常的String类型的参数
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @return String
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final String checkNullAndAssignString(final Object obj,final String...patterns){
		try {
			if (obj == null) {
				throw new NullPointerException();
			}
			String str=String.class.cast(obj);
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return str;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return str;
		} catch (Exception e) {
			if(e instanceof NullPointerException){
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if(e instanceof ClassCastException){
				throw new RuntimeException(ErrorMsg.CAST_STRING_EXCEPTION.getMessageCN());
			}
			throw new RuntimeException();
		}
	}

	/**
	 * 针对多个参数统一一个格式的校验
	 * @param pattern
	 * @param objs
	 * @return
	 */
	public static final String[] checkNullAndAssignMultiString(final String pattern, final Object...objs){
		String[] strings = new String[objs.length];
		for(int i = 0; i < objs.length; i++){
			strings[i] = checkNullAndAssignString(objs[i],pattern);
		}
		return strings;
	}

	/**
	 * @Title: checkEmptyAndAssignString
	 * @Target: 针对空(null)和空字符串("")
	 * @Description: TODO(针对传入的参数进行非空(null)和空字符串("")校验并返回string数据类型--->只针对String类型，校验和赋值同时进行，同时返回真实的String值)
	 * @Attention: 保证传入的数据是字符串，且不能为空(null)和空字符串("")，否则报异常，正常就返回一个正常的String类型的参数
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @return String
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final String checkEmptyAndAssignString(final Object obj,final String...patterns){
		try {
			if (obj == null) {
				throw new NullPointerException();
			}
			String str=String.class.cast(obj);
			if("".equals(str)){
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return str;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return str;
		} catch (Exception e) {
			if(e instanceof NullPointerException){
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if(e instanceof ClassCastException){
				throw new RuntimeException(ErrorMsg.CAST_STRING_EXCEPTION.getMessageCN());
			}
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 针对多个参数统一一个格式的校验
	 * @param pattern
	 * @param objs
	 * @return
	 */
	public static final String[] checkEmptyAndAssignMultiString(final String pattern, final Object...objs){
		String[] strings = new String[objs.length];
		for(int i = 0; i < objs.length; i++){
			strings[i] = checkEmptyAndAssignString(objs[i],pattern);
		}
		return strings;
	}

	/**
	 * @Title: checkBlankAndAssignString
	 * @target: 针对空(null)和空字符串("")和空内容的字符串("   ")
	 * @Description: TODO(针对传入的参数进行非空(null)和空字符串("")和空内容的字符串("   ")校验并返回string数据类型--->只针对String类型，校验和赋值同时进行，同时返回真实的String值)
	 * @Attention: 保证传入的数据是字符串，且不能为空(null)和空字符串("")和空内容的字符串("   ")，否则报异常，正常就返回一个正常的String类型的参数
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @return String
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final String checkBlankAndAssignString(final Object obj,final String...patterns){
		try {
			if (obj == null) {
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			String str=String.class.cast(obj);
			if("".equals(str.trim())){
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			if(patterns.length != 0){//格式校验
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return str;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return str;
		} catch (Exception e) {
			if(e instanceof NullPointerException){
				throw new RuntimeException(ErrorMsg.NULL_POINT.getMessageCN());
			}
			if(e instanceof ClassCastException){
				throw new RuntimeException(("CAST_STRING_EXCEPTION"));
			}
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 针对多个参数统一一个格式的校验
	 * @param pattern
	 * @param objs
	 * @return
	 */
	public static final String[] checkBlankAndAssignMultiString(final String pattern, final Object...objs){
		String[] strings = new String[objs.length];
		for(int i = 0; i < objs.length; i++){
			strings[i] = checkBlankAndAssignString(objs[i],pattern);
		}
		return strings;
	}

	/**
	 * @Title: checkBlankStringAndAssignNullIfIsBlank
	 * @Description: TODO(针对传入的Object类型参数进行非空(null)和空字符串("")和空内容的字符串("   ")校验并返回string数据类型,只要是null,""或"  "全部返回null,不会抛出异常，除非无法强转为String,其它情况正常转String)
	 * @Attention: 默认返回null
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @return String
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final String checkBlankStringAndAssignNullIfIsBlank(final Object obj, final String...patterns){
		try {
			if (obj == null) {
				return null;
			}
			String str=String.class.cast(obj);
			if("".equals(str.trim())){
				return null;
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return str;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return str;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMsg.CAST_STRING_EXCEPTION.getMessageCN());
		}
	}

	/**
	 * @Title: checkBlankStringAndAssignEmptyIfIsBlank
	 * @Description: TODO(针对传入的参数进行非空(null)和空字符串("")和空内容的字符串("   ")校验并返回string数据类型,只要是null,""或"  "全部返回""空串,不会抛出异常，除非无法强转为String,其它情况正常转String)
	 * @Attention: 默认返回""
	 * @AddFuntion:增加多格式校验功能[2018/1/27 10:48AM]
	 * @return String
	 * @author ylx
	 * @date 2017年12月26日 下午3:48:40
	 */
	public static final String checkBlankStringAndAssignEmptyIfIsBlank(final Object obj,final String...patterns){
		try {
			if (obj == null) {
				return "";
			}
			String str=String.class.cast(obj);
			if("".equals(str.trim())){
				return "";
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return str;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
			return str;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMsg.CAST_STRING_EXCEPTION.getMessageCN());
		}
	}

	/**
	 * 
	* @Title: checkAndAssignDefaultInt
	* @Description: TODO(如果校验的Object不能转换Integer则抛出异常，如果是null，空字符串或者是长空串，统一返回用户指定的默认值defaultInt)
	* @Attention: obj为null，空字符串""或者是长空串"   "都不会抛异常，而是直接返回默认值
	* @return int    返回类型
	* @author ylx
	* @date 2017年12月27日 下午1:51:43
	 */
	public static final int checkAndAssignDefaultInt(final Object obj,int defaultInt){
		String number;
		try {
			if (obj == null) {
				return defaultInt;
			}
			number = String.class.cast(obj);
			if("".equals(number.trim())){
				return defaultInt;
			}
			try {
				return Integer.parseInt(number.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			Integer num = null;
			try {
				num = Integer.class.cast(obj);
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.CAST_INTEGER_EXCEPTION.getMessageCN());
			}
			return num;
		}
	}

	
	/**
	 * 
	* @Title: checkAndAssignDefaultLong
	* @Description: TODO(如果校验的Object不能转换Long则抛出异常，如果是null，空字符串或者是长空串，统一返回用户指定的默认值defaultLong)
	* @Attention: obj为null，空字符串""或者是长空串"   "都不会抛异常，而是直接返回默认值
	* @Solved: 这里要特别注意下，Long.class.cast(obj)当obj是Integer时会出现异常，包装类不能像基本类型一样强转，所以这里改成num=Long.parseLong(""+obj);
	* @return long    返回类型
	* @author ylx
	* @date 2017年12月27日 下午1:51:43
	 */
	public static final long checkAndAssignDefaultLong(final Object obj,final long defaultLong){
		String number;
		try {
			if (obj == null) {
				return defaultLong;
			}
			number = String.class.cast(obj);
			if("".equals(number.trim())){
				return defaultLong;
			}
			try {
				return Long.parseLong(number.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			Long num = null;
			try {
				num=Long.parseLong(String.valueOf(obj));
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.CAST_LONG_EXCEPTION.getMessageCN());
			}
			return num;
		}
	}

	/**
	 * 
	* @Title: checkAndAssignNullIntegerIfIsBlank
	* @Description: TODO(如果校验的Object不能转换Long则抛出异常，如果是null，空字符串或者是长空串，统一返回用户null
	* 				checkAndAssignInt的增强版(对传过来的空串等一系列问题进行扩展处理)
	* @Attention: obj为null，空字符串""或者是长空串"   "都不会抛异常，而是直接返回默认值null
	* @return Integer    返回类型
	* @author ylx
	* @date 2017年12月27日 下午1:51:43
	 */
	public static final Integer checkAndAssignNullIntegerIfIsBlank(final Object obj){
		String number;
		try {
			if (obj == null) {
				return null;
			}
			number = String.class.cast(obj);
			if("".equals(number.trim())){
				return null;
			}
			try {
				return Integer.parseInt(number.trim());
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		} catch (Exception e) {
			if (e instanceof NumberFormatException) {
				throw new RuntimeException(ErrorMsg.NUMBER_PARSE_EXCEPTION.getMessageCN());
			}
			Integer num = null;
			try {
				num=Integer.parseInt(String.valueOf(obj));
			} catch (Exception e1) {
				throw new RuntimeException(ErrorMsg.CAST_INTEGER_EXCEPTION.getMessageCN());
			}
			return num;
		}
	}

	/**
	 * 
	* @Title: checkNullString
	* @Description: TODO(检测字符串是否为null，否则就报错，如果传入格式要求还要符合传入的格式要求，不然也报错)
	* @param @param obj
	* @param @param patterns    设定文件
	* @return void    返回类型
	* @author ylx
	* @date 2018年1月29日 上午11:43:30
	* @throws
	 */
	public static final void checkNullString(Object obj,String...patterns){
		try {
			String str=String.class.cast(obj);
			if(null==str) {
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			if(patterns.length!=0){//格式校验
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 
	* @Title: checkEmptyString
	* @Description: TODO(检测字符串是否为null/""，否则就报错，如果传入格式要求还要符合传入的格式要求，不然也报错)
	* @param @param obj
	* @param @param patterns    设定文件
	* @return void    返回类型
	* @author ylx
	* @date 2018年1月29日 上午11:43:30
	* @throws
	 */
	public static final void checkEmptyString(Object obj,String...patterns){
		try {
			String str=String.class.cast(obj);
			if(null==str) {
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			if("".equals(str)) {
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			//格式校验
			if(patterns.length!=0){
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 
	* @Title: checkBlankString
	* @Description: TODO(检测字符串是否为null/""/"  "，否则就报错，如果传入格式要求还要符合传入的格式要求，不然也报错)
	* @param @param obj
	* @param @param patterns    设定文件
	* @return void    返回类型
	* @author ylx
	* @date 2018年1月29日 上午11:43:30
	* @throws
	 */
	public static final void checkBlankString(Object obj,String...patterns){
		try {
			String str=String.class.cast(obj);
			if (null==str) {
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			if ("".equals(str.trim())) {
				throw new RuntimeException(ErrorMsg.EMPTY_STRING.getMessageCN());
			}
			//格式校验
			if(patterns.length!=0){
				for (String pattern : patterns) {
					if(str.matches(pattern)){
						return;
					}
				}
				throw new RuntimeException(ErrorMsg.WRONG_FORMAT.getMessageCN());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param //如果传入的integer为Null，报错
	 */
	public static final void checkNullInteger(Integer integer){
		if(integer==null){
			throw new RuntimeException(ErrorMsg.NULL_INTEGER.getMessageCN());
		}
	}

	/**
	 * 根据传入进来的对象进行解析，如果不能转换成Integer类型，则统一返回defaultInt
	 * @param obj
	 * @param defaultInt
	 * @return
	 */
	public static final Integer getInteger(Object obj,Integer defaultInt){
		try {
			int i = Integer.parseInt((String) obj);
			return i;
		}catch(Exception e){
			return defaultInt;
		}
	}

	/**
	 * 多个String参数的非空校验
	 * @param Strings
	 */
	public static void checkBlankOfMultiString(final String... Strings){
		for(String obj : Strings){
			ValidationUtil.checkBlankAndAssignString(obj);
		}
	}

	/**
	 * 多个Long参数的非空校验
	 * @param longs
	 */
	public static void checkOfMultiLong(final Long... longs){
		for(Long obj : longs){
			ValidationUtil.checkOfLong(obj);
		}
	}
	
	

}

class OutOfRangeException extends RuntimeException{
	public OutOfRangeException() {}
	public OutOfRangeException(String message) {
		super(message);
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
