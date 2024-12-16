package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dataaccess.DAOrder;
import entities.Expired;
import entities.Order;

public class BOrder {
	DAOrder daOrder;
	BMember bMember;
	BStaff bStaff;
	BBook bBook;
	public BOrder(){
		daOrder = new DAOrder();
		bMember = new BMember();
		bStaff = new BStaff();
		bBook = new BBook();
	}
	public DefaultTableModel getAllExpiredToModel() throws ClassNotFoundException, SQLException{
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Độc Giả");
		model.addColumn("Nhân Viên");
		model.addColumn("Tên Sách");
		model.addColumn("Ngày Cho Mượn");
		model.addColumn("Ngày Hết Hạn");
		model.addColumn("Mô Tả");
		model.addColumn("Số Ngày Quá Hạn");
		List<Expired> orders = daOrder.getAllExpired();
		for(Expired order:orders){
			String[] cells = new String[8];
			cells[0] = String.valueOf(order.getId());
			cells[1] = bMember.getMemberByID(Integer.parseInt(String.valueOf(order.getMember()))).getFullname();
			cells[2] = bStaff.getStaffByID(Integer.parseInt(String.valueOf(order.getStaff()))).getFullname();
			cells[3] = bBook.getBookByID(Integer.parseInt(String.valueOf(order.getBook()))).getTitle();
			cells[4] = String.valueOf(order.getStart_date().toString());
			cells[5] = String.valueOf(String.valueOf(order.getEnd_date().toString()));
			cells[6] = order.getDescription();
			cells[7] = String.valueOf(order.getExpired());
			model.addRow(cells);
		}
		return model;
	}
	public DefaultTableModel getAllOrderToModel() throws NumberFormatException, ClassNotFoundException, SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Độc Giả");
		model.addColumn("Nhân Viên");
		model.addColumn("Tên Sách");
		model.addColumn("Ngày Cho Mượn");
		model.addColumn("Ngày Hết Hạn");
		model.addColumn("Mô Tả");
		List<Order> orders = daOrder.getAllOrder();
		for(Order order:orders){
			String[] cells = new String[8];
			cells[0] = String.valueOf(order.getId());
			cells[1] = bMember.getMemberByID(Integer.parseInt(String.valueOf(order.getMember()))).getFullname();
			cells[2] = bStaff.getStaffByID(Integer.parseInt(String.valueOf(order.getStaff()))).getFullname();
			cells[3] = bBook.getBookByID(Integer.parseInt(String.valueOf(order.getBook()))).getTitle();
			cells[4] = order.getStart_date().toString();
			String edate = String.valueOf(order.getEnd_date());
			if(edate.equals(null))
				cells[5] = "";
			else
				cells[5] = edate;
			cells[6] = order.getDescription();
			model.addRow(cells);
		}
		return model;
	}
	public void insert(Order order) throws ClassNotFoundException, SQLException {
		daOrder.insert(order);
		bBook.subQuantityById(order.getBook());
	}
	public void deleteOrderByID(int choose) throws ClassNotFoundException, SQLException {
		daOrder.deleteOrderByID(choose);   
		bBook.addQuantityById(getOrderById(choose).getBook()); 
	}
	private Order getOrderById(int choose) throws ClassNotFoundException, SQLException {
		return daOrder.getOrderById(choose);
	}
	public void update(Order order) throws ClassNotFoundException, SQLException {
		daOrder.update(order);
	}
	public DefaultTableModel getAllOrderByMemberToModel(int member) throws ClassNotFoundException, SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nhân Viên");
		model.addColumn("Tên Sách");
		model.addColumn("Ngày Cho Mượn");
		model.addColumn("Ngày Hết Hạn");
		model.addColumn("Mô Tả");
		List<Order> orders = daOrder.getAllOrderByMember(member);
		for(Order order:orders){
			String[] cells = new String[6];
			cells[0] = String.valueOf(order.getId());
			cells[1] = bStaff.getStaffByID(Integer.parseInt(String.valueOf(order.getStaff()))).getFullname();
			cells[2] = bBook.getBookByID(Integer.parseInt(String.valueOf(order.getBook()))).getTitle();
			cells[3] = order.getStart_date().toString();
			String edate = String.valueOf(order.getEnd_date());
			if(edate.equals(null))
				cells[4] = "null";
			else
				cells[4] = edate;
			cells[5] = order.getDescription();
			model.addRow(cells);
		}
		return model;
	}
	/*hung lv */
	
	public DefaultTableModel searchOrderManager(String textSearch) throws ClassNotFoundException, SQLException{
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nhân Viên");
		model.addColumn("Tên Sách");
		model.addColumn("Ngày Cho Mượn");
		model.addColumn("Ngày Hết Hạn");
		model.addColumn("Mô Tả");
		List<Order> orders = daOrder.getSearchOrderManager(textSearch);
		for(Order order:orders){
			String[] cells = new String[6];
			cells[0] = String.valueOf(order.getId());
			cells[1] = bStaff.getStaffByID(Integer.parseInt(String.valueOf(order.getStaff()))).getFullname();
			cells[2] = bBook.getBookByID(Integer.parseInt(String.valueOf(order.getBook()))).getTitle();
			cells[3] = order.getStart_date().toString();
			String edate = String.valueOf(order.getEnd_date());
			if(edate.equals(null))
				cells[4] = "null";
			else
				cells[4] = edate;
			cells[5] = order.getDescription();
			model.addRow(cells);
		}
		return model;	}
	
	
	/*end hunglv*/
	
	
	
}
