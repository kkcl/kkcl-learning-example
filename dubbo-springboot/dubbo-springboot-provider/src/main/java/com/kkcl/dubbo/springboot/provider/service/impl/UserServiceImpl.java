package com.kkcl.dubbo.springboot.provider.service.impl;

import com.kkcl.dubbo.springboot.api.bean.UserAddress;
import com.kkcl.dubbo.springboot.api.service.UserService;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Arrays;
import java.util.List;

@Service   //暴露服务
@Component
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address1 = new UserAddress(1, "北京市", "1", "李老师", "010-56253823", "Y");
        UserAddress address2 = new UserAddress(2, "西安市", "1", "王老师", "010-56253812", "N");
        return Arrays.asList(address1,address2);
    }
}
