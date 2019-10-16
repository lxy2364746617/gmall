package com.lxy.gmall.manage.mapper;

import com.lxy.gmall.bean.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-11 下午 4:35
 */
public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {

    List<SpuSaleAttr> selectSpuSaleAttrList(String spuId);

    /**
     * 查询销售属性对象集合
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(String skuId, String spuId);

}
