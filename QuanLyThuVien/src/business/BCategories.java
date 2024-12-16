package business;


import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dataaccess.DACategories;
import entities.Categories;

public class BCategories {
	private DACategories daCategories;
	public BCategories(){
		daCategories = new DACategories();
	}
	public DefaultComboBoxModel<String> getAllCategoriesToModel() throws ClassNotFoundException, SQLException{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<Categories> cates = daCategories.getAllCategories();
		for(Categories cate: cates){
			model.addElement(cate.getName());
		}
		return model;
	}
	public Categories getCateByID(int id) throws ClassNotFoundException, SQLException{
		return daCategories.getCateByID(id);
	}
	

}
