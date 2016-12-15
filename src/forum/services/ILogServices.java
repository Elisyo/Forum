package forum.services;

public interface ILogServices {
	
	public void inscription(String userName, String mail, String pass, String nom, String prenom);
	public boolean connexion(String userName, String pass);

}
