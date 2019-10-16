package com.lxy.gmall.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chris
 * @data 2019-10-15 下午 6:52
 */
@Data
public class SkuLsResult implements Serializable {

    private List<SkuLsInfo> skuLsInfoList;

    private long total;

    private long totalPages;

    private List<String> attrValueIdList;

}
