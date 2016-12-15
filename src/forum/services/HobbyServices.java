package forum.services;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Hobby;
import forum.persistance.dao.IHobbyDAO;
import forum.utils.MyFactory;

public class HobbyServices implements IHobbyServices{
	
	private static HobbyServices INSTANCE = null;
	final IHobbyDAO hobbyDAO = (IHobbyDAO) MyFactory.getInstance(IHobbyDAO.class);

	private HobbyServices() {
	}

	public synchronized static HobbyServices getInstance() {
		if (INSTANCE == null)
			INSTANCE = new HobbyServices();
		return INSTANCE;
	}


	public void removeHobby(Hobby hobby) throws SQLException {
		hobbyDAO.removeHobby(hobby);
	}

	public void createHobby(String categorie, String nom) throws SQLException {
		hobbyDAO.createHobby(categorie, nom);
	}

	public ArrayList<Hobby> getAllHobbies() {
		ArrayList<Hobby> listHobbies =  new ArrayList<Hobby>();
		listHobbies = hobbyDAO.getAllHobbies();
		return listHobbies;
	}

	public ArrayList<String> getAllCategorie() throws SQLException {
		ArrayList<String> listCategories = new ArrayList<String>();
		listCategories = hobbyDAO.getAllCategorie();
		return listCategories;
	}

	public ArrayList<String> getHobbyByCategorie(String categorie) throws SQLException {
		ArrayList<String> listHobby = new ArrayList<String>();
		listHobby = hobbyDAO.getHobbyByCategorie(categorie);
		return listHobby;
	}

	public Hobby getHobbyByName(String name) throws SQLException {
		Hobby hobby = new Hobby();
		hobby = hobbyDAO.getHobbyByName(name);
		return hobby;
	}

	public ArrayList<Hobby> getOtherHobbies() {
		ArrayList<Hobby> listHobbies =  new ArrayList<Hobby>();
		listHobbies = hobbyDAO.getOtherHobbies();
		return listHobbies;
	}

	public ArrayList<String> getOtherCategorie() {
		ArrayList<String> listCategories = new ArrayList<String>();
		listCategories = hobbyDAO.getOtherCategorie();
		return listCategories;
	}

	public ArrayList<String> getOtherHobbyByCategorie(String categorie) throws SQLException {
		ArrayList<String> listHobby = new ArrayList<String>();
		listHobby = hobbyDAO.getOtherHobbyByCategorie(categorie);
		return listHobby;
	}

}
