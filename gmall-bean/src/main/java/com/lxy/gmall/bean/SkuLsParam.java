package com.lxy.gmall.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Chris
 * @data 2019-10-15 下午 6:49
 */
@Data
public class SkuLsParam implements Serializable {

    private String keyword;

    private String catalog3Id;

    private String[] valueId;

    private int pageNo = 1;

    private int pageSize = 20;
}
