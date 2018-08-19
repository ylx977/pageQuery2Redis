package com.fuzamei.service.impl;

import com.fuzamei.mapper.PageQueryMapper;
import com.fuzamei.model.QueryBO;
import com.fuzamei.model.UserDO;
import com.fuzamei.service.PageQueryService;
import com.fuzamei.utils.page.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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
        List<UserDO> rows = pageQueryMapper.query(queryBO);
        int total = pageQueryMapper.queryCount(queryBO);
        return PageDTO.getPagination(total,rows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(QueryBO queryBO) {
        int count = pageQueryMapper.findSameUsername(queryBO);
        if(count != 0){
            throw new RuntimeException("username不能重复");
        }
        int success = pageQueryMapper.insert(queryBO);
        if(success == 0){
            throw new RuntimeException("插入数据失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(QueryBO queryBO) {
        pageQueryMapper.delete(queryBO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QueryBO queryBO) {
        if(!StringUtils.isEmpty(queryBO.getUsername())){
            int count = pageQueryMapper.findSameUsername(queryBO);
            if(count != 0){
                throw new RuntimeException("username不能重复");
            }
        }
        int success = pageQueryMapper.update(queryBO);
        if(success == 0){
            throw new RuntimeException("更新失败");
        }
    }
}
