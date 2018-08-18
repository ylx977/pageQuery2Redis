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

    void delete(QueryBO queryBO);

    void update(QueryBO queryBO);
}
