package com.lxy.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Chris
 * @data 2019-10-09 下午 6:31
 */
@Data
public class BaseAttrInfo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String attrName;

    @Column
    private String catalog3Id;

    @Transient
    private List<BaseAttrValue> attrValueList;
}
