package com.lxy.gmall.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Chris
 * @data 2019-10-15 下午 2:51
 */
@Data
public class SkuLsInfo implements Serializable {

    private String id;

    private BigDecimal price;

    private String skuName;

    private String catalog3Id;

    private String skuDefaultImg;

    private Long hotScore = 0L;

    private List<SkuLsAttrValue> skuAttrValueList;
}
