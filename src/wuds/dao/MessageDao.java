package wuds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wuds.entity.Message;
import wuds.util.C3P0Util;

/**公告信息数据访问类
 * @author liulei
 *
 */
public class MessageDao {
	public List<Message> getAllMessage() {
		List<Message> messages = new ArrayList<>();
		try(Connection connection = C3P0Util.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("select * from message");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				messages.add(dump(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	private Message dump(ResultSet resultSet) throws SQLException {
		Message message = new Message();
		message.setMsg(resultSet.getString("msg"));
		return message;
	}
}
