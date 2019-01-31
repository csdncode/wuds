package wuds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wuds.entity.Work;
import wuds.util.C3P0Util;

/**作业数据访问类
 * @author liulei
 *
 */
public class WorkDao {
	
	/**删除作业
	 * @param course
	 * @param expNo
	 * @param grade
	 * @return
	 */
	public int deleteWork(String course, String expNo, String grade) {
		int count = 0;
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("delete from work where course=? and expNo=? and grade=?");
			statement.setString(1, course);
			statement.setString(2, expNo);
			statement.setString(3, grade);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**提交人数加1操作
	 * @param course
	 * @param expNo
	 * @param grade
	 * @return
	 */
	public int countPP(String course, String expNo, String grade) {
		int count = 0;
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("update work set count = count + 1 where course=? and expNo=? and grade=?");
			statement.setString(1, course);
			statement.setString(2, expNo);
			statement.setString(3, grade);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**添加作业
	 * @param work
	 * @return 添加成功返回1失败返回0
	 */
	public int add(Work work) {
		int count = 0;
		try(Connection connection = C3P0Util.getConnection()) {
			String sql = "insert into work value(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, work.getCourse());
			statement.setString(2, work.getExpNo());
			statement.setString(3, work.getGrade());
			statement.setLong(4, work.getEndTime());
			statement.setInt(5, work.getCount());
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**根据班级查找作业
	 * @param grade
	 * @return 返回作业list对象
	 */
	public List<Work> findByGrade(String grade) {
		List<Work> works = new ArrayList<Work>();
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select * from work where grade=? order by endTime desc");
			statement.setString(1, grade);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				works.add(dump(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return works;
	}
	
	/**将结果集中的数据转储到work对象中
	 * @param 数据库查询的结果集resultSet
	 * @return work对象
	 * @throws SQLException
	 */
	private Work dump(ResultSet resultSet) throws SQLException {
		Work work = new Work();
		work.setCourse(resultSet.getString("course"));
		work.setExpNo(resultSet.getString("expNo"));
		work.setGrade(resultSet.getString("grade"));
		work.setEndTime(resultSet.getLong("endTime"));
		work.setCount(resultSet.getInt("count"));
		return work;
	}
}
