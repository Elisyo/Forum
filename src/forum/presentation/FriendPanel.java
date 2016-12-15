package forum.presentation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import forum.action.UserAction;
import forum.bean.data.User;

public class FriendPanel extends JPanel implements ListSelectionListener{

	User u;
	
	JPanel ManageFriends = new JPanel();
	JPanel Friends = new JPanel();
	JPanel OtherUsers = new JPanel();
	
	ArrayList<User> friendsList = new ArrayList<User>();
	JList<User> friends = new JList<User>();
	DefaultListModel<User> lmodel = new DefaultListModel<User>();
	
	ArrayList<User> usersList = new ArrayList<User>();
	JList<User> users = new JList<User>();
	DefaultListModel<User> l2model = new DefaultListModel<User>();
	
	FriendPanel(User u) throws SQLException{
		this.u=u;
		
		System.out.println("test");
		
		GridLayout gl = new GridLayout(2, 1);
		this.setLayout(gl);
		ManageFriends = manageFriends();
		this.add(ManageFriends);
		this.add(notif());
	}
	
	private void lmodel(){
		lmodel.clear();
		try {
			friendsList=UserAction.getInstance().getFriendsListOfUser(u.getNomCompte());
			System.out.println(friendsList.size());
			for (int i = 0; i < friendsList.size(); i++) {
				lmodel.addElement(friendsList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void l2model(){
		l2model.clear();
		try {
			usersList=UserAction.getInstance().getOtherUser();
			System.out.println(usersList.size());
			for (int i = 0; i < usersList.size(); i++) {
				l2model.addElement(usersList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private JPanel manageFriends(){
		JPanel result = new JPanel();
		
		GridLayout gl = new GridLayout(1, 2);
		result.setLayout(gl);
		Friends = friends();
		result.add(Friends);
		OtherUsers = otherUsers();
		result.add(OtherUsers);
		
		return result;
	}
	
	private JPanel friends(){
		JPanel result = new JPanel();
		
		lmodel();
		
		GridLayout gl = new GridLayout(2, 1);
		result.setLayout(gl);
		
		if (friendsList.size() > 0) {			
			friends.setModel(lmodel);
			friends.getSelectionModel().addListSelectionListener(this);
			friends.setPreferredSize(new Dimension(150, 300));
			JScrollPane js = new JScrollPane();
			js.getViewport().setView(friends);

			JButton delete = new JButton("Supprimer");
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Supprimer un ami");
					// refresh les listes
				}
			});
			
			result.add(js);
			result.add(delete);
		} else {
			result.add(new JLabel("Vous n'avez pas d'ami"));
		}
		
		return result;
	}
	
	private JPanel otherUsers(){
		JPanel result = new JPanel();
		
		l2model();
		
		GridLayout gl = new GridLayout(2, 1);
		result.setLayout(gl);
		
		if (usersList.size() > 0) {			
			users.setModel(l2model);
			// il faut changer ce addListSelectionListener(null)
			users.getSelectionModel().addListSelectionListener(this);
			users.setPreferredSize(new Dimension(150, 300));
			JScrollPane js = new JScrollPane();
			js.getViewport().setView(users);

			JButton add = new JButton("Ajouter");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Ajouter un ami");
					// refresh les listes
				}
			});
			
			result.add(js);
			result.add(add);
		} else {
			result.add(new JLabel("Vous ne trouvez pas les autres users"));
		}
		
		return result;
	}
	
	private JPanel notif(){
		JPanel result = new JPanel();
		
		result.add(new JLabel("Notifications"));
		
		return result;
	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
