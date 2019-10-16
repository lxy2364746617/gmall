package com.lxy.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Chris
 * @data 2019-10-11 下午 11:07
 */
@Data
public class SkuImage implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    private String skuId;

    @Column
    private String imgName;

    @Column
    private String imgUrl;

    @Column
    private String spuImgId;

    @Column
    private String isDefault;

}
