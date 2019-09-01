package com.kkcl.gmall.service;

import java.util.List;

import com.kkcl.gmall.bean.UserAddress;

/**
 * 用户服务
 * @author lfy
 *
 */
public interface UserService {
	public List<UserAddress> getUserAddressList(String userId);
}
