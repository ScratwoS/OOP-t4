package dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.BBook;
import business.BMember;
import business.BStaff;
import entities.Expired;
import entities.Order;
import entities.Staff;

public class DAOrder {
	// Hàm trả về danh sách tất cả các yêu cầu mượn sách
	public List<Order> getAllOrder() throws ClassNotFoundException, SQLException {
		List<Order> orders = new ArrayList<Order>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, member, staff, book, start_date, end_date, description FROM orders";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			int member = result.getInt(2);
			int staff = result.getInt(3);
			int book = result.getInt(4);
			Date start_date = result.getDate(5);
			Date end_date = result.getDate(6);
			String description = result.getString(7);
			Order order = new Order(id, member, staff, book, start_date, end_date, description);
			orders.add(order);
		}
		return orders;
	}

	public List<Expired> getAllExpired() throws ClassNotFoundException, SQLException {
		List<Expired> orders = new ArrayList<Expired>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, member, staff, book, start_date, end_date, description, datediff(CURRENT_TIMESTAMP,end_date) as quahan FROM orders WHERE end_date is NOT null";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while (result.next()) {
			int expired = result.getInt(8);
			if (expired > 0) {
				int id = result.getInt(1);
				int member = result.getInt(2);
				int staff = result.getInt(3);
				int book = result.getInt(4);
				Date start_date = result.getDate(5);
				Date end_date = result.getDate(6);
				String description = result.getString(7);

				Expired order = new Expired(id, member, staff, book, expired, start_date, end_date, description);
				orders.add(order);
			}
		}
		return orders;
	}

	// public void insert(Order order) throws ClassNotFoundException,
	// SQLException {
	// Connection con = DAConnection.getConnection();
	// String query = "INSERT INTO `orders`(`member`, `staff`, `book`,
	// `start_date`, `end_date`, `description`) VALUES (?,?,?,?,?,?)";
	// PreparedStatement stm = con.prepareStatement(query);
	// stm.setInt(1, order.getMember());
	// stm.setInt(2, order.getStaff());
	// stm.setInt(3, order.getBook());
	// stm.setDate(4, new Date(order.getStart_date().getTime()));
	// stm.setDate(5, new Date(order.getEnd_date().getTime()));
	// stm.setString(6, order.getDescription());
	// stm.executeUpdate();
	// }
	/* Hung lv */
	public void insert(Order order) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		BMember bMember = new BMember();
		BStaff bStaff = new BStaff();
		BBook bBook = new BBook();
		String query = "INSERT INTO `orders`(`member`, `staff`, `book`, `start_date`, `end_date`, `description`,`descriptioninfo`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, order.getMember());
		stm.setInt(2, order.getStaff());
		stm.setInt(3, order.getBook());
		stm.setDate(4, new Date(order.getStart_date().getTime()));
		stm.setDate(5, new Date(order.getEnd_date().getTime()));
		stm.setString(6, order.getDescription());
		String tmp = bMember.getMemberByID(order.getMember()).toString();
		String tmp2 = bStaff.getStaffByID(order.getStaff()).toString();
		String tmp3 = bBook.getBookByID(order.getBook()).toString();
		stm.setString(7, tmp + tmp2 + tmp3 + (new Date(order.getStart_date().getTime())).toString()
				+ (new Date(order.getEnd_date().getTime())).toString());
		stm.executeUpdate();

	}
	/* End hunglv */

	public void deleteOrderByID(int choose) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "DELETE FROM orders WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, choose);
		stm.executeUpdate();
	}

	// public void update(Order order) throws ClassNotFoundException,
	// SQLException {
	// Connection con = DAConnection.getConnection();
	// String query = "UPDATE `orders`
	// SET`member`=?,`staff`=?,`book`=?,`start_date`=?,`end_date`=?,`description`=?
	// WHERE id=?";
	// PreparedStatement stm = con.prepareStatement(query);
	// stm.setInt(1, order.getMember());
	// stm.setInt(2, order.getStaff());
	// stm.setInt(3, order.getBook());
	// stm.setDate(4, new Date(order.getStart_date().getTime()));
	// stm.setDate(5, new Date(order.getEnd_date().getTime()));
	// stm.setString(6, order.getDescription());
	// stm.setInt(7, order.getId());
	// stm.executeUpdate();
	// }
	/* Hung lv */
	public void update(Order order) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		BMember bMember = new BMember();
		BStaff bStaff = new BStaff();
		BBook bBook = new BBook();
		String query = "UPDATE `orders` SET`member`=?,`staff`=?,`book`=?,`start_date`=?,`end_date`=?,`description`=?,`descriptioninfo`=? WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, order.getMember());
		stm.setInt(2, order.getStaff());
		stm.setInt(3, order.getBook());
		stm.setDate(4, new Date(order.getStart_date().getTime()));
		stm.setDate(5, new Date(order.getEnd_date().getTime()));
		stm.setString(6, order.getDescription());
		String tmp = bMember.getMemberByID(order.getMember()).toString();
		String tmp2 = bStaff.getStaffByID(order.getStaff()).toString();
		String tmp3 = bBook.getBookByID(order.getBook()).toString();
		stm.setString(7, tmp + tmp2 + tmp3 + (new Date(order.getStart_date().getTime())).toString()
				+ (new Date(order.getEnd_date().getTime())).toString());
		stm.setInt(8, order.getId());
		stm.executeUpdate();
	}

	/* end hunglv */
	public List<Order> getAllOrderByMember(int m) throws ClassNotFoundException, SQLException {
		List<Order> orders = new ArrayList<Order>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, member, staff, book, start_date, end_date, description FROM orders WHERE member =?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, m);
		ResultSet result = stm.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			int member = result.getInt(2);
			int staff = result.getInt(3);
			int book = result.getInt(4);
			Date start_date = result.getDate(5);
			Date end_date = result.getDate(6);
			String description = result.getString(7);
			Order order = new Order(id, member, staff, book, start_date, end_date, description);
			orders.add(order);
		}
		return orders;
	}

	/* hung lv */
	public List<Order> getSearchOrderManager(String textSearch) throws ClassNotFoundException, SQLException {
		List<Order> orders = new ArrayList<Order>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, member, staff, book, start_date, end_date, description FROM orders WHERE descriptioninfo LIKE '%"
				+ textSearch + "%'";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			int member = result.getInt(2);
			int staff = result.getInt(3);
			int book = result.getInt(4);
			Date start_date = result.getDate(5);
			Date end_date = result.getDate(6);
			String description = result.getString(7);
			Order order = new Order(id, member, staff, book, start_date, end_date, description);
			orders.add(order);
		}
		return orders;
	}
	/* hunglv end */

	public Order getOrderById(int choose) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, member, staff, book, start_date, end_date, description FROM orders WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, choose);
		ResultSet result = stm.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			int member = result.getInt(2);
			int staff = result.getInt(3);
			int book = result.getInt(4);
			Date start_date = result.getDate(5);
			Date end_date = result.getDate(6);
			String description = result.getString(7);
			Order order = new Order(id, member, staff, book, start_date, end_date, description);
			return order;
		}
		return null;
	}

}
