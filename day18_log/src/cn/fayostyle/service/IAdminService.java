package cn.fayostyle.service;

import cn.fayostyle.entity.Admin;
import cn.fayostyle.exception.UserExistsException;

/**
 * 业务逻辑层接口设计
 * @author fayosytle
 * @time 2017年2月6日下午9:09:16
 */
public interface IAdminService {
	/**
	 * 注册
	 */
	void register(Admin admin) throws UserExistsException;
	
	/**
	 * 登陆
	 */
	Admin login(Admin admin);
}
