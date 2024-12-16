package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dataaccess.DATypeBook;
import entities.TypeOfBook;

public class BTypeOfBook {
	private DATypeBook daTypeBook;
	public BTypeOfBook(){
		daTypeBook = new DATypeBook();
	}
	public DefaultComboBoxModel<String> getAllTypesToModel() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<TypeOfBook> types = daTypeBook.getAllTypes();
		for (TypeOfBook type : types) {
			model.addElement(type.getName());
		}
		return model;
	}
	public TypeOfBook getTypeByID(int id) throws ClassNotFoundException, SQLException{
		return daTypeBook.getTypeByID(id);
	}
}
