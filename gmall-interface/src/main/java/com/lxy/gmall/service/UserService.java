package com.lxy.gmall.service;

import com.lxy.gmall.bean.UserAddress;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-08 下午 8:29
 */
public interface UserService {

    /**
     * 根据id查询用户地址
     * @param userId
     * @return
     */
    List<UserAddress> findUserAddressByUserId(String userId);
}
