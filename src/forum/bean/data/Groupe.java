package forum.bean.data;

import java.util.ArrayList;
import java.util.List;

import forum.UnitOfWork.IDomainObject;
import forum.UnitOfWork.Observateur;
import forum.UnitOfWork.Visiteur;

public class Groupe implements IDomainObject{

	private User moderator;
	private String nomGroupe;
	private List<User> users;
	private int idGroupe;
	private List<MessageGroup> listMessage;
	List<Observateur> obs;

	public Groupe(int idGroupe, String nomGroupe, User moderator, ArrayList<User> users, List<MessageGroup> listMessage) {
		this.idGroupe = idGroupe;
		this.moderator = moderator;
		this.users = users;
		this.nomGroupe = nomGroupe;
		this.listMessage = listMessage;
		this.obs = new ArrayList<Observateur>();
	}
	
	public Groupe() {
		this.obs = new ArrayList<Observateur>();
	}
	
	public String toString(){
		return this.getNomGroupe();
	}

	public List<MessageGroup> getListMessage() {
		return listMessage;
	}

	public void setListMessage(List<MessageGroup> listMessage) {
		this.listMessage = listMessage;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
		notifier();
	}

	public void setModerator(User moderator) {
		this.moderator = moderator;
		notifier();
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}


	public User getModerator() {
		return moderator;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
		notifier();
	}


	public int getIdGroupe() {
		return idGroupe;
	}

	public void accepter(Visiteur v) {
		v.visiter(this);
	}
	
	public void add(Observateur o) {
		obs.add(o);
	}

	public void notifier() {
		for (Observateur o : obs)
			o.action(this);
	}
}
