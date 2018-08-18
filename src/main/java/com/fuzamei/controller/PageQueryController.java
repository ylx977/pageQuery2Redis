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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.fuzamei.controller.BaseController.*;

/**
 * @author ylx
 * Created by fuzamei on 2018/8/17.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/query/")
public class PageQueryController {

    @Autowired
    @Qualifier("noRedis")
    private PageQueryService pageQueryService;


    /**
     {
        "page":"",
        "rowNum":"",
        "address":"",
        "time":"",
        "price":""
     }
     * @return
     */
    @RequestMapping("/query")
    public JsonResult query(@RequestBody QueryBO queryBO){
        log.info("查询分页数据");
        try {
            queryBO.verifyQuery();
        }catch (Exception e){
            return valiError(e);
        }
        try {
            PageDTO pageDTO = pageQueryService.query(queryBO);
            return success(pageDTO);
        }catch (Exception e){
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
            return valiError(e);
        }
        try {
            pageQueryService.insert(queryBO);
            return success();
        }catch (Exception e){
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
    @RequestMapping("/update")
    public JsonResult update(@RequestBody QueryBO queryBO){
        log.info("修改数据");
        try {
            queryBO.verifyUpdate();
        }catch (Exception e){
            return valiError(e);
        }
        try {
            pageQueryService.update(queryBO);
            return success();
        }catch (Exception e){
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
            return valiError(e);
        }
        try {
            pageQueryService.delete(queryBO);
            return success();
        }catch (Exception e){
            return sysError(e);
        }
    }

}
