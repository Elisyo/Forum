package forum.services;

import java.sql.SQLException;

import forum.bean.data.Groupe;
import forum.bean.data.MessageNotification;
import forum.bean.data.RequestNotification;
import forum.bean.data.User;

public interface INotificationServices {

	void sendMessageNotification(Groupe groupe) throws SQLException;

	void sendRequestionNotification(User destinateur, User userCourant) throws SQLException;

	public MessageNotification getMessageNotificationByid(int id) throws SQLException;

	public RequestNotification getRequestNotificationByid(int id) throws SQLException;

}
