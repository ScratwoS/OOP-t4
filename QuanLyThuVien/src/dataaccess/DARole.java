package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Role;

public class DARole {
	public List<Role> getAllRoles() throws ClassNotFoundException, SQLException{
		Connection con = DAConnection.getConnection();
		List<Role> roles = new ArrayList<Role>();
		String query = "SELECT id, name, description FROM roles";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			Role role = new Role(result.getInt(1), result.getString(2), result.getString(3));
			roles.add(role);
		}
		return roles;
	}
}
