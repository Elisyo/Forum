package forum.persistance.dao;

import java.sql.SQLException;

import forum.bean.data.Groupe;
import forum.bean.data.MessageNotification;
import forum.bean.data.RequestNotification;
import forum.bean.data.User;

public interface INotificationDAO {

	public void sendMessageNotification(Groupe groupe) throws SQLException;

	public void sendRequestionNotification(User destinateur, User userCourant) throws SQLException;

	public MessageNotification getMessageNotificationByid(int id) throws SQLException;

	public RequestNotification getRequestNotificationByid(int id) throws SQLException;

}
