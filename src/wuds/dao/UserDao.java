package wuds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wuds.entity.User;
import wuds.util.C3P0Util;

/**用户数据访问类
 * @author liulei
 *
 */
public class UserDao {
	
	/**重置密码为123456
	 * @param username
	 * @param grade
	 * @return
	 */
	public int resetPassword(String username, String grade) {
		int count = 0;
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("update user set password=MD5('123456') where username=? and grade=?");
			statement.setString(1, username);
			statement.setString(2, grade);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**根据姓名或学号查找
	 * @param str姓名或学号
	 * @param grade
	 * @return
	 */
	public List<User> findByUN(String str, String grade) {
		List<User> users = new ArrayList<>();
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select * from user where (username=? or name=?) and grade=?");
			statement.setString(1, str);
			statement.setString(2, str);
			statement.setString(3, grade);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				users.add(dump(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	/**根据班级查找用户
	 * @param grade
	 * @return
	 */
	public List<User> findByGrade(String grade){
		List<User> users = new ArrayList<>();
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select * from user where grade=?");
			statement.setString(1, grade);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				users.add(dump(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	/**修改用户密码
	 * @param username 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 返回修改记录条数，修改成功返回1，失败返回0
	 */
	public int changePassword(String username, String oldPassword, String newPassword) {
		int count = 0;
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("update user set password=MD5(?) where username=? and password=MD5(?)");
			statement.setString(1, newPassword);
			statement.setString(2, username);
			statement.setString(3, oldPassword);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**登录验证
	 * @param username 用户名长度为10
	 * @param password 密码
	 * @return 用户名密码正确返回包含用户信息的user对象，否则返回null
	 */
	public User login(String username, String password) {
		User user = null;
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select * from user where username=? and password=MD5(?)");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				user = dump(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**数据转储<BR/>
	 * 将数据从结果集ResultSet中转储到user中
	 * @param resultSet
	 * @return 保存用户信息的user对象
	 * @throws SQLException
	 */
	private User dump(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setUsername(resultSet.getString("username"));
		user.setPassword(resultSet.getString("password"));
		user.setRole(resultSet.getInt("role"));
		user.setName(resultSet.getString("name"));
		user.setGrade(resultSet.getString("grade"));
		return user;
	}
}
