package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import dataaccess.DALanguage;
import entities.Language;

public class BLanguage {

	private DALanguage daLang;
	public BLanguage(){
		daLang = new DALanguage();
	}
	public DefaultComboBoxModel<String> getAllLangsToModel() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<Language> langs = daLang.getAllLangs();
		for (Language lang : langs) {
			model.addElement(lang.getName());
		}
		return model;
	}
	public Language getLangByID(int id) throws ClassNotFoundException, SQLException{
		return daLang.getLangByID(id);
	}
}
