package business;

import java.sql.SQLException;
import java.util.List;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import dataaccess.DAStatics;
import entities.Statics;

public class BStatics {
	private DAStatics daStatics;
	private BCategories bcategory;
	private BTypeOfBook btype;
	public BStatics() {
		daStatics = new DAStatics();
		bcategory = new BCategories();
		btype = new BTypeOfBook();
	}

	public PieDataset createDatasetCategory() throws ClassNotFoundException, SQLException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		List<Statics> statics = daStatics.getDatasetCategory();
		for (Statics s : statics) {
			String key = bcategory.getCateByID(s.getId()).getName();
			double value = s.getPercent();
			dataset.setValue(key, value);
		}
		return dataset;
	}
	public PieDataset createDatasetType() throws ClassNotFoundException, SQLException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		List<Statics> statics = daStatics.getDatasetType();
		for (Statics s : statics) {
			String key = btype.getTypeByID(s.getId()).getName();
			double value = s.getPercent();
			dataset.setValue(key, value);
		}
		return dataset;
	}
}
