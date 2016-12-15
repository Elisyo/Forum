package forum.services;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Groupe;
import forum.bean.data.Hobby;
import forum.bean.data.User;
import forum.persistance.dao.IUserDAO;
import forum.utils.MyFactory;

public class UserServices implements IUserServices{
	
	private static UserServices INSTANCE = null;
	final IUserDAO userDAO = (IUserDAO) MyFactory.getInstance(IUserDAO.class);

	private UserServices() {
	}

	public synchronized static UserServices getInstance() {
		if (INSTANCE == null)
			INSTANCE = new UserServices();
		return INSTANCE;
	}

	public void removeUser(User user) throws SQLException {
		userDAO.removeUser(user);
	}

	public ArrayList<Groupe> getListGroupOfUser(User user) throws SQLException {
		ArrayList<Groupe> listGroupOfUser = new ArrayList<Groupe>();
		listGroupOfUser = userDAO.getListGroupOfUser(user);
		return listGroupOfUser;
	}

	public ArrayList<Hobby> getListHobbiesOfUser(User user) throws SQLException {
		ArrayList<Hobby> listHobbiesOfUser = new ArrayList<Hobby>();
		listHobbiesOfUser = userDAO.getHobbiesListOfUser(user.getNom());
		return listHobbiesOfUser;
	}


	public ArrayList<User> getFriendsListOfUser(String name) throws SQLException {
		ArrayList<User> friendsListOfUser = new ArrayList<User>();
		friendsListOfUser = userDAO.getFriendsListOfUser(name);
		return friendsListOfUser;
	}

	public User getUserByName(String name) throws SQLException {
		User user = new User();
		user = userDAO.findUserByNameAccount(name);
		return user;
	}

	
	public void sendFriendRequest(User destinateur, User destinataire) throws SQLException {
		userDAO.sendFriendRequest(destinateur, destinataire);
	}

	public void acceptFriendRequest(User destinateur, User userCourant) throws SQLException {
		userDAO.acceptFriendRequest(destinateur, userCourant);
	}

	public void declineFriendRequest(User destinateur, User userCourant) throws SQLException {
		userDAO.declineFriendRequest(destinateur, userCourant);
	}

	public ArrayList<User> getAllUser() {
		ArrayList<User> users = new ArrayList<User>();
		users = userDAO.getAllUser();
		return users;
	}

	public ArrayList<User> getOtherUser() {
		ArrayList<User> users = new ArrayList<User>();
		users = userDAO.getOtherUser();
		return users;
	}

	public void addHobby(Hobby hobby) throws SQLException {
		userDAO.addHobby(hobby);
	}

	public void removeHobby(Hobby hobby) throws SQLException {
		userDAO.removeHobby(hobby);
	}


}
