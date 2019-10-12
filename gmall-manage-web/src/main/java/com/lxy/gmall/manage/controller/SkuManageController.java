package com.lxy.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lxy.gmall.bean.SkuInfo;
import com.lxy.gmall.bean.SpuImage;
import com.lxy.gmall.bean.SpuSaleAttr;
import com.lxy.gmall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-11 下午 7:39
 */
@RestController
@CrossOrigin
public class SkuManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("spuImageList")
    public List<SpuImage> getSpuImageList(String spuId) {
        return manageService.getSpuImageList(spuId);
    }

    @RequestMapping("spuSaleAttrList")
    public List<SpuSaleAttr> spuSaleAttrList(String spuId) {
        return manageService.getSpuSaleAttrList(spuId);
    }

    @RequestMapping("saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        manageService.saveSkuInfo();
    }
}
