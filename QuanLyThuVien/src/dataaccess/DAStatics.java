package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Statics;

public class DAStatics {

	public List<Statics> getDatasetCategory() throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		List<Statics> statics = new ArrayList<Statics>();
		String query = "SELECT `category`, SUM(`quantity`) as soluong FROM `books`GROUP BY `category` HAVING soluong>0";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			Statics s = new Statics();
			s.setId(Integer.parseInt(result.getString(1)));
			s.setPercent(result.getDouble(2));
			statics.add(s);
		}
		return statics;
	}

	public List<Statics> getDatasetType() throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		List<Statics> statics = new ArrayList<Statics>();
		String query = "SELECT `type`, SUM(`quantity`) as soluong FROM `books`GROUP BY `type` HAVING soluong>0";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			Statics s = new Statics();
			s.setId(Integer.parseInt(result.getString(1)));
			s.setPercent(result.getDouble(2));
			statics.add(s);
		}
		return statics;
	}

}
