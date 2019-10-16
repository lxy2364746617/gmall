package com.lxy.gmall.service;

import com.lxy.gmall.bean.SkuLsInfo;
import com.lxy.gmall.bean.SkuLsParam;
import com.lxy.gmall.bean.SkuLsResult;

/**
 * @author Chris
 * @data 2019-10-15 下午 3:01
 */
public interface ListService {

    /**
     * 商品上架
     * @param skuLsInfo
     */
    void saveSkuInfo(SkuLsInfo skuLsInfo);

    /**
     * 动态生产dsl语句，从es中检索结果集
     * @param skuLsParam
     * @return
     */
    SkuLsResult search(SkuLsParam skuLsParam);
}
