package forum.action;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.action.LogAction;
import forum.bean.data.Groupe;
import forum.bean.data.Hobby;
import forum.bean.data.User;
import forum.services.IUserServices;
import forum.utils.MyFactory;

public class UserAction {
	
	final IUserServices userServices = (IUserServices) MyFactory.getInstance(IUserServices.class);
	
	private static UserAction INSTANCE = null;

	private UserAction() {
	}

	public synchronized static UserAction getInstance() {
		if (INSTANCE == null)
			INSTANCE = new UserAction();
		return INSTANCE;
	}
	
	public void removeUser(User user) throws SQLException{
		if(user != null){
			userServices.removeUser(user);
		}
	}
	
	public ArrayList<Groupe> getListGroupOfUser(User user) throws SQLException{
		ArrayList<Groupe> listGroupOfUser = new ArrayList<Groupe>();
		listGroupOfUser = userServices.getListGroupOfUser(user);
		return listGroupOfUser;
	}
	
	
	public ArrayList<Hobby> getListHobbiesOfUser(User user) throws SQLException{
		ArrayList<Hobby> listHobbiesOfUser = new ArrayList<Hobby>();
		listHobbiesOfUser = userServices.getListHobbiesOfUser(user);
		return listHobbiesOfUser;
	}
	
	
	public ArrayList<User> getFriendsListOfUser(String name) throws SQLException{
		ArrayList<User> friendsListOfUser = new ArrayList<User>();
		friendsListOfUser = userServices.getFriendsListOfUser(name);
		return friendsListOfUser;
	}
	
	public User getUserByName(String name) throws SQLException{
		User user = new User();
		user = userServices.getUserByName(name);
		return user;
	}
	
	public void sendFriendRequest(User destinataire) throws SQLException{
		userServices.sendFriendRequest(LogAction.getInstance().currentUser,destinataire);
	}
	
	public void acceptFriendRequest(User destinateur) throws SQLException{
		userServices.acceptFriendRequest(destinateur, LogAction.getInstance().currentUser);
	}
	
	public void declineFriendRequest(User destinateur) throws SQLException{
		userServices.declineFriendRequest(destinateur,LogAction.getInstance().currentUser);
	}
	
	public ArrayList<User> getAllUser() throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		users = userServices.getAllUser();
		return users;
	}
	
	public ArrayList<User> getOtherUser() throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		users = userServices.getOtherUser();
		return users;
	}
	
	public void addHobby(Hobby hobby) throws SQLException{
		userServices.addHobby(hobby);
	}
	
	public void removeHobby(Hobby hobby) throws SQLException{
		userServices.removeHobby(hobby);
	}
}
