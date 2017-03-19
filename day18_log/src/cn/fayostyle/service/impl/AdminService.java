package cn.fayostyle.service.impl;

import org.junit.Test;

import cn.fayostyle.dao.IAdminDao;
import cn.fayostyle.dao.impl.AdminDao;
import cn.fayostyle.entity.Admin;
import cn.fayostyle.exception.UserExistsException;
import cn.fayostyle.service.IAdminService;

/**
 * 业务逻辑层实现
 * @author fayosytle
 * @time 2017��2��6������9:14:47
 */
public class AdminService implements IAdminService{
	//调用的dao
	private IAdminDao adminDao = new AdminDao();
	@Override 
	public void register(Admin admin) throws UserExistsException {
		//��ѯ�û����Ƿ����
		
		try {
			boolean flag = adminDao.userExists(admin.getUserName());
			if(flag) {
				//������ע�ᣬ���������ʾ
				throw new UserExistsException("用户名已经存在啦！ ");
			}
			adminDao.save(admin);
		} catch (UserExistsException e) {
			throw e;
		} catch (Exception e){
			throw new RuntimeException(e); 
		}
	}

	@Override
	public Admin login(Admin admin) {
		return adminDao.findByNameAndPwd(admin);	
	}
	


}
