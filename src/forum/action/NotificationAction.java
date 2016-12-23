package forum.action;

import java.sql.SQLException;

import forum.bean.data.MessageNotification;
import forum.bean.data.RequestNotification;
import forum.services.INotificationServices;
import forum.utils.MyFactory;

public class NotificationAction {
	
	private static NotificationAction INSTANCE = null;

	private NotificationAction() {
	}

	public synchronized static NotificationAction getInstance() {
		if (INSTANCE == null)
			INSTANCE = new NotificationAction();
		return INSTANCE;
	}
	
	final INotificationServices notificationServices = (INotificationServices) MyFactory.getInstance(INotificationServices.class);

	private static NotificationAction INSTANCE = null;
	
	public MessageNotification getMessageNotificationByid(int id) throws SQLException{
		MessageNotification mn = notificationServices.getMessageNotificationByid(id);
		return mn;
	}
	
	public RequestNotification getRequestNotificationByid(int id) throws SQLException{
		RequestNotification rn = notificationServices.getRequestNotificationByid(id);
		return rn;
	}
	public synchronized static NotificationAction getInstance() {
		if (INSTANCE == null)
			INSTANCE = new NotificationAction();
		return INSTANCE;
	}
}
