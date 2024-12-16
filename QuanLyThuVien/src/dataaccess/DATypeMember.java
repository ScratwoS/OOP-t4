package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.TypeOfMember;

public class DATypeMember {
	public List<TypeOfMember> getAllTypes() throws ClassNotFoundException, SQLException{
		List<TypeOfMember> types = new ArrayList<TypeOfMember>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name, description FROM member_types";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String name = result.getString(2);
			String description = result.getString(3);
			TypeOfMember type = new TypeOfMember(id, name, description);
			types.add(type);
		}
		return types;
	}
	public TypeOfMember getTypeMemberByID(int id) throws ClassNotFoundException, SQLException{
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, name , description FROM member_types WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			TypeOfMember type = new TypeOfMember(result.getInt(1), result.getString(2), result.getString(3));
			return type;
		}
		return null;
	}
}
