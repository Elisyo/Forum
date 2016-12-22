package forum.services;

import java.sql.SQLException;

import forum.bean.data.Groupe;
import forum.bean.data.User;

public interface INotificationServices {

	void sendMessageNotification(Groupe groupe) throws SQLException;

	void sendRequestionNotification(User destinateur, User userCourant) throws SQLException;

}
