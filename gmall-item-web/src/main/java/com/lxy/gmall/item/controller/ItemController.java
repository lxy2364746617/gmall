package com.lxy.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lxy.gmall.bean.SkuInfo;
import com.lxy.gmall.bean.SkuSaleAttrValue;
import com.lxy.gmall.bean.SpuSaleAttr;
import com.lxy.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chris
 * @data 2019-10-13 下午 3:21
 */
@Controller
public class ItemController {

    @Reference
    private ManageService manageService;

    @RequestMapping("{skuId}.html")
    public String getItem(@PathVariable String skuId, HttpServletRequest request) {
        System.out.println("skuId : " + skuId);
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
//        List<SkuImage> skuImageList = manageService.getSkuImageList(skuId);

        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrListCheckBySku(skuInfo);

        List<SkuSaleAttrValue> skuSaleAttrValueList = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());
        String key = "";
        Map<String, String> map = new HashMap<>();
        if(skuSaleAttrValueList != null && skuSaleAttrValueList.size() > 0) {
            for (int i = 0; i < skuSaleAttrValueList.size(); i++) {
                SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueList.get(i);
                if (key.length() > 0) {
                    key += "|";
                }
                key += skuSaleAttrValue.getSaleAttrValueId();
                if ((i + 1) == skuSaleAttrValueList.size() || !skuSaleAttrValue.getSkuId().equals(skuSaleAttrValueList.get(i + 1).getSkuId())) {
                    map.put(key, skuSaleAttrValue.getSkuId());
                    key = "";
                }
            }
        }

        String valuesSkuJson = JSON.toJSONString(map);
        System.out.println("valuesSkuJson" + valuesSkuJson);



        request.setAttribute("valuesSkuJson", valuesSkuJson);
        request.setAttribute("spuSaleAttrList", spuSaleAttrList);
        request.setAttribute("skuInfo", skuInfo);
//        request.setAttribute("skuImageList", skuImageList);
        return "item";
    }
}
