package forum.virtualProxy;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Hobby;
import forum.persistance.dao.UserDAO;

public class ListeHobbiesOfUserProxy extends ArrayList<Hobby>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -121076460165573455L;

	private ArrayList<Hobby> inst = null;
	private String name;

	public ListeHobbiesOfUserProxy(String name) {
		this.name = name;
	}

	public void initialize() throws SQLException {
		inst = UserDAO.getInstance().getHobbiesListOfUser(name);
	}

	void ensureIsInitialized() {
		if (inst == null) {
			try {
				this.initialize();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Hobby get(int i) {
		ensureIsInitialized();
		return inst.get(i);
	}	
	
	public int size(){
		ensureIsInitialized();
		return inst.size();
	}
	

}
