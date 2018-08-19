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
    private String password;
    private Long price;
    private Long startPrice;
    private Long endPrice;
    private String address;
    private Long startTime;
    private Long endTime;
    private Long ctime;
    private Long utime;


    private String redisUsername;
    private String redisAddress;
    private Long redisStartPrice;
    private Long redisEndPrice;
    private Long redisStartTime;
    private Long redisEndTime;

    public void verifyQuery() {
        ValidationUtil.checkAndAssignDefaultInt(this.page,1);
        ValidationUtil.checkAndAssignInt(this.rowNum);
        this.startPage = (page - 1) * rowNum;

        this.redisStartTime = this.startTime;
        this.redisEndTime = this.endTime;

        this.startTime = ValidationUtil.checkAndAssignDefaultLong(this.startTime, 0L);
        this.endTime = ValidationUtil.checkAndAssignDefaultLong(this.endTime, Long.MAX_VALUE);
        if(this.startTime > this.endTime){
            this.endTime = Long.MAX_VALUE;
        }

        this.redisStartPrice = this.startPrice;
        this.redisEndPrice = this.endPrice;

        this.startPrice = ValidationUtil.checkAndAssignDefaultLong(this.startPrice, 0L);
        this.endPrice = ValidationUtil.checkAndAssignDefaultLong(this.endPrice, Long.MAX_VALUE);
        if(this.startPrice > this.endPrice){
            this.endPrice = Long.MAX_VALUE;
        }
        this.redisAddress = ValidationUtil.checkBlankStringAndAssignNullIfIsBlank(this.address);
        this.redisUsername = ValidationUtil.checkBlankStringAndAssignNullIfIsBlank(this.username);
    }

    public void verifyInsert() {
        ValidationUtil.checkBlankAndAssignString(this.username);
        ValidationUtil.checkBlankAndAssignString(this.password);
        ValidationUtil.checkBlankAndAssignString(this.address);
        ValidationUtil.checkAndAssignLong(this.price);
        this.ctime = System.currentTimeMillis();
        this.utime = System.currentTimeMillis();
    }

    public void verifyUpdate() {
        ValidationUtil.checkAndAssignLong(this.id);
        if(this.price == null &&
           this.address == null &&
           this.username == null &&
           this.password == null){
            throw new RuntimeException("参数不能全为空");
        }
        if(this.price != null){
            ValidationUtil.checkAndAssignLong(this.price);
        }
        if(this.address != null){
            ValidationUtil.checkBlankAndAssignString(this.address);
        }
        if(this.username != null){
            ValidationUtil.checkBlankAndAssignString(this.username);
        }
        if(this.password != null){
            ValidationUtil.checkBlankAndAssignString(this.password);
        }
        this.utime = System.currentTimeMillis();
    }

    public void verifyDelete() {
        this.ids.stream().forEach(ValidationUtil::checkAndAssignLong);
    }
}
