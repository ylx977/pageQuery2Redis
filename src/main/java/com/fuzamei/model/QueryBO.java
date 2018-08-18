package com.fuzamei.model;

import com.fuzamei.utils.validate.ValidationUtil;
import lombok.Data;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
@Data
public class QueryBO {

    private Integer page;
    private Integer rowNum;
    private Integer startPage;
    private Long id;
    private List<Long> ids;
    private String username;
    private Long price;
    private String address;
    private Long startTime;
    private Long endTime;

    public void verifyQuery() {
        ValidationUtil.checkAndAssignDefaultInt(this.page,1);
        ValidationUtil.checkAndAssignInt(this.rowNum);
        this.startPage = (page-1)*rowNum;
    }

    public void verifyInsert() {
    }

    public void verifyUpdate() {

    }

    public void verifyDelete() {
        this.ids.stream().forEach(ValidationUtil::checkAndAssignLong);
    }
}
