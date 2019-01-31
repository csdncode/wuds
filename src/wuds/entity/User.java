package wuds.entity;

/**用户类
 * 包含用户的用户名、密码、角色、姓名、年级
 * <P/>
 * WORK_ADMINISTRATOR是作业管理员的角色属性的值<BR/>
 * USER_ADMINISTRATOR是用户管理员的角色属性的值
 * @author liulei
 *
 */
public class User {
	
	/**作业管理员的角色属性的值
	 */
	public static final int WORK_ADMINISTRATOR = 1;
	
	/**用户管理员的角色属性的值
	 */
	public static final int USER_ADMINISTRATOR = 2;
	
	private String username;
	private String password;
	private int role;
	private String name;
	private String grade;
	
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
