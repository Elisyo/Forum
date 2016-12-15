package forum.services;

import java.sql.SQLException;

import forum.persistance.dao.ILogDAO;
import forum.utils.MyFactory;

public class LogServices implements ILogServices {

	private static LogServices INSTANCE = null;

	private LogServices() {
	}

	public synchronized static LogServices getInstance() {
		if (INSTANCE == null)
			INSTANCE = new LogServices();
		return INSTANCE;
	}


	public boolean connexion(String userName, String pass) {

		boolean connexion = false;
		final ILogDAO logDAO = (ILogDAO) MyFactory.getInstance(ILogDAO.class);
		try {
			connexion = logDAO.connexion(userName, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connexion;
	}


	public void inscription(String userName, String mail, String pass,
			String nom, String prenom) {
		final ILogDAO logDAO = (ILogDAO) MyFactory.getInstance(ILogDAO.class);
		try {
			logDAO.inscription(userName, mail, pass, nom, prenom);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
