package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import dataaccess.DAStaff;
import entities.Order;
import entities.Staff;

public class BStaff {
	DAStaff daStaff;
	public BStaff(){
		daStaff = new DAStaff();
	}
	public void insertUser(Staff staff) throws ClassNotFoundException, SQLException{
		daStaff.insertStaff(staff);
	}
	public boolean hasUser(String staff) throws ClassNotFoundException, SQLException{
		return daStaff.hasStaff(staff);
	}
	public DefaultTableModel getAllUserToModel() throws ClassNotFoundException, SQLException{

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Username");
		model.addColumn("Password");
		model.addColumn("Email");
		model.addColumn("Họ và Tên");
		model.addColumn("Vai Trò");
		model.addColumn("Tình Trạng Kích Hoạt");
		model.addColumn("Mô Tả");
		List<Staff> staffs = daStaff.getAllStaffs();
		
		for (Staff staff : staffs) {
			String[] cells = new String[8];
			cells[0] = String.valueOf(staff.getId());
			cells[1] = staff.getUsername();
			cells[2] = staff.getPassword();
			cells[3] = staff.getEmail();
			cells[4] = staff.getFullname();
			if(staff.getRole()==1)
				cells[5] = "Admin";
			else if(staff.getRole()==1)
				cells[5] = "Quản Lý";
			else
				cells[5] = "Nhân Viên";
			if(staff.getActivated()==1)
				cells[6] = "Đã kích hoạt";
			else
				cells[6] = "Chưa kích hoạt";
			cells[7] = staff.getDescription();
			model.addRow(cells);
		}
		return model;
	}

	public Staff getStaffByID(int id) throws ClassNotFoundException, SQLException {
		return daStaff.getStaffByID(id);
	}

	public Staff getStaffByUsername(String user) throws ClassNotFoundException, SQLException {
		return daStaff.getStaffByUsername(user);
		
	}
	public void deleteStaffById(int id) throws ClassNotFoundException, SQLException {
		daStaff.deleteStaffById(id);
	}
	public DefaultComboBoxModel<String> getAllStaffToModel() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<Staff> staffs = daStaff.getAllStaffs();
		for(Staff staff: staffs){
			model.addElement(staff.getFullname());
		}
		return model;
	}
	public void updateInfo(Staff staff) throws ClassNotFoundException, SQLException {
		daStaff.updateInfo(staff);
	}
	public void updatePassword(Staff staff) throws ClassNotFoundException, SQLException {
		daStaff.updatePassword(staff);
	}
	public DefaultTableModel getAllUAUserToModel() throws ClassNotFoundException, SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Username");
		model.addColumn("Password");
		model.addColumn("Email");
		model.addColumn("Họ và Tên");
		model.addColumn("Vai Trò");
		model.addColumn("Tình Trạng Kích Hoạt");
		model.addColumn("Mô Tả");
		List<Staff> staffs = daStaff.getAllUAStaffs();
		
		for (Staff staff : staffs) {
			String[] cells = new String[8];
			cells[0] = String.valueOf(staff.getId());
			cells[1] = staff.getUsername();
			cells[2] = String.valueOf(staff.getPassword());
			cells[3] = staff.getEmail();
			cells[4] = staff.getFullname();
			if(staff.getRole()==1)
				cells[5] = "Admin";
			else if(staff.getRole()==1)
				cells[5] = "Quản Lý";
			else
				cells[5] = "Nhân Viên";
			if(staff.getActivated()==1)
				cells[6] = "Đã kích hoạt";
			else
				cells[6] = "Chưa kích hoạt";
			cells[7] = staff.getDescription();
			model.addRow(cells);
		}
		return model;
	}
	public DefaultTableModel getAllAUserToModel() throws ClassNotFoundException, SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Username");
		model.addColumn("Password");
		model.addColumn("Email");
		model.addColumn("Họ và Tên");
		model.addColumn("Vai Trò");
		model.addColumn("Tình Trạng Kích Hoạt");
		model.addColumn("Mô Tả");
		List<Staff> staffs = daStaff.getAllAStaffs();
		
		for (Staff staff : staffs) {
			String[] cells = new String[8];
			cells[0] = String.valueOf(staff.getId());
			cells[1] = staff.getUsername();
			cells[2] = String.valueOf(staff.getPassword());
			cells[3] = staff.getEmail();
			cells[4] = staff.getFullname();
			if(staff.getRole()==1)
				cells[5] = "Admin";
			else if(staff.getRole()==1)
				cells[5] = "Quản Lý";
			else
				cells[5] = "Nhân Viên";
			if(staff.getActivated()==1)
				cells[6] = "Đã kích hoạt";
			else
				cells[6] = "Chưa kích hoạt";
			cells[7] = staff.getDescription();
			model.addRow(cells);
		}
		return model;
	}
	public void updateActive(Staff staff) throws ClassNotFoundException, SQLException {
		daStaff.updateActive(staff);
	}
	public Staff getStaffByName(String string) throws ClassNotFoundException, SQLException {
		return daStaff.getStaffByName(string);
	}
}
