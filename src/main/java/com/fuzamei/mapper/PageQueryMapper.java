package com.fuzamei.mapper;

import com.fuzamei.model.QueryBO;
import com.fuzamei.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
@Mapper
public interface PageQueryMapper {

    int insert(QueryBO queryBO);

    @Select("select count(*) from user_query where username = #{username}")
    int findSameUsername(QueryBO queryBO);

    int update(QueryBO queryBO);

    void delete(QueryBO queryBO);

    List<UserDO> query(QueryBO queryBO);

    int queryCount(QueryBO queryBO);

    List<UserDO> queryDelete(QueryBO queryBO);

    @Select("select * from user_query where id = #{id}")
    UserDO findDataById(QueryBO queryBO);
}
