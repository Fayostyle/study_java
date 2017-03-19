package cn.fayostyle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.fayostyle.dao.IAdminDao;
import cn.fayostyle.entity.Admin;
import cn.fayostyle.utils.JdbcUtil;

public class AdminDao implements IAdminDao{
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	@Override
	public void save(Admin admin) {
		String sql = "INSERT INTO admin(userName,PASSWORD) VALUES (?,?)";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getUserName());
			pstmt.setString(2, admin.getPwd());
			
			//ִ执行
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeAll(con, pstmt, null);
		}
	}

	@Override
	public Admin findByNameAndPwd(Admin admin) {
		String sql ="SELECT * FROM admin WHERE userName=? AND PASSWORD=?";
		Admin ad = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getUserName());
			pstmt.setString(2, admin.getPwd());
			//执行
			rs = pstmt.executeQuery();
			
			//遍历
			if(rs.next()) {
				ad = new Admin();
				ad.setId(rs.getInt("id"));
				ad.setUserName(rs.getString("userName"));
				ad.setPwd(rs.getString("password"));
			}
			return ad;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeAll(con, pstmt, rs);
		}
		
	}

	@Override
	public boolean userExists(String userName) {
		String sql = "SELECT id FROM admin WHERE userName=?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			//ִ��
			rs = pstmt.executeQuery();
			//�ж�
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.closeAll(con, pstmt, rs);
		}
	}

}
