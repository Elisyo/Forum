package forum.persistance.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Groupe;
import forum.bean.data.Hobby;
import forum.bean.data.MessageNotification;
import forum.bean.data.RequestNotification;
import forum.bean.data.User;

public interface IUserDAO {

	public void removeUser(User user) throws SQLException;

	public ArrayList<Groupe> getListGroupOfUser(User user) throws SQLException;
	
	public User findUserByNameAccount(String name) throws SQLException;

	public ArrayList<User> getFriendsListOfUser(String name) throws SQLException;

	public ArrayList<Hobby> getHobbiesListOfUser(String name) throws SQLException;

	public void sendFriendRequest(User destinateur, User destinataire) throws SQLException;

	public void acceptFriendRequest(User destinateur, User userCourant) throws SQLException;

	public void declineFriendRequest(User destinateur, User userCourant) throws SQLException;

	public ArrayList<User> getAllUser();

	public ArrayList<User> getOtherUser();

	public void addHobby(Hobby hobby) throws SQLException;

	public void removeHobby(Hobby hobby) throws SQLException;

	public ArrayList<RequestNotification> getRequestNotification();

	public ArrayList<MessageNotification> getMessageNotification();

}
