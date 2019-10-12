package com.lxy.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Chris
 * @data 2019-10-11 下午 3:40
 */
@Data
public class BaseSaleAttr implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    private String name;

}
