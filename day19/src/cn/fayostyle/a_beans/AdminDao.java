package cn.fayostyle.a_beans;

import java.util.List;

public class AdminDao extends BaseDao {

	public AdminDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void save(Admin admin) {
		String sql = "INSERT INTO ADMIN (USERNAME,PASSWORD) VALUES (?,?)";
		Object[] paramsValue = {admin.getUserName(), admin.getPwd()};
		super.update(sql, paramsValue);
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM ADMIN WHERE ID=?";
		Object[] paramsValue = {id};
		super.update(sql, paramsValue);
	}
	
	public List<Admin> getAll() {
		String sql = "SELECT * FROM ADMIN";
		List<Admin> list = super.query(sql, null, Admin.class);
		return list;
	}
	
	public Admin findById(int id) {
		String sql = "SELECT * FROM ADMIN WHERE ID=?";
		Object[] paramsValue = {id};
		List<Admin> list = super.query(sql, paramsValue, Admin.class);
		
		return (list.size()>0&&list!=null)?list.get(0) : null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
