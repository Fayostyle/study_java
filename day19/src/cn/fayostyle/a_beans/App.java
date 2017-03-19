package cn.fayostyle.a_beans;

import java.lang.Exception;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

public class App {

	public App() {
	}
	
	@Test
	public void test1() throws Exception {
		// 基本操作
		Admin admin = new Admin();
		//admin.setUserName("name");
		admin.setPwd("123");
		
		//BeanUtils组件的使用
		BeanUtils.copyProperty(admin, "userName", "jack1");
		BeanUtils.setProperty(admin, "age", 18);
		//BeanUtils.copyProperties(admin, admin);
		
		//对象的拷贝
		Admin newAdmin = new Admin();
		BeanUtils.copyProperties(newAdmin, admin);
		
		
		//map数据。 拷贝到对象中
		Admin adminMap = new Admin();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "Nacy");
		map.put("age", 90);
		BeanUtils.populate(adminMap, map);
		
		System.out.println(admin);
		System.out.println(newAdmin);
		System.out.println(adminMap);
		
		
	}
	
	@Test
	public void test2() throws Exception {
		// 模拟表单数据
		String name = "jack";
		String age = "20";
		String birth = "1999-12-03";
		
		// 对象
		Admin admin = new Admin();
		
		// 注册日期类型转换器：1， 自定义的方式
		ConvertUtils.register(new Converter() {
			// 转换的内部实现方法，需要重写
			@Override
			public Object convert(Class type, Object value) {
				
				// 判断
				if (type != Date.class) {
					return null;
				}
				if (value == null || "".equals(value.toString().trim())) {
					return null;
				}
				
				
				try {
					// 字符串转换为日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					return sdf.parse(value.toString());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		},Date.class);
		
		/**
		 * 注意此处导包不要导错，一定要是java.util.Date, 开始由于导入了java.sql.Date,就出现了错误！
		 */
		
		
		// 把表单提交的数据，封装到对象中
		BeanUtils.copyProperty(admin, "userName", name);
		BeanUtils.copyProperty(admin, "age", age);
		BeanUtils.copyProperty(admin, "birth", birth);
		
		//------ 测试------
		System.out.println(admin);		
	}
	
	//使用提供的日期类型转换器工具类
	@Test
	public void test3() throws Exception {
		String nameString = "Bog";
		int ageString = 12;
		String birth = "1990-12-31";
		
		Admin admin = new Admin();
		
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		
		BeanUtils.copyProperty(admin, "userName", "li");
		BeanUtils.copyProperty(admin, "age", ageString);
		BeanUtils.copyProperty(admin, "birth", birth);
		
		System.out.println(admin);
	}
	
	@Test
	public void testDB() throws Exception {
		System.out.println("111111");
		Connection connection = JdbcUtil.getConnection();
		System.out.println(connection);
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println(metaData.getUserName());
		System.out.println(metaData.getURL());
		System.out.println(metaData.getCatalogSeparator());
		System.out.println(metaData.getCatalogTerm());
		System.out.println(metaData.getDatabaseProductName());
		System.out.println(metaData.getDatabaseProductVersion());
	}
		
	@Test
	public void testParams() throws Exception {
		Connection conn = JdbcUtil.getConnection();
		
//		DatabaseMetaData metaData = conn.getMetaData();
		String sql = "SELECT * FROM ADMIN WHERE ID=? AND USERNAME=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ParameterMetaData p_metaData = pstmt.getParameterMetaData();
		int count = p_metaData.getParameterCount();
		System.out.println(count);
		
		
	}
	@Test
	public void testRs() throws Exception {
		String sql = "select * from admin";
		
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData r_meData = rs.getMetaData();
		while(rs.next()){
			int count = r_meData.getColumnCount();
			for(int i = 0; i < count; i++) {
				String columnName = r_meData.getColumnName(i+1);
				Object columnValue = rs.getObject(columnName);
				
				System.out.println(columnName + "->" + columnValue);
			}
		}
	}
	
	
	
	
	
	@Test
	public void testAdminDao() {
		AdminDao adminDao = new AdminDao();
		List<Admin> list = adminDao.getAll();
		System.out.println(list);
		
		adminDao.delete(2);
		List<Admin> list2 = adminDao.getAll();
		System.out.println(list2);
		
		Admin admin = adminDao.findById(3);
		System.out.println(admin);
		
		Admin admin2 = new Admin();
		adminDao.save(admin2);
		System.out.println(admin2);
		
		List<Admin> list3 = adminDao.getAll();
		System.out.println(list3);
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
