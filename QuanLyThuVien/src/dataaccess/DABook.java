package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.BCategories;
import business.BLanguage;
import business.BTypeOfBook;
import entities.Book;

public class DABook {

	public List<Book> getAllBooks() throws ClassNotFoundException, SQLException {
		List<Book> books = new ArrayList<Book>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, title, author, publisher, published_year, price, category, type, image, language, quantity, description FROM books";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String title = result.getString(2);
			String author = result.getString(3);
			String publisher = result.getString(4);
			int published_year = result.getInt(5);
			double price = result.getDouble(6);
			int category = result.getInt(7);
			int type = result.getInt(8);
			String image = result.getString(9);
			int language = result.getInt(10);
			int quantity = result.getInt(11);
			String description = result.getString(12);
			Book book = new Book(id, published_year, quantity, category, type, language, price, title, author, publisher, image, description);
			books.add(book);
		}
		return books;
	}
//
//	public void insert(Book book) throws ClassNotFoundException, SQLException {
//		Connection con = DAConnection.getConnection();
//		String query = "INSERT INTO books(title, author, publisher, published_year, price, category, type, image, language, quantity, description) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//		PreparedStatement stm = con.prepareStatement(query);
//		stm.setString(1, book.getTitle());
//		stm.setString(2, book.getAuthor());
//		stm.setString(3, book.getPublisher());
//		stm.setInt(4, book.getPublished_year());
//		stm.setDouble(5, book.getPrice());
//		stm.setInt(6, book.getCategory());
//		stm.setInt(7,book.getType());
//		stm.setString(8, book.getImage());
//		stm.setInt(9, book.getLanguage());
//		stm.setInt(10, book.getQuantity());
//		stm.setString(11, book.getDescription());
//		stm.executeUpdate();
//	}
	/*Hung lv*/
	public void insert(Book book) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		BCategories bCategories=new BCategories();
		BTypeOfBook bTypeOfBook=new BTypeOfBook();
		BLanguage bLanguage=new BLanguage();
		String query = "INSERT INTO books(title, author, publisher, published_year, price, category, type, image, language, quantity, description,descriptioninfo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, book.getTitle());
		stm.setString(2, book.getAuthor());
		stm.setString(3, book.getPublisher());
		stm.setInt(4, book.getPublished_year());
		stm.setDouble(5, book.getPrice());
		stm.setInt(6, book.getCategory());
		stm.setInt(7,book.getType());
		stm.setString(8, book.getImage());
		stm.setInt(9, book.getLanguage());
		stm.setInt(10, book.getQuantity());
		stm.setString(11, book.getDescription());
		String tmp=bCategories.getCateByID(book.getCategory()).toString();
		tmp+=bLanguage.getLangByID(book.getLanguage()).toString();
		tmp+=bTypeOfBook.getTypeByID(book.getType()).toString();
		tmp+=book.toString();
		stm.setString(12, tmp);
		stm.executeUpdate();
	}
/*end Hung lv*/

	public void deleteBookByID(int id) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "UPDATE books SET truonggiday=? WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, 0);
		stm.setInt(2, id);
		stm.executeUpdate();
	}

