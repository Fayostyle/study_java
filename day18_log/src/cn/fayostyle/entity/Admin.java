package cn.fayostyle.entity;

/**
 * 实体类设计 javabean 
 * @author fayosytle
 * @time 2017��2��5������6:52:13
 */
public class Admin {
	
	private int id;
	private String userName;
	private String pwd;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
