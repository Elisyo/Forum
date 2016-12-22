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

import forum.action.GroupAction;
import forum.action.UserAction;
import forum.bean.data.Notification;
import forum.bean.data.User;

public class FriendPanel extends JPanel implements ListSelectionListener{

	User u;
	User userSelected = null;
	boolean boolUserSelected = false;
	
	JPanel ManageFriends = new JPanel();
	JPanel Friends = new JPanel();
	JPanel OtherUsers = new JPanel();
	JPanel Notif = new JPanel();
	
	ArrayList<User> friendsList = new ArrayList<User>();
	JList<User> friends = new JList<User>();
	DefaultListModel<User> lmodel = new DefaultListModel<User>();
	
	ArrayList<User> usersList = new ArrayList<User>();
	JList<User> users = new JList<User>();
	DefaultListModel<User> l2model = new DefaultListModel<User>();
	
	ArrayList<Notification> friendNotif = new ArrayList<Notification>();
	JList<Notification> JfriendNotif = new JList<Notification>();
	DefaultListModel<Notification> l3model = new DefaultListModel<Notification>();
	
	FriendPanel(User u) throws SQLException{
		this.u=u;
				
		GridLayout gl = new GridLayout(2, 1);
		this.setLayout(gl);
		ManageFriends = manageFriends();
		this.add(ManageFriends);
		Notif = notif();
		this.add(Notif);
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
	
	private void l3model(){
		l3model.clear();
		try {
			friendNotif=UserAction.getInstance();
			System.out.println(friendNotif.size());
			for (int i = 0; i < friendNotif.size(); i++) {
				l3model.addElement(friendNotif.get(i));
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
		int debutIndex = friends.getSelectionModel().getMinSelectionIndex();
		int finIndex = friends.getSelectionModel().getMaxSelectionIndex();
		
		int debutIndexMembers = users.getSelectionModel().getMinSelectionIndex();
		int finIndexMembers = users.getSelectionModel().getMaxSelectionIndex();
		
		int debutIndexAll = JfriendNotif.getSelectionModel().getMinSelectionIndex();
		int finIndexAll = JfriendNotif.getSelectionModel().getMaxSelectionIndex();
		
		if(e.getValueIsAdjusting()){
			if (!friends.getSelectionModel().isSelectionEmpty()) {
				String hob = "";
				for (int i = debutIndex; i <= finIndex; i++) {
					hob += friends.getModel().getElementAt(i).getNomCompte();
				}
				int idGroup = Integer.parseInt(hob);
				try {
					userSelected = UserAction.getInstance().getUserByName(hob);
					boolUserSelected = true;
					lmodel();
					l2model();
					l3model();/*
					usersForGroup.remove(otherUsers);
					usersForGroup.remove(Users);
					otherUsers=otherUsers();
					Users = Users();
					usersForGroup.add(Users);
					usersForGroup.add(otherUsers);
					usersForGroup.repaint();
					usersForGroup.revalidate();	*/			
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				friends.getSelectionModel().clearSelection();
			}else if(!users.getSelectionModel().isSelectionEmpty()) {
				String hob = "";
				for (int i = debutIndexMembers; i <= finIndexMembers; i++) {
					hob += users.getModel().getElementAt(i).getNomCompte();
				}
				try {
					userSelected = UserAction.getInstance().getUserByName(hob);
					boolUserSelected = false;
					lmodel();
					l2model();
					l3model();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				users.getSelectionModel().clearSelection();
			}else if(!JfriendNotif.getSelectionModel().isSelectionEmpty()) {
				String hob = "";
				for (int i = debutIndexAll; i <= finIndexAll; i++) {
					hob += JfriendNotif.getModel().getElementAt(i).getNomCompte();
				}
				try {
					userSelected = UserAction.getInstance().getUserByName(hob);
					lmodel();
					l2model();
					l3model();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JfriendNotif.getSelectionModel().clearSelection();
			}
		}
	}
}
