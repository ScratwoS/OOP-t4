package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import entities.Member;
import entities.Staff;

public class DAStaff {
//Hàm trả về một danh sách các nhân viên trong thư viện.
	public List<Staff> getAllStaffs() throws ClassNotFoundException, SQLException{
		List<Staff> staffs = new ArrayList<Staff>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, username, password, email, fullname, role, activated, description FROM staff";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String username = result.getString(2);
			String password = result.getString(3);
			String email = result.getString(4);
			String fullname = result.getString(5);
			int role = result.getInt(6);
			int activated = result.getInt(7);
			String description = result.getString(8);
			Staff staff = new Staff(id, role, activated, username, password, email, fullname, description);
			staffs.add(staff);
		}
		return staffs;
	}

	public Staff getStaffByID(int i) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, username, password, email, fullname, role, activated, description FROM staff WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, i);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String username = result.getString(2);
			String password = result.getString(3);
			String email = result.getString(4);
			String fullname = result.getString(5);
			int role = result.getInt(6);
			int activated = result.getInt(7);
			String description = result.getString(8);
			Staff staff = new Staff(id, role, activated, username, password, email, fullname, description);
			return staff;
		}
		return null;
	}

	public Staff getStaffByUsername(String user) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, username, password, email, fullname, role, activated, description FROM staff WHERE username=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, user);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String username = result.getString(2);
			String password = result.getString(3);
			String email = result.getString(4);
			String fullname = result.getString(5);
			int role = result.getInt(6);
			int activated = result.getInt(7);
			String description = result.getString(8);
			Staff staff = new Staff(id, role, activated, username, password, email, fullname, description);
			return staff;
		}
		return null;
	}

	public void deleteStaffById(int id) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasStaff(String staff) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id FROM staff WHERE username=?";
		PreparedStatement stm = con.prepareStatement(query );
		stm.setString(1, staff);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			return true;
		}
		return false;
	}

	public void insertStaff(Staff staff) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "INSERT INTO `staff`(`username`, `password`, `email`, `fullname`, `role`, `activated`, `description`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, staff.getUsername());
		stm.setString(2, staff.getPassword());
		stm.setString(3, staff.getEmail());
		stm.setString(4, staff.getFullname());
		stm.setInt(5, staff.getRole());
		stm.setInt(6, staff.getActivated());
		stm.setString(7, staff.getDescription());
		stm.executeUpdate();
	}

	public void updateInfo(Staff staff) throws SQLException, ClassNotFoundException {
		Connection con = DAConnection.getConnection();
		String query = "UPDATE staff SET email =?, fullname =?,description=? WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, staff.getEmail());
		stm.setString(2, staff.getFullname());
		stm.setString(3, staff.getDescription());
		stm.setInt(4, staff.getId());
		stm.executeUpdate();
	}

	public void updatePassword(Staff staff) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "UPDATE staff SET password = ? WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, staff.getPassword());
		stm.setInt(2, staff.getId());
		stm.executeUpdate();
	}

	public List<Staff> getAllUAStaffs() throws ClassNotFoundException, SQLException {
		List<Staff> staffs = new ArrayList<Staff>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, username, password, email, fullname, role, activated, description FROM staff WHERE activated=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, 0);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String username = result.getString(2);
			String password = result.getString(3);
			String email = result.getString(4);
			String fullname = result.getString(5);
			int role = result.getInt(6);
			int activated = result.getInt(7);
			String description = result.getString(8);
			Staff staff = new Staff(id, role, activated, username, password, email, fullname, description);
			staffs.add(staff);
		}
		return staffs;
	}

	public List<Staff> getAllAStaffs() throws ClassNotFoundException, SQLException {
		List<Staff> staffs = new ArrayList<Staff>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, username, password, email, fullname, role, activated, description FROM staff WHERE activated=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, 1);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String username = result.getString(2);
			String password = result.getString(3);
			String email = result.getString(4);
			String fullname = result.getString(5);
			int role = result.getInt(6);
			int activated = result.getInt(7);
			String description = result.getString(8);
			Staff staff = new Staff(id, role, activated, username, password, email, fullname, description);
			staffs.add(staff);
		}
		return staffs;
	}

	public void updateActive(Staff staff) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "UPDATE staff SET activated = ? WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, staff.getActivated());
		stm.setInt(2, staff.getId());
		stm.executeUpdate();
	}
	public Staff getStaffByName(String string) throws SQLException, ClassNotFoundException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, username, password, email, fullname, role, activated, description FROM staff WHERE username=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, string);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String username = result.getString(2);
			String password = result.getString(3);
			String email = result.getString(4);
			String fullname = result.getString(5);
			int role = result.getInt(6);
			int activated = result.getInt(7);
			String description = result.getString(8);
			Staff staff = new Staff(id, role, activated, username, password, email, fullname, description);
			return staff;
		}
		return null;
	}

}