//	public void update(Book book) throws ClassNotFoundException, SQLException {
//		Connection con = DAConnection.getConnection();
//		String query = "UPDATE books SET title=?, author=?, publisher=?, published_year=?, price=?, category=?, type=?, image=?, language=?, quantity=?, description=? WHERE id=?";
//		PreparedStatement stm = con.prepareStatement(query);
//		stm.setInt(12, book.getId());
//		stm.setString(1, book.getTitle());
//		stm.setString(2, book.getAuthor());
//		stm.setString(3, book.getPublisher());
//		stm.setInt(4, book.getPublished_year());
//		stm.setDouble(5, book.getPrice());
//		stm.setInt(6, book.getCategory());
//		stm.setInt(7,book.getType());
//		stm.setString(8, book.getImage());
//		stm.setInt(9, book.getLanguage());
//		stm.setInt(10, book.getQuantity());
//		stm.setString(11, book.getDescription());
//		stm.executeUpdate();
//	}/*hung lv */
	public void update(Book book) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		BCategories bCategories=new BCategories();
		BTypeOfBook bTypeOfBook=new BTypeOfBook();
		BLanguage bLanguage=new BLanguage();
		String query = "UPDATE books SET title=?, author=?, publisher=?, published_year=?, price=?, category=?, type=?, image=?, language=?, quantity=?, description=?,descriptioninfo=? WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(13, book.getId());
		stm.setString(1, book.getTitle());
		stm.setString(2, book.getAuthor());
		stm.setString(3, book.getPublisher());
		stm.setInt(4, book.getPublished_year());
		stm.setDouble(5, book.getPrice());
		stm.setInt(6, book.getCategory());
		stm.setInt(7,book.getType());
		stm.setString(8, book.getImage());
		stm.setInt(9, book.getLanguage());
		stm.setInt(10, book.getQuantity());
		stm.setString(11, book.getDescription());
		String tmp=bCategories.getCateByID(book.getCategory()).toString();
		tmp+=bLanguage.getLangByID(book.getLanguage()).toString();
		tmp+=bTypeOfBook.getTypeByID(book.getType()).toString();
		tmp+=book.toString();
		
		stm.setString(12, tmp);
		stm.executeUpdate();
	}

	/*end hung lv*/

	public Book getBookByID(int i) throws SQLException, ClassNotFoundException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id,title, author, publisher, published_year, price, category, type, image, language, quantity, description FROM books WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, i);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String title = result.getString(2);
			String author = result.getString(3);
			String publisher = result.getString(4);
			int published_year = result.getInt(5);
			double price = result.getDouble(6);
			int category = result.getInt(7);
			int type = result.getInt(8);
			String image = result.getString(9);
			int language = result.getInt(10);
			int quantity = result.getInt(11);
			String description = result.getString(12);
			Book book = new Book(id,published_year, quantity, category, type, language, price, title, author, publisher, image, description);
			return book;
		}
		return null;
	}

	public Book getBookByName(String string) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, title, author, publisher, published_year, price, category, type, image, language, quantity, description FROM books WHERE title=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, string);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String title = result.getString(2);
			String author = result.getString(3);
			String publisher = result.getString(4);
			int published_year = result.getInt(5);
			double price = result.getDouble(6);
			int category = result.getInt(7);
			int type = result.getInt(8);
			String image = result.getString(9);
			int language = result.getInt(10);
			int quantity = result.getInt(11);
			String description = result.getString(12);
			Book book = new Book(id, published_year, quantity, category, type, language, price, title, author, publisher, image, description);
			return book;
		}
		return null;
	}
	/*Hung lv*/
	public List<Book> getSearchBook(String textSearch) throws ClassNotFoundException, SQLException {
		List<Book> books = new ArrayList<Book>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, title, author, publisher, published_year, price, category, type, image, language, quantity, description FROM books WHERE descriptioninfo LIKE '%"+textSearch+"%'";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String title = result.getString(2);
			String author = result.getString(3);
			String publisher = result.getString(4);
			int published_year = result.getInt(5);
			double price = result.getDouble(6);
			int category = result.getInt(7);
			int type = result.getInt(8);
			String image = result.getString(9);
			int language = result.getInt(10);
			int quantity = result.getInt(11);
			String description = result.getString(12);
			Book book = new Book(id, published_year, quantity, category, type, language, price, title, author, publisher, image, description);
			books.add(book);
		}
		return books;
	}
	/*Hung lv end*/
	public void subQuantityById(int id,int quantity) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "UPDATE books SET quantity = ? WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, quantity-1);
		stm.setInt(2, id);
		stm.executeUpdate();
	}
	public void addQuantityById(int id, int quantity) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "UPDATE books SET quantity = ? WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, quantity+1);
		stm.setInt(2, id);
		stm.executeUpdate();
	}

}
