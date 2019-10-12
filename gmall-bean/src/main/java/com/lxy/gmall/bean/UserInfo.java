package com.lxy.gmall.bean;

/**
 * @author Chris
 * @data 2019-10-08 下午 8:19
 */

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    private String loginName;

    @Column
    private String nickName;

    @Column
    private String passwd;

    @Column
    private String name;

    @Column
    private String phoneNum;

    @Column
    private String email;

    @Column
    private String headImg;

    @Column
    private String userLevel;

}
