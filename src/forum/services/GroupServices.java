package forum.services;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Groupe;
import forum.bean.data.Message;
import forum.bean.data.MessageGroup;
import forum.bean.data.User;
import forum.persistance.dao.IGroupDAO;
import forum.utils.MyFactory;

public class GroupServices implements IGroupServices{
	
	private static GroupServices INSTANCE = null;
	final IGroupDAO groupDAO = (IGroupDAO) MyFactory.getInstance(IGroupDAO.class);

	private GroupServices() {
	}

	public synchronized static GroupServices getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GroupServices();
		return INSTANCE;
	}
	
	 public static String encrypt(String password){
	        String crypte="";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48;  
	            crypte=crypte+(char)c; 
	        }
	        return crypte;
	    }

	 public static String decrypt(String password){
	        String aCrypter="";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48;  
	            aCrypter=aCrypter+(char)c; 
	        }
	        return aCrypter;
	    }


	public void createGroupe(String nameOfGroup, User creator) throws SQLException {
		groupDAO.createGroup(nameOfGroup, creator);
		Groupe groupe = groupDAO.getGroupeByName(nameOfGroup);
		groupDAO.addUserInGroup(creator, groupe);
	}

	public void addUserInGroup(User user, Groupe groupe) throws SQLException {
		groupDAO.addUserInGroup(user, groupe);
	}

	public void addListUsersInGroup(ArrayList<User> listUsers, Groupe groupe) throws SQLException {
		groupDAO.addListUsersInGroup(listUsers, groupe);		
	}

	public void deleteGroup(Groupe groupe) throws SQLException {
		groupDAO.deleteGroup(groupe);
	}

	public void removeUserOfGroup(User user, Groupe groupe) throws SQLException {
		groupDAO.removeUserOfGroup(user, groupe);
	}

	public void removeMessageUserOfGroup(User user, Groupe groupe) throws SQLException {
		groupDAO.removeMessageUserOfGroup(user,groupe);	
	}

	public ArrayList<User> getListUserOfGroupById(int id) throws SQLException {
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = groupDAO.getListUserOfGroupById(id);
		return listUsers;
	}

	public ArrayList<User> getListUserOfGroupByName(String name) throws SQLException {
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = groupDAO.getListUserOfGroupByName(name);
		return listUsers;
	}

	public ArrayList<Groupe> getListGroupe() throws SQLException {
		ArrayList<Groupe> listeGroupe = new ArrayList<Groupe>();
		listeGroupe = groupDAO.getListGroupe();
		return listeGroupe;
	}

	public ArrayList<MessageGroup> getMessageOfGroupe(int id) throws SQLException {
		ArrayList<MessageGroup> listMessage = new ArrayList<MessageGroup>();
		listMessage = groupDAO.getMessageOfGroupe(id);
		return listMessage;
	}

	public Groupe getGroupById(int id) throws SQLException {
		Groupe groupe = new Groupe();
		groupe = groupDAO.getGroupeById(id);
		return groupe;
	}

	public void createGroupeWithUsers(String nameOfGroup, User creator, ArrayList<User> listUsers) throws SQLException {
		groupDAO.createGroup(nameOfGroup, creator);
		Groupe groupe = groupDAO.getGroupeByName(nameOfGroup);
		addUserInGroup(creator, groupe);
		addListUsersInGroup(listUsers, groupe);
	}

	public void removeAllUsersOfGroup(Groupe groupe) throws SQLException {
		groupDAO.removeAllUsersOfGroup(groupe);
	}

	public void removeMessageOfGroup(int id) throws SQLException {
		groupDAO.removeMessageOfGroup(id);
	}

	public void removeAllMessageOfGroupe(Groupe groupe) throws SQLException {
		groupDAO.removeAllMessageOfGroupe(groupe);
	}

	public void sendMessageGroup(Message message, Groupe groupe) {
		if (message.isCrypt()){
			message.setContenue(encrypt(message.getContenue()));
			groupDAO.sendMessageGroup(message, groupe);
		}
		else{
			groupDAO.sendMessageGroup(message, groupe);
		}
	}

	public ArrayList<User> getFreeUserForGroup(Groupe groupe) {
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = groupDAO.getFreeUserForGroup(groupe);
		return listUsers;
	}



}
