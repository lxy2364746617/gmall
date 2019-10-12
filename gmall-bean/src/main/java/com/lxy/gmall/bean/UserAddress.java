package com.lxy.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Chris
 * @data 2019-10-08 下午 8:20
 */
@Data
public class UserAddress implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    private String userAddress;

    @Column
    private String userId;

    @Column
    private String consignee;

    @Column
    private String phoneNum;

    @Column
    private String isDefault;

}
