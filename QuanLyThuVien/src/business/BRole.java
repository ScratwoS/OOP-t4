package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dataaccess.DARole;
import entities.Role;

public class BRole {
	private DARole daRole;
	public BRole(){
		daRole = new DARole();
	}
	public DefaultComboBoxModel<String> getAllRolesToModel() throws ClassNotFoundException, SQLException{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<Role> roles = daRole.getAllRoles();
		for(Role role : roles){
			model.addElement(role.getName());
		}
		return model;
	}
}
