package cn.fayostyle.dao;

import cn.fayostyle.entity.Admin;

/**
 * 数据访问层 接口设计
 * @author fayosytle
 * @time 2017年2月5日下午6:55:22
 */
public interface IAdminDao {
	
	/**
	 * 保存
	 */
	void save(Admin admin);
	
	/**
	 * 根据用户名密码查询
	 * @param admin
	 * @return
	 */
	Admin findByNameAndPwd(Admin admin);
	
	/**
	 * 检车用户名是否存在
	 * @param userName 要检查的用户名
	 * @return ture 表示已经存在 否则false不存在即可以注册使用
	 */
	boolean userExists(String userName);
}
