package forum.persistance.dao;

import java.sql.SQLException;

import forum.bean.data.MessageGroup;

public interface IMessageGroupDAO {

	public MessageGroup getMessageById(int id) throws SQLException;
}
