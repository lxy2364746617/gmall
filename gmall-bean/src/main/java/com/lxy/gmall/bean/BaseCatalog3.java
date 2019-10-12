package com.lxy.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Chris
 * @data 2019-10-09 下午 6:30
 */
@Data
public class BaseCatalog3 implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String catalog2Id;

}
