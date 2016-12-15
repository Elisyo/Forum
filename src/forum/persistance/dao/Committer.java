package forum.persistance.dao;

import java.sql.SQLException;

import forum.UnitOfWork.Visiteur;
import forum.bean.data.Groupe;
import forum.bean.data.User;

public class Committer extends Visiteur {


	@Override
	public void visiter(User u) {
		 try {
			UserDAO.getInstance().update(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visiter(Groupe g) {
		try {
			GroupDAO.getInstance().update(g);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
	
