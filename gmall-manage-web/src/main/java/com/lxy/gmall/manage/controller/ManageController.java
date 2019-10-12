package com.lxy.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lxy.gmall.bean.*;
import com.lxy.gmall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-09 下午 6:25
 */
@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;

    /**
     * 查询一级分类数据
     * @return
     */
    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> getCatalog1() {
        return manageService.getCatalog1();
    }

    /**
     * 查询二级分类数据
     * @return
     */
    @RequestMapping("getCatalog2")
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        return manageService.getCatalog2(catalog1Id);
    }

    /**
     * 查询三级分类数据
     * @param catalog2Id
     * @return
     */
    @RequestMapping("getCatalog3")
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        return manageService.getCatalog3(catalog2Id);
    }

    /**
     * 根据三级分类id查询属性
     * @param catalog3Id
     * @return
     */
    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        return manageService.getAttrInfoList(catalog3Id);
    }

    /**
     * 添加属性
     * @return
     */
    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        manageService.saveBaseAttrInfo(baseAttrInfo);
        return "OK!";
    }

    /**
     * 根据attrId修改属性值
     * @param attrId
     * @return
     */
    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId) {
//        return manageService.getAttrValueList(attrId);
        BaseAttrInfo baseAttrInfo = manageService.getBaseAttrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
    }

    @RequestMapping("spuList")
    public List<SpuInfo> spuList(SpuInfo spuInfo) {
        return manageService.getSpuInfoList(spuInfo);
    }
}
