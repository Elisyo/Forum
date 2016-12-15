package forum.virtualProxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import forum.bean.data.User;
import forum.persistance.dao.GroupDAO;


public class ListeUserOfGroupProxy extends ArrayList<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4546494325091900762L;
	private List<User> inst = null;
	private int id;

	public ListeUserOfGroupProxy(int id) {
		this.id = id;
	}

	public void initialize() throws SQLException {
		inst = GroupDAO.getInstance().getListUserOfGroupById(id);
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
