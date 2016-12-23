package forum.action;

import java.sql.SQLException;

import forum.bean.data.MessageNotification;
import forum.bean.data.RequestNotification;
import forum.services.INotificationServices;
import forum.utils.MyFactory;

public class NotificationAction {
	
	final INotificationServices notificationServices = (INotificationServices) MyFactory.getInstance(INotificationServices.class);

	public MessageNotification getMessageNotificationByid(int id) throws SQLException{
		MessageNotification mn = notificationServices.getMessageNotificationByid(id);
		return mn;
	}
	
	public RequestNotification getRequestNotificationByid(int id) throws SQLException{
		RequestNotification rn = notificationServices.getRequestNotificationByid(id);
		return rn;
	}
}
