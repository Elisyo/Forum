package forum.persistance.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Groupe;
import forum.bean.data.Message;
import forum.bean.data.MessageGroup;
import forum.bean.data.User;

public interface IGroupDAO {
	
	public void createGroup(String name, User creator) throws SQLException;

	public void addUserInGroup(User user, Groupe groupe) throws SQLException;

	public void addListUsersInGroup(ArrayList<User> listUsers, Groupe groupe) throws SQLException;

	public void deleteGroup(Groupe groupe) throws SQLException;

	public void removeUserOfGroup(User user, Groupe groupe) throws SQLException;

	public ArrayList<User> getListUserOfGroupById(int id) throws SQLException;

	public ArrayList<User> getListUserOfGroupByName(String name) throws SQLException;

	public ArrayList<Groupe> getListGroupe() throws SQLException;
	
	public Groupe getGroupeById(int id) throws SQLException;
	
	public Groupe getGroupeByName(String name) throws SQLException;

	public ArrayList<MessageGroup> getMessageOfGroupe(int id) throws SQLException;

	public void removeAllUsersOfGroup(Groupe groupe) throws SQLException;

	public void removeMessageOfGroup(int id) throws SQLException;

	public void removeAllMessageOfGroupe(Groupe groupe) throws SQLException;

	public void removeMessageUserOfGroup(User user, Groupe groupe) throws SQLException;

	public void sendMessageGroup(Message message, Groupe groupe);

	public ArrayList<User> getFreeUserForGroup(Groupe groupe) throws SQLException;

}
