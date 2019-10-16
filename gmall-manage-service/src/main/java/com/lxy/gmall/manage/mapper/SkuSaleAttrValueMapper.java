package com.lxy.gmall.manage.mapper;

import com.lxy.gmall.bean.SkuSaleAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-12 上午 8:49
 */
public interface SkuSaleAttrValueMapper extends Mapper<SkuSaleAttrValue> {

    List<SkuSaleAttrValue> selectSkuSaleAttrValueListBySpu(String spuId);

}
