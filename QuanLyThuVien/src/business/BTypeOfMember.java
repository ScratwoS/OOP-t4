package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dataaccess.DATypeMember;
import entities.TypeOfMember;

public class BTypeOfMember {
	private DATypeMember daTypeMember;
	public BTypeOfMember(){
		daTypeMember = new DATypeMember();
	}
	public DefaultComboBoxModel<String> getAllTypesToModel() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<TypeOfMember> types = daTypeMember.getAllTypes();
		for(TypeOfMember type:types)
			model.addElement(type.getName());
		return model;
	}
	public TypeOfMember getTypeByID(int id) throws ClassNotFoundException, SQLException{
		return daTypeMember.getTypeMemberByID(id);
	}
	
}
