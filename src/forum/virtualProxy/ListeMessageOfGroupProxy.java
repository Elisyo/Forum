package forum.virtualProxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import forum.bean.data.MessageGroup;
import forum.bean.data.User;
import forum.persistance.dao.UserDAO;

public class ListeMessageOfGroupProxy  extends ArrayList<MessageGroup> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431660864344036005L;
	private List<MessageGroup> inst = null;
	private int id;

	public ListeMessageOfGroupProxy(int id) {
		this.id = id;
	}

	public void initialize() throws SQLException {
		inst = UserDAO.getInstance().getMessageOfGroupe(id);
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
	
	public MessageGroup get(int i) {
		ensureIsInitialized();
		return inst.get(i);
	}	
	
	public int size(){
		ensureIsInitialized();
		return inst.size();
	}
	
}
