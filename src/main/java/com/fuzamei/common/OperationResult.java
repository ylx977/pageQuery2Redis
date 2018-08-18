package com.fuzamei.common;


import com.fuzamei.common.enums.http.CodeStatus;
import lombok.Data;

/**
 * @author fine
 * @date 2018/7/3 下午2:47
 */
@Data
public class OperationResult<T> {

	private Boolean result;

	private T data;

	private CodeStatus codeStatus;

}
