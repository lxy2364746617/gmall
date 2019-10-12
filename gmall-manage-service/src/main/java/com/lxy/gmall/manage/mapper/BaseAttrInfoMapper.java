package com.lxy.gmall.manage.mapper;

import com.lxy.gmall.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-09 下午 6:42
 */
public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {

    List<BaseAttrInfo> selectBaseAttrInfoListByCatalog3Id(String catalog3Id);

}
