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

import forum.action.NotificationAction;
import forum.action.UserAction;
import forum.bean.data.RequestNotification;
import forum.bean.data.User;

public class FriendPanel extends JPanel implements ListSelectionListener{

	User u;
	User userSelected = null;
	RequestNotification requestSelected = null;
	boolean boolUserSelected = false;
	
	JPanel JFriendPanel = new JPanel();
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
	
	ArrayList<RequestNotification> friendNotif = new ArrayList<RequestNotification>();
	JList<RequestNotification> JfriendNotif = new JList<RequestNotification>();
	DefaultListModel<RequestNotification> l3model = new DefaultListModel<RequestNotification>();
	
	FriendPanel(User u) throws SQLException{
		this.u=u;
				
		GridLayout gl = new GridLayout(2, 1);
		JFriendPanel.setLayout(gl);
		ManageFriends = manageFriends();
		JFriendPanel.add(ManageFriends);
		Notif = notif();
		JFriendPanel.add(Notif);
		
		this.add(JFriendPanel);
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
		friendNotif=UserAction.getInstance().getRequestNotification();
		System.out.println(friendNotif.size());
		for (int i = 0; i < friendNotif.size(); i++) {
			l3model.addElement(friendNotif.get(i));
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
					lmodel();
					l2model();
					l3model();
					JFriendPanel.remove(ManageFriends);
					JFriendPanel.remove(Notif);
					ManageFriends = manageFriends();
					Notif = notif();
					JFriendPanel.add(ManageFriends);
					JFriendPanel.add(Notif);
					JFriendPanel.repaint();
					JFriendPanel.revalidate();
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
			users.getSelectionModel().addListSelectionListener(this);
			users.setPreferredSize(new Dimension(150, 300));
			JScrollPane js = new JScrollPane();
			js.getViewport().setView(users);

			JButton add = new JButton("Ajouter");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Ajouter un ami");
					try {
						UserAction.getInstance().sendFriendRequest(userSelected);
						lmodel();
						l2model();
						l3model();
						JFriendPanel.remove(ManageFriends);
						JFriendPanel.remove(Notif);
						ManageFriends = manageFriends();
						Notif = notif();
						JFriendPanel.add(ManageFriends);
						JFriendPanel.add(Notif);
						JFriendPanel.repaint();
						JFriendPanel.revalidate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
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
		l3model();
		if (friendNotif.size() > 0) {			
			JfriendNotif.setModel(l3model);
			JfriendNotif.getSelectionModel().addListSelectionListener(this);
			JfriendNotif.setPreferredSize(new Dimension(150, 300));
			JScrollPane js = new JScrollPane();
			js.getViewport().setView(JfriendNotif);

			JButton add = new JButton("Ajouter");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Ajouter un ami");
					try {
						UserAction.getInstance().acceptFriendRequest(requestSelected.getReceveur());
						lmodel();
						l2model();
						l3model();
						JFriendPanel.remove(ManageFriends);
						JFriendPanel.remove(Notif);
						ManageFriends = manageFriends();
						Notif = notif();
						JFriendPanel.add(ManageFriends);
						JFriendPanel.add(Notif);
						JFriendPanel.repaint();
						JFriendPanel.revalidate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			JButton delete = new JButton("Supprimer");
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Supprimer une demande");
					try {
						UserAction.getInstance().declineFriendRequest(requestSelected.getReceveur());
						lmodel();
						l2model();
						l3model();
						JFriendPanel.remove(ManageFriends);
						JFriendPanel.remove(Notif);
						ManageFriends = manageFriends();
						Notif = notif();
						JFriendPanel.add(ManageFriends);
						JFriendPanel.add(Notif);
						JFriendPanel.repaint();
						JFriendPanel.revalidate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			result.add(js);
			result.add(add);
		} else {
			result.add(new JLabel("Vous ne trouvez pas les autres users"));
		}
		
		return result;
	}
	
	public static void refresh(){
		
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
				try {
					userSelected = UserAction.getInstance().getUserByName(hob);
					boolUserSelected = true;
					lmodel();
					l2model();
					l3model();
					JFriendPanel.remove(ManageFriends);
					JFriendPanel.remove(Notif);
					ManageFriends = manageFriends();
					Notif = notif();
					JFriendPanel.add(ManageFriends);
					JFriendPanel.add(Notif);
					JFriendPanel.repaint();
					JFriendPanel.revalidate();
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
					JFriendPanel.remove(ManageFriends);
					JFriendPanel.remove(Notif);
					ManageFriends = manageFriends();
					Notif = notif();
					JFriendPanel.add(ManageFriends);
					JFriendPanel.add(Notif);
					JFriendPanel.repaint();
					JFriendPanel.revalidate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				users.getSelectionModel().clearSelection();
			}else if(!JfriendNotif.getSelectionModel().isSelectionEmpty()) {
				String hob = "";
				for (int i = debutIndexAll; i <= finIndexAll; i++) {
					hob += JfriendNotif.getModel().getElementAt(i).getId();
				}
				int idRequest = Integer.parseInt(hob);
				try {
					requestSelected = NotificationAction.getInstance().getRequestNotificationByid(idRequest);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lmodel();
				l2model();
				l3model();
				JFriendPanel.remove(ManageFriends);
				JFriendPanel.remove(Notif);
				ManageFriends = manageFriends();
				Notif = notif();
				JFriendPanel.add(ManageFriends);
				JFriendPanel.add(Notif);
				JFriendPanel.repaint();
				JFriendPanel.revalidate();
				JfriendNotif.getSelectionModel().clearSelection();
			}
		}
	}
}