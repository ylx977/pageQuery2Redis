package com.fuzamei.utils.page;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * This is a pagination DTO
 * @author ylx
 * @modifier ylx[yanglingxiao2009@163.com]
 */
@Setter
@Getter
@ToString
public class PageDTO {

	private PageDTO() {}

	public static final <T> PageDTO getPagination(int total, List<T> rows){
		PageDTO pagination = new PageDTO();
		pagination.setRows(rows);
		pagination.setTotal(total);
		return pagination;
	}

	/**
	 * 需要显示的数据
	 */
	private List rows;

	/**
	 * 总的页数
	 */
	private int total;


}