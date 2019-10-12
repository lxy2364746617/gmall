package com.lxy.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lxy.gmall.bean.UserAddress;
import com.lxy.gmall.service.UserService;
import com.lxy.gmall.user.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-08 下午 8:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressByUserId(String userId) {
        /*UserAddress userAddress = new UserAddress();
        userAddress.setId(userId);
        List<UserAddress> list = userAddressMapper.select(userAddress);
        return list;*/

        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("userId", userId);
        return userAddressMapper.selectByExample(example);
    }
}
