package com.lxy.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lxy.gmall.bean.UserAddress;
import com.lxy.gmall.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chris
 * @data 2019-10-08 下午 7:45
 */
@RestController
public class OrderController {

//    @Autowired
    @Reference
    private UserService userService;

    @RequestMapping("trade")
    public List<UserAddress> trade(String userId) {
        return userService.findUserAddressByUserId(userId);
    }
}
