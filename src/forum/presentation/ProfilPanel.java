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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import forum.action.HobbyAction;
import forum.action.UserAction;
import forum.bean.data.Hobby;
import forum.bean.data.User;

public class ProfilPanel extends JPanel implements ListSelectionListener{
	
	private User u;
	ArrayList<Hobby> hobbiesAction = HobbyAction.getInstance().getOtherHobbies();
	
	private Hobby hobbySelected;
	private boolean boolHobbySelected = false;
	
	JList<Hobby> hobbies = new JList<Hobby>();
	DefaultListModel<Hobby> lmodel = new DefaultListModel<Hobby>();
	
	JList<Hobby> allHobbies = new JList<Hobby>();
	DefaultListModel<Hobby> l2model = new DefaultListModel<Hobby>();
	
	ProfilPanel(User u){
		this.u=u;
		
		lmodel();
		l2model();		
		
		GridLayout gl = new GridLayout(2, 1);
		this.setLayout(gl);
		this.add(infos());
		this.add(hobbies());
	}
	
	private void lmodel(){
		lmodel.clear();
		for (int i = 0; i < u.getHobbies().size(); i++) {
			lmodel.addElement(u.getHobbies().get(i));
		}
	}
	
	private void l2model() {
		l2model.clear();
		for (int i = 0; i < hobbiesAction.size(); i++) {
			l2model.addElement(hobbiesAction.get(i));
		}
	}
	
	public JPanel infos(){
		JPanel result = new JPanel();
		GridLayout gl1 = new GridLayout(2, 1);
		result.setLayout(gl1);
		JPanel jp = new JPanel();
		GridLayout gl = new GridLayout(5, 2);
		jp.setLayout(gl);
		jp.add(new JLabel("Prenom : "));jp.add(new JTextField(u.getPrenom()));
		jp.add(new JLabel("Nom : "));jp.add(new JTextField(u.getNom()));
		jp.add(new JLabel("Mail : "));jp.add(new JTextField(u.getMail()));
		jp.add(new JLabel("Mot de passe : "));jp.add(new JPasswordField());
		jp.add(new JLabel("Confirmer le mdp : "));jp.add(new JPasswordField());
		result.add(jp);
		JButton save = new JButton("Sauvegarder");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Sauvegarder un user");
				UserAction.getInstance();
			}
		});
		result.add(save);
		return result;
	}
	
	public JPanel hobbies(){
		JPanel result = new JPanel();
		GridLayout gl = new GridLayout(1, 2);
		result.setLayout(gl);
		result.add(userHobbies());
		result.add(allHobbies());
		
		return result;
	}
	
	public JPanel userHobbies() {
		JPanel jpanel = new JPanel();
		if (u.getHobbies().size() > 0) {			
			hobbies.setModel(lmodel);
			// il faut changer ce addListSelectionListener(null)
			hobbies.getSelectionModel().addListSelectionListener(this);
			hobbies.setPreferredSize(new Dimension(150, 300));
			JScrollPane js = new JScrollPane();
			js.getViewport().setView(hobbies);

			JButton delete = new JButton("Supprimer");
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(hobbySelected!=null && boolHobbySelected==true){
						System.out.println("Supprimer un hobby au user : " + hobbySelected.getNom());
						try {
							HobbyAction.getInstance().removeHobby(hobbySelected);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						hobbySelected=null;
					}
				}
			});
			
			jpanel.add(js);
			jpanel.add(delete);
		} else {
			jpanel.add(new JLabel("Vous n'avez pas 'hobby'"));
		}
		return jpanel;
	}
	
	public JPanel allHobbies() {
		JPanel jpanel = new JPanel();
		if (hobbiesAction.size() > 0) {
			JPanel jcenter = new JPanel();

			allHobbies.setModel(l2model);
			allHobbies.getSelectionModel().addListSelectionListener(this);
			allHobbies.setPreferredSize(new Dimension(150, 300));
			JScrollPane js = new JScrollPane();
			js.getViewport().setView(allHobbies);
			jcenter.add(js);

			jpanel.add(jcenter);
			JButton add = new JButton("Ajouter");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(hobbySelected!=null && boolHobbySelected==false){
						System.out.println("Ajouter un hobby au user : " + hobbySelected.getNom());
						hobbySelected=null;
					}
				}
			});
			jpanel.add(add);
		} else {
			jpanel.add(new JLabel("Il n'y a plus de hobby disponible"));
		}
		return jpanel;
	}

	public void valueChanged(ListSelectionEvent e) {
		int debutIndex = hobbies.getSelectionModel().getMinSelectionIndex();
		int finIndex = hobbies.getSelectionModel().getMaxSelectionIndex();
		
		int debutIndexAll = allHobbies.getSelectionModel().getMinSelectionIndex();
		int finIndexAll = allHobbies.getSelectionModel().getMaxSelectionIndex();
		
		if(e.getValueIsAdjusting()){
			if (!hobbies.getSelectionModel().isSelectionEmpty()) {
				String hob = "";
				for (int i = debutIndex; i <= finIndex; i++) {
					hob += hobbies.getModel().getElementAt(i).getNom();

				}
				try {
					hobbySelected = HobbyAction.getInstance().getHobbyByName(hob);
					boolHobbySelected = true;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				hobbies.getSelectionModel().clearSelection();
			}else if(!allHobbies.getSelectionModel().isSelectionEmpty()) {
				String hob = "";
				for (int i = debutIndexAll; i <= finIndexAll; i++) {
					hob += allHobbies.getModel().getElementAt(i).getNom();
				}
				try {
					hobbySelected = HobbyAction.getInstance().getHobbyByName(hob);
					boolHobbySelected = false;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				allHobbies.getSelectionModel().clearSelection();
			}
		}
	}
	
}
