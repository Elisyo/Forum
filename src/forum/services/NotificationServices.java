package forum.services;

import java.sql.SQLException;

import forum.bean.data.Groupe;
import forum.bean.data.User;
import forum.persistance.dao.IHobbyDAO;
import forum.persistance.dao.INotificationDAO;
import forum.utils.MyFactory;

public class NotificationServices implements INotificationServices{

	
	private static NotificationServices INSTANCE = null;
	final INotificationDAO notificationDAO = (INotificationDAO) MyFactory.getInstance(INotificationDAO.class);
	
	private NotificationServices() {
	}

	public synchronized static NotificationServices getInstance() {
		if (INSTANCE == null)
			INSTANCE = new NotificationServices();
		return INSTANCE;
	}
	
	@Override
	public void sendMessageNotification(Groupe groupe) throws SQLException {
		notificationDAO.sendMessageNotification(groupe);
		
	}

	@Override
	public void sendRequestionNotification(User destinateur, User userCourant) throws SQLException {
		notificationDAO.sendRequestionNotification(destinateur,userCourant);
	}

}
