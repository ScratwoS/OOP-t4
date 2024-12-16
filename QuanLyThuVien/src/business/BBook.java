package business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dataaccess.DABook;
import entities.Book;
import entities.Order;

public class BBook {
	private DABook daBook;
	private BLanguage bLang;
	private BTypeOfBook bTypeBook;
	private BCategories bCategories;

	public BBook() {
		daBook = new DABook();
		bLang = new BLanguage();
		bTypeBook = new BTypeOfBook();
		bCategories = new BCategories();
	}

	public DefaultTableModel getAllBookToModel() throws ClassNotFoundException, SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Tên Sách");
		model.addColumn("Tác Giả");
		model.addColumn("Nhà Xuất Bản");
		model.addColumn("Năm Xuất Bản");
		model.addColumn("Giá Tiền");
		model.addColumn("Thể Loại");
		model.addColumn("Kiểu Sách");
		model.addColumn("Link Ảnh");
		model.addColumn("Ngôn Ngữ");
		model.addColumn("Số Lượng");
		model.addColumn("Ghi Chú Khác");
		List<Book> books = daBook.getAllBooks();
		for (Book book : books) {
			String[] cells = new String[12];
			cells[0] = String.valueOf(book.getId());
			cells[1] = book.getTitle();
			cells[2] = book.getAuthor();
			cells[3] = book.getPublisher();
			cells[4] = String.valueOf(book.getPublished_year());
			cells[5] = String.valueOf(book.getPrice());
			cells[6] = bCategories.getCateByID(book.getCategory()).getName();
			cells[7] = bTypeBook.getTypeByID(book.getType()).getName();
			cells[8] = book.getImage();
			cells[9] = bLang.getLangByID(book.getLanguage()).getName();
			cells[10] = String.valueOf(book.getQuantity());
			cells[11] = book.getDescription();
			model.addRow(cells);
		}
		return model;
	}

	public void insert(Book book) throws ClassNotFoundException, SQLException {
		daBook.insert(book);
	}

	public void deleteBookByID(int id) throws ClassNotFoundException, SQLException {
		daBook.deleteBookByID(id);
	}

	public void update(Book book) throws ClassNotFoundException, SQLException {
		daBook.update(book);
	}

	public DefaultComboBoxModel<String> getAllBookToCBModel() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<Book> books = daBook.getAllBooks();
		for (Book book : books) {
			model.addElement(book.getTitle());
		}
		return model;
	}

	public Book getBookByID(int id) throws ClassNotFoundException, SQLException {
		return daBook.getBookByID(id);
	}

	public Book getBookByName(String string) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return daBook.getBookByName(string);
	}

	/* hung lv */
	public DefaultTableModel searchOrderManager(String textSearch) throws ClassNotFoundException, SQLException {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Tên Sách");
		model.addColumn("Tác Giả");
		model.addColumn("Nhà Xuất Bản");
		model.addColumn("Năm Xuất Bản");
		model.addColumn("Giá Tiền");
		model.addColumn("Thể Loại");
		model.addColumn("Kiểu Sách");
		model.addColumn("Link Ảnh");
		model.addColumn("Ngôn Ngữ");
		model.addColumn("Số Lượng");
		model.addColumn("Ghi Chú Khác");
		List<Book> books = daBook.getSearchBook(textSearch);
		for (Book book : books) {
			String[] cells = new String[12];
			cells[0] = String.valueOf(book.getId());
			cells[1] = book.getTitle();
			cells[2] = book.getAuthor();
			cells[3] = book.getPublisher();
			cells[4] = String.valueOf(book.getPublished_year());
			cells[5] = String.valueOf(book.getPrice());
			cells[6] = bCategories.getCateByID(book.getCategory()).getName();
			cells[7] = bTypeBook.getTypeByID(book.getType()).getName();
			cells[8] = book.getImage();
			cells[9] = bLang.getLangByID(book.getLanguage()).getName();
			cells[10] = String.valueOf(book.getQuantity());
			cells[11] = book.getDescription();
			model.addRow(cells);
		}
		return model;
	}
	/* end Hunglv */

	public void subQuantityById(int id) throws ClassNotFoundException, SQLException {
		daBook.subQuantityById(id, getBookByID(id).getQuantity());
	}
	public void addQuantityById(int id) throws ClassNotFoundException, SQLException {
		daBook.addQuantityById(id, getBookByID(id).getQuantity());
	}

}
