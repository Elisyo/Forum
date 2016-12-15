package forum.virtualProxy;

import java.sql.SQLException;

import forum.bean.data.Hobby;
import forum.persistance.dao.HobbyDAO;

public class HobbyProxy extends Hobby{

	private Hobby inst = null;
	private int id;

	public HobbyProxy(int id) {
		this.id = id;
	}

	public void initialize() throws SQLException {
		inst = HobbyDAO.getInstance().findHobbyByID(id);
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

	public int getId() {
		ensureIsInitialized();
		return inst.getId();
	}
	
	public String getType(){
		ensureIsInitialized();
		return inst.getType();
	}
	
	public String getNom(){
		ensureIsInitialized();
		return inst.getNom();
	}
}
