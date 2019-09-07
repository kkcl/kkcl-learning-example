package com.kkcl.dubbo.springboot.api.service;

import com.kkcl.dubbo.springboot.api.bean.UserAddress;

import java.util.List;



/**
 * 用户服务
 * @author lfy
 *
 */
public interface UserService {
	public List<UserAddress> getUserAddressList(String userId);

}
