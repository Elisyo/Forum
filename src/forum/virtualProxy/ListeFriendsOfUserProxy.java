package forum.virtualProxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import forum.bean.data.User;
import forum.persistance.dao.UserDAO;

public class ListeFriendsOfUserProxy extends ArrayList<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2313732593525732474L;
	private List<User> inst = null;
	private String name;

	public ListeFriendsOfUserProxy(String name) {
		this.name = name;
	}

	public void initialize() throws SQLException {
		inst = UserDAO.getInstance().getFriendsListOfUser(name);
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
	
	public User get(int i) {
		ensureIsInitialized();
		return inst.get(i);
	}	
	
	public int size(){
		ensureIsInitialized();
		return inst.size();
	}
	
}
