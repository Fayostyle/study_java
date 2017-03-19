package cn.fayostyle.dao;

import cn.fayostyle.entity.Admin;

/**
 * ���ݷ��ʲ� �ӿ����
 * @author fayosytle
 * @time 2017��2��5������6:55:22
 */
public interface IAdminDao {
	
	/**
	 * ����
	 */
	void save(Admin admin);
	
	/**
	 * �����û��������ѯ
	 * @param admin
	 * @return
	 */
	Admin findByNameAndPwd(Admin admin);
	
	/**
	 * �쳵�û����Ƿ����
	 * @param userName Ҫ�����û���
	 * @return ture ��ʾ�Ѿ����� ����false�����ڼ�����ע��ʹ��
	 */
	boolean userExists(String userName);
}
