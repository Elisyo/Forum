package forum.action;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Groupe;
import forum.bean.data.Message;
import forum.bean.data.MessageGroup;
import forum.bean.data.User;
import forum.services.IGroupServices;
import forum.utils.MyFactory;

public class GroupAction {

	final IGroupServices groupServices = (IGroupServices) MyFactory.getInstance(IGroupServices.class);
	
	private static GroupAction INSTANCE = null;

	private GroupAction() {
	}

	public synchronized static GroupAction getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GroupAction();
		return INSTANCE;
	}

	public ArrayList<Groupe> getListGroupe() throws SQLException{
		ArrayList<Groupe> listeGroupe = new ArrayList<Groupe>();
		listeGroupe = groupServices.getListGroupe();
		return listeGroupe;
	}
	
	public Groupe getGroupeById(int id) throws SQLException{
		Groupe groupe =new Groupe();
		groupe = groupServices.getGroupById(id);
		return groupe;
	}
	
	
	public void createGroupe(String nameOfGroup, User creator) throws SQLException {
		if (nameOfGroupIsGood(nameOfGroup) && creator != null) {
			groupServices.createGroupe(nameOfGroup, creator);
		}
	}
	
	public void createGroupeWithUsers(String nameOfGroup, User creator, ArrayList<User> listUsers) throws SQLException {
		if (nameOfGroupIsGood(nameOfGroup) && creator != null) {
			groupServices.createGroupeWithUsers(nameOfGroup, creator, listUsers);
		}
	}
	

	public void addUserInGroup(User user, Groupe groupe) throws SQLException {
		if (user != null) {
			groupServices.addUserInGroup(user, groupe);
		}
	}

	public void addListUsersInGroup(ArrayList<User> listUsers, Groupe groupe) throws SQLException {
		if (listUsers != null && listUsers.size() > 0) {
			groupServices.addListUsersInGroup(listUsers, groupe);
		}
	}

	public void removeUserOfGroup(User user, Groupe groupe) throws SQLException {
		if (user != null) {
			groupServices.removeUserOfGroup(user, groupe);
		}
	}
	
	public void removeAllUsersOfGroup(Groupe groupe) throws SQLException{
		if(groupe!=null){
			groupServices.removeAllUsersOfGroup(groupe);
		}
	}

	public void removeMessageOfGroup(int id) throws SQLException{
		if(id!=0){
			groupServices.removeMessageOfGroup(id);
		}
	}
	
	public void removeAllMessageOfGroupe(Groupe groupe) throws SQLException{
		if(groupe != null){			
			groupServices.removeAllMessageOfGroupe(groupe);
		}
	}
	
	public void deleteGroup(Groupe groupe) throws SQLException {
		if (groupe != null) {
			groupServices.deleteGroup(groupe);
		}
	}

	public ArrayList<User> getListUserOfGroupById(int id) throws SQLException {
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = groupServices.getListUserOfGroupById(id);
		return listUsers;
	}
	
	public ArrayList<User> getListUserOfGroupByName(String name) throws SQLException {
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = groupServices.getListUserOfGroupByName(name);
		return listUsers;
	}
	
	public ArrayList<MessageGroup> getMessageOfGroupe(int id) throws SQLException{
		ArrayList<MessageGroup> listMessage = new ArrayList<MessageGroup>();
		listMessage = groupServices.getMessageOfGroupe(id);
		return listMessage;
	}
	
	public void sendMessageGroup(Message message, Groupe groupe) throws SQLException{
		groupServices.sendMessageGroup(message, groupe);
	}
	
	
	public ArrayList<User> getFreeUserForGroup(Groupe groupe) throws SQLException{
		ArrayList<User> listUsers = new ArrayList<User>();
		listUsers = groupServices.getFreeUserForGroup(groupe);
		return listUsers;
	}

	public boolean nameOfGroupIsGood(String name) {

		if (name == null) {
			return false;
		}
		return true;
	}

}
