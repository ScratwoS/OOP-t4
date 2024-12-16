package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Categories;

public class DACategories {


	public Categories getCateByID(int id) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM book_categories WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			Categories cate = new Categories(result.getInt(1), result.getString(2), result.getString(3));
			return cate;
		}
		return null;
	}

	public List<Categories> getAllCategories() throws ClassNotFoundException, SQLException {
		List<Categories> cates = new ArrayList<Categories>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM book_categories";
		PreparedStatement stm = con.prepareStatement(query );
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String name = result.getString(2);
			String description = result.getString(3);
			Categories cate = new Categories(id, name, description);
			cates.add(cate);
		}
		return cates;
	}
	
}
