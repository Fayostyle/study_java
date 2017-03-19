package cn.fayostyle.service;

import cn.fayostyle.entity.Admin;
import cn.fayostyle.exception.UserExistsException;

/**
 * ҵ���߼���ӿ����
 * @author fayosytle
 * @time 2017��2��6������9:09:16
 */
public interface IAdminService {
	/**
	 * ע��
	 */
	void register(Admin admin) throws UserExistsException;
	
	/**
	 * ��½
	 */
	Admin login(Admin admin);
}
