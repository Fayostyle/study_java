package cn.fayostyle.dbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.fayostyle.a_beans.Admin;
import cn.fayostyle.a_beans.JdbcUtil;

public class App_query {
	
	private Connection conn;
	public App_query() {
		
	}
	
	//1查询 自定义结果集封装数据
	@Test
	public void testQuery() throws Exception {
		String sql = "select * from admin where id=?";
		conn = JdbcUtil.getConnection();
		//创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		//查询
		Admin admin = qr.query(conn, sql, new ResultSetHandler<Admin>() {
			//如何封装一个Admin对象
			public Admin handle(ResultSet rs) throws SQLException {
				if(rs.next()) {
					Admin admin = new Admin();
					admin.setId(rs.getInt("id"));
					admin.setUserName(rs.getString("userName"));
					admin.setPwd(rs.getString("password"));
					return admin;
				}
				return null;
			}
		}, 7);
		//测试
		System.out.println(admin);
		conn.close();
	}
	
	//2 查询 使用组件提供的结果街封装数据
	
	//1 BeanHandler： 查询返回单个对象
	@Test
	public void testQueryOne() throws Exception {
		String sql = "select * from admin where id=?";
		conn = JdbcUtil.getConnection();
		//创建DbUtils核心工具类对象
		QueryRunner qr = new QueryRunner();
		Admin admin = qr.query(conn, sql, new BeanHandler<Admin>(Admin.class), 7);
		System.out.println(admin);
		conn.close();
				
	}
	
	//2 BeanListHandler: 查询返回list集合，集合元素是指定的对象
	@Test
	public void testQueryList() throws Exception {
		String sql = "select * from admin";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		List<Admin> list = qr.query(conn, sql, new BeanListHandler<Admin>(Admin.class));
		System.out.println(list);
		conn.close();
	}
	
	//ArrayHandler,查询返回结果记录的第一行，封装对象数组，即返回 Object[]
	@Test
	public void testArrayHandler() throws Exception{
		String sql = "select * from admin";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		Object[] objects = qr.query(conn, sql, new ArrayHandler());
		for(int i=0;i<objects.length; i++){
			
			System.out.print(objects[i]+"-");
		
		}
	}
	
	//AyyarListHandler 把查询的每一行都封装为对象数组，再添加到list集合中
	@Test
	public void testArrayListHandler() throws Exception{
		String sql = "select * from admin";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		List<Object[]> list = qr.query(conn, sql, new ArrayListHandler());
		for(int i =0;i<list.size();i++) {
			Object[] objects = list.get(i);
			for(int j = 0; j < objects.length; j++) {
				System.out.print(objects[j]);
			}
			System.out.println();
		}
	}
	
	
	//查询返回结果记录的第一行的第一列， 在聚合函数统计的时候用
	@Test
	public void testScalarHandler() throws Exception{
		String sql = "select * from admin";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		long num = qr.query(conn,sql, new ScalarHandler<Integer>());
		System.out.println(num);
	}
	
	//MapHandler 查询返回结果的第一条记录封装为map，key为列名称，value为对应的值
	@Test
	public void testMapHandler() throws Exception{
		String sql = "select * from admin";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		Map<String, Object> map = qr.query(conn, sql, new MapHandler());
		System.out.println(map);
	}
	
	
	
	
	
	

}
