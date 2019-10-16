package com.lxy.gmall.list.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lxy.gmall.bean.SkuLsParam;
import com.lxy.gmall.bean.SkuLsResult;
import com.lxy.gmall.service.ListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Chris
 * @data 2019-10-15 下午 9:24
 */
@Controller
public class ListController {

    @Reference
    private ListService listService;

    @RequestMapping("list.html")
    @ResponseBody
    public String getList(SkuLsParam skuLsParam) {
        SkuLsResult skuLsResult = listService.search(skuLsParam);
        return JSON.toJSONString(skuLsResult);
    }
}
