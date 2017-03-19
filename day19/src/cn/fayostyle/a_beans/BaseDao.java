package cn.fayostyle.a_beans;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;


public class BaseDao {

		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		/**
		 * 更新的通用方法
		 * @param sql
		 * @param paramsValue
		 */
		public void update(String sql, Object[] paramsValue) {
			try {
				conn = JdbcUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				int count = pstmt.getParameterMetaData().getParameterCount();
				if(paramsValue != null && paramsValue.length > 0) {
					for(int i = 0; i<count; i++) {
						pstmt.setObject(i+1, paramsValue[i]);
						
					}
				}
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				JdbcUtil.closeAll(conn, pstmt, null);
			}
		}
		
		public <T> List<T> query(String sql, Object[] paramsValues, Class<T> clazz) {
			try {
				List list = new ArrayList<T>();
				T t = null;
				
				conn = JdbcUtil.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				int count = pstmt.getParameterMetaData().getParameterCount();
				if(paramsValues != null && paramsValues.length > 0){
					for(int i = 0; i<count; i++		){	
						pstmt.setObject(i+1, paramsValues[i]);
					}
				}
				
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnNumber = rsmd.getColumnCount();
				
				while(rs.next()) {
					t = clazz.newInstance();
					for(int i=0; i<columnNumber; i++) {
						String columnName = rsmd.getColumnName(i+1);
						Object columnValue = rs.getObject(columnName);
						BeanUtils.copyProperty(t, columnName, columnValue);
					}
					list.add(t);
				}
				return list;
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				JdbcUtil.closeAll(conn, pstmt, rs);
			}
			
		}
		
	
		
}
