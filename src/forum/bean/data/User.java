package forum.bean.data;
import java.util.ArrayList;
import java.util.List;

import forum.UnitOfWork.IDomainObject;
import forum.UnitOfWork.Observateur;
import forum.UnitOfWork.Visiteur;

public class User implements IDomainObject{

	
	private String nomCompte;
	private String mail;
	private String nom;
	private String prenom;
	private String role;
	private ArrayList<Hobby> hobbies;
	private ArrayList<User> friends;
	List<Observateur> obs;
	

	public User(String nomCompte,String mail, String nom, String prenom, String role, ArrayList<Hobby> hobbies, ArrayList<User> friends){
		this.nomCompte=nomCompte;
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.role=role;
		this.hobbies = hobbies;
		this.friends = friends;
		this.obs = new ArrayList<Observateur>();
	}
	
	public User(){
		this.obs = new ArrayList<Observateur>();
	}
	
	public String toString(){
		return this.getNomCompte();
	}

	public String getNomCompte() {
		return nomCompte;
	}
	public void setNomCompte(String nomCompte) {
		this.nomCompte = nomCompte;
	}
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
		notifier();
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
		notifier();
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
		notifier();
	}

	public ArrayList<Hobby> getHobbies() {
		return hobbies;
	}
	public void setHobbies(ArrayList<Hobby> hobbies) {
		this.hobbies = hobbies;
		notifier();
	}

	public ArrayList<User> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
		notifier();
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
