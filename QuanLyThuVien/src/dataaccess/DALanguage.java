package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Language;

public class DALanguage {

	public List<Language> getAllLangs() throws SQLException, ClassNotFoundException {
		List<Language> langs = new ArrayList<Language>();

		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM languages";

		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();

		while (result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			String description = result.getString(3);
			Language lang = new Language(id, name, description);
			langs.add(lang);
		}

		return langs;
	}

	public Language getLangByID(int id) throws SQLException, ClassNotFoundException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM languages WHERE id =?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			Language lang = new Language(result.getInt(1), result.getString(2), result.getString(3));
			return lang;
		}
		return null;
	}

}
