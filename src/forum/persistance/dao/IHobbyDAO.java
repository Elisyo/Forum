package forum.persistance.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import forum.bean.data.Hobby;

public interface IHobbyDAO {

	public void removeHobby(Hobby hobby) throws SQLException;

	public void createHobby(String categorie, String nom) throws SQLException;

	public ArrayList<Hobby> getAllHobbies();

	public Hobby findHobbyByID(int id) throws SQLException;

	public ArrayList<String> getAllCategorie() throws SQLException;

	public ArrayList<String> getHobbyByCategorie(String categorie) throws SQLException;

	public Hobby getHobbyByName(String name) throws SQLException;

	public ArrayList<Hobby> getOtherHobbies();

	public ArrayList<String> getOtherCategorie();

	public ArrayList<String> getOtherHobbyByCategorie(String categorie) throws SQLException;
}
