package action.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.opensymphony.xwork2.ActionSupport;

import jdbc.login.MySQLConnBean;

public class LoginAction extends ActionSupport {
	public String username;
	public String password;
    public static Connection con=null;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@SuppressWarnings("finally")
	public String actionSql() throws SQLException{
		boolean flag=false;
		String name=getUsername();
		String word=getPassword();
		MySQLConnBean.getConnection();
		if(con==null) {
			return "error";
		}
		String sqlString="select * from user";
		ResultSet rs=MySQLConnBean.executeQuery(sqlString);
		try {
			if(rs.next())
			{
				if((name.compareTo(rs.getString("username"))==0
						&&(word.compareTo(rs.getString("password"))==0))) {
					flag=true;
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			if(flag) {
				return "success";
			}else {
				return  "error";
			}
		}
	}
}
