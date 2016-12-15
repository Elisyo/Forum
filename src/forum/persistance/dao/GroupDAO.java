package forum.persistance.dao;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import forum.bean.data.Groupe;
import forum.bean.data.Message;
import forum.bean.data.MessageGroup;
import forum.bean.data.User;
import forum.services.MaConnection;
import forum.virtualProxy.GroupeProxy;
import forum.virtualProxy.ListeUserOfGroupProxy;
import forum.virtualProxy.MessageGroupeProxy;
import forum.virtualProxy.UserProxy;

public class GroupDAO implements IGroupDAO{
	
	HashMap<Integer, WeakReference<Groupe>> listGroup;
	private static GroupDAO INSTANCE = null;
	Connection con = null;
	

	private GroupDAO() {
		listGroup = new HashMap<Integer, WeakReference<Groupe>>();
		con = MaConnection.getInstance().getConnection();
	}

	public synchronized static GroupDAO getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GroupDAO();
		return INSTANCE;
	}
	
	public ArrayList<Groupe> getListGroupe() throws SQLException {
		ArrayList<Groupe> listGroupe = new ArrayList<Groupe>();

		Statement stmt = null;
		
		final String requete = "SELECT * FROM `group`";
		try {
			 stmt = con.createStatement();
			ResultSet resultat = stmt.executeQuery(requete);
			while (resultat.next()) {	
				GroupeProxy groupe = new GroupeProxy(resultat.getInt(1));
				groupe.add(UnitOfWork.getInstance());
				listGroupe.add(groupe);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		}

		return listGroupe;
	}

	public Groupe getGroupeById(int id) throws SQLException {
		
		Groupe groupe = null;

		PreparedStatement preparedStatement = null;
		final String requete = "select * from `group` where id = ?";
		try {
			preparedStatement = con.prepareStatement(requete);
			
			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			
			
			while (resultat.next()) {
				
				UserProxy moderateur = new UserProxy(resultat.getString(3));
				ListeUserOfGroupProxy listUser = new ListeUserOfGroupProxy(id);
				groupe = new Groupe(resultat.getInt(1), resultat.getString(2), moderateur, listUser, null);
				groupe.add(UnitOfWork.getInstance());
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}

		return groupe;
	}



	public void addUserInGroup(User user, Groupe groupe) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "insert into useringroup values (?,?)";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setString(1, user.getNomCompte());
			preparedStatement.setInt(2, groupe.getIdGroupe());

			preparedStatement.executeUpdate();;

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}
	}

	public void addListUsersInGroup(ArrayList<User> listUsers, Groupe groupe) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "insert into useringroup values (?,?)";
		try {
			preparedStatement = con.prepareStatement(requete);

			for(int i=0; i<listUsers.size(); i++){
				preparedStatement.setString(1, listUsers.get(i).getNomCompte());
				preparedStatement.setInt(2, groupe.getIdGroupe());				
				preparedStatement.executeUpdate();
			}
			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}
	}

	public void deleteGroup(Groupe groupe) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "DELETE FROM `group` WHERE `id` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, groupe.getIdGroupe());

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}
	}

	public void removeUserOfGroup(User user, Groupe groupe) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		final String requete = "DELETE FROM `useringroup` WHERE `nameAccount` = ? and `idGroup` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setString(1, user.getNomCompte());
			preparedStatement.setInt(2, groupe.getIdGroupe());

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}
			
	}

	public ArrayList<User> getListUserOfGroupById(int id) throws SQLException {
		
		ArrayList<User> listUsers = new ArrayList<User>();

		PreparedStatement preparedStatement = null;
		final String requete = "select * from useringroup where idGroup = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				
				UserProxy userProxy = new UserProxy(resultat.getString(1));
				listUsers.add(userProxy);
				
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}

		return listUsers;
	}


	public ArrayList<User> getListUserOfGroupByName(String name) throws SQLException {
		ArrayList<User> listUsers = new ArrayList<User>();

		PreparedStatement preparedStatement = null;
		final String requete = "select * from useringroup where name = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setString(1, name);

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				
				UserProxy userProxy = new UserProxy(resultat.getString(1));
				listUsers.add(userProxy);
				
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}

		return listUsers;
	}

	public ArrayList<MessageGroup> getMessageOfGroupe(int id) throws SQLException {
		ArrayList<MessageGroup> listMessage = new ArrayList<MessageGroup>();

		PreparedStatement preparedStatement = null;
		final String requete = "select id from message where idGroupe = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, id);

			ResultSet resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				MessageGroupeProxy messageProxy = new MessageGroupeProxy(resultat.getInt(1));
				listMessage.add(messageProxy);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}

		return listMessage;
	}

	public void createGroup(String name, User creator) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "INSERT INTO `deleplanque`.`group` (`id`, `name`, `nameUser`) VALUES (NULL, ?, ?)";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, creator.getNomCompte());

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}		
	}

	public Groupe getGroupeByName(String name) throws SQLException {
		Groupe groupe = null;

		PreparedStatement preparedStatement = null;
		final String requete = "select * from `group` where name = ?";
		try {
			preparedStatement = con.prepareStatement(requete);
			
			preparedStatement.setString(1, name);

			ResultSet resultat = preparedStatement.executeQuery();

			
			
			while (resultat.next()) {
				
				UserProxy moderateur = new UserProxy(resultat.getString(3));
				ListeUserOfGroupProxy listUser = new ListeUserOfGroupProxy(resultat.getInt(1));
				groupe = new Groupe(resultat.getInt(1), resultat.getString(2), moderateur, listUser, null);
				groupe.add(UnitOfWork.getInstance());						
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}

		return groupe;
	}

	public void removeAllUsersOfGroup(Groupe groupe) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "DELETE FROM `useringroup` WHERE `idGroup` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, groupe.getIdGroupe());

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}
		
	}

	public void removeMessageOfGroup(int id) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "DELETE FROM `message` WHERE `id` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}		
	}

	public void removeAllMessageOfGroupe(Groupe groupe) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "DELETE FROM `message` WHERE `idGroupe` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, groupe.getIdGroupe());

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}		
	}


	public void removeMessageUserOfGroup(User user, Groupe groupe) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "DELETE FROM `message` WHERE `idGroupe` = ? and `destinataire` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			preparedStatement.setInt(1, groupe.getIdGroupe());
			preparedStatement.setString(2, user.getNomCompte());

			preparedStatement.executeUpdate();

			
		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}		
	}
	
	public void update(Groupe g) throws SQLException {
		getInstance().updateGroup(g);
		System.out.println("Données mise à jour !");
	}

	private void updateGroup(Groupe g) throws SQLException {
		PreparedStatement preparedStatement = null;
		final String requete = "UPDATE `deleplanque`.`group` SET `name` = ?, `nameUser` = ? WHERE `group`.`id` = ?";
		try {
			preparedStatement = con.prepareStatement(requete);

			
			preparedStatement.setString(1, g.getNomGroupe());
			preparedStatement.setString(2, g.getModerator().getNomCompte());
			preparedStatement.setInt(3, g.getIdGroupe());

			preparedStatement.execute();

		} catch (final SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (final SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			preparedStatement.close();
		}
		
	}

	public void sendMessageGroup(Message message, Groupe groupe) {
		System.out.println(message.getContenue());
	}


}
