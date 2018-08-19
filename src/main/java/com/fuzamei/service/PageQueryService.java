package com.fuzamei.service;

import com.fuzamei.model.QueryBO;
import com.fuzamei.utils.page.PageDTO;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
public interface PageQueryService {

    /**
     * 查询分页数据
     * @param queryBO
     */
    PageDTO query(QueryBO queryBO);

    /**
     * 插入数据
     * @param queryBO
     */
    void insert(QueryBO queryBO);

    /**
     * 根据id删除，支持批量删除
     * @param queryBO
     */
    void delete(QueryBO queryBO);

    /**
     * 根据id更新username address price
     * @param queryBO
     */
    void update(QueryBO queryBO);
}
