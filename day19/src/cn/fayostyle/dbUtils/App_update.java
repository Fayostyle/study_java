package cn.fayostyle.dbUtils;

import java.sql.Connection;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import cn.fayostyle.a_beans.JdbcUtil;

public class App_update {
	
	private Connection conn;
	public App_update() {

	}
	
	@Test
	public void testUpdate() throws Exception {
		String sql = "delete from admin where id=?";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		qr.update(conn, sql, 3);
		DbUtils.close(conn);
	}
	
	@Test
	public void testBatch() throws Exception {
		String sql = "insert into admin (userName,password) values (?,?)";
		conn = JdbcUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		qr.batch(conn, sql, new Object[][] {{"lucy1","123"},{"lucy2", "1234"},{"lucy3", "12345"}});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
