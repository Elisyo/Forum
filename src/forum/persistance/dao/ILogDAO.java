package forum.persistance.dao;

import java.sql.SQLException;

public interface ILogDAO {

	public void inscription(String userName, String mail, String pass, String nom, String prenom) throws SQLException;
	public boolean connexion(String userName, String pass) throws SQLException;
}
