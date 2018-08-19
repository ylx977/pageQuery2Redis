package com.fuzamei.controller;

import com.fuzamei.model.QueryBO;
import com.fuzamei.service.PageQueryService;
import com.fuzamei.utils.JsonResult;
import com.fuzamei.utils.page.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.fuzamei.controller.BaseController.*;

/**
 * @author ylx
 * 专门做了一个分页的查询系统，数据库和redis同步的一个查询功能，redis实现mysql的分页功能
 * 其中username唯一
 * address不一定唯一
 * Price也不一定唯一 可选择正序或反系排列
 * time也不一定唯一   可选择正序或反序排列
 * Created by fuzamei on 2018/8/17.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/query/")
public class PageQueryController {

    @Autowired
    @Qualifier("useRedis")
    private PageQueryService pageQueryService;


    /**
     {
        "page":"",
        "rowNum":"",
        "address":"",
        "username":"",
        "startPrice":"",
        "endPrice":"",
        "startTime":"",
        "endTime":""
     }
     * @return
     */
    @RequestMapping("/query")
    public JsonResult query(@RequestBody QueryBO queryBO){
        log.info("查询分页数据");
        try {
            queryBO.verifyQuery();
        }catch (Exception e){
            e.printStackTrace();
            return valiError(e);
        }
        try {
            PageDTO pageDTO = pageQueryService.query(queryBO);
            return success(pageDTO);
        }catch (Exception e){
            e.printStackTrace();
            return sysError(e);
        }
    }

    /**
     {
        "username":"",
        "password":"",
        "address":"",
        "price":""
     }
     * @return
     */
    @RequestMapping("/insert")
    public JsonResult insert(@RequestBody QueryBO queryBO){
        log.info("插入行新数据");
        try {
            queryBO.verifyInsert();
        }catch (Exception e){
            e.printStackTrace();
            return valiError(e);
        }
        try {
            pageQueryService.insert(queryBO);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return sysError(e);
        }
    }

    /**
     {
        "id":"",
        "username":"",
        "password":"",
        "address":"",
        "price":""
     }
     * @return
     */
    @RequestMapping("/update")
    public JsonResult update(@RequestBody QueryBO queryBO){
        log.info("修改数据");
        try {
            queryBO.verifyUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return valiError(e);
        }
        try {
            pageQueryService.update(queryBO);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return sysError(e);
        }
    }

    /**
     {
        "ids":[1,2,3]
     }
     * @return
     */
    @RequestMapping("/delete")
    public JsonResult delete(@RequestBody QueryBO queryBO){
        log.info("删除数据");
        try {
            queryBO.verifyDelete();
        }catch (Exception e){
            e.printStackTrace();
            return valiError(e);
        }
        try {
            pageQueryService.delete(queryBO);
            return success();
        }catch (Exception e){
            e.printStackTrace();
            return sysError(e);
        }
    }

}
