package forum.persistance.dao;

import java.sql.SQLException;

import forum.bean.data.Groupe;
import forum.bean.data.User;

public interface INotificationDAO {

	void sendMessageNotification(Groupe groupe) throws SQLException;

	void sendRequestionNotification(User destinateur, User userCourant) throws SQLException;

}
