package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.TypeOfBook;

public class DATypeBook {

	public List<TypeOfBook> getAllTypes() throws ClassNotFoundException, SQLException {
		List<TypeOfBook> types = new ArrayList<TypeOfBook>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM book_types";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			TypeOfBook type = new TypeOfBook(result.getInt(1), result.getString(2), result.getString(3));
			types.add(type);
		}
		return types;
	}

	public TypeOfBook getTypeByID(int id) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM book_types WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			TypeOfBook type = new TypeOfBook(result.getInt(1), result.getString(2), result.getString(3));
			return type;
		}
		return null;
		
	}

}
