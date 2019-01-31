package wuds.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**C3P0数据库连接池工具<BR/>
 *包含获取数据库连接方法
 * @author liulei
 *
 */
public class C3P0Util {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
	
	/**获取数据库连接
	 * @return 从数据库连接池中取出的一个数据库连接
	 * @throws SQLException
	 * @see Connection
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
		//TODO logger
	}
}
