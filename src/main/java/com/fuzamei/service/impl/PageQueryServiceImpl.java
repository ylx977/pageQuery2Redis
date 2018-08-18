package com.fuzamei.service.impl;

import com.fuzamei.mapper.PageQueryMapper;
import com.fuzamei.model.QueryBO;
import com.fuzamei.service.PageQueryService;
import com.fuzamei.utils.page.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
@Slf4j
@Service("noRedis")
public class PageQueryServiceImpl implements PageQueryService{

    @Autowired
    private PageQueryMapper pageQueryMapper;


    @Override
    public PageDTO query(QueryBO queryBO) {
        return null;
    }

    @Override
    public void insert(QueryBO queryBO) {

    }

    @Override
    public void delete(QueryBO queryBO) {

    }

    @Override
    public void update(QueryBO queryBO) {

    }
}
