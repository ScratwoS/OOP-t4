package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import dataaccess.DAMember;
import entities.Member;
import entities.Order;

public class BMember {
	private DAMember daMember;
	private BTypeOfMember bType;
	public BMember(){
		bType = new BTypeOfMember();
		daMember = new DAMember();
	}
	public DefaultComboBoxModel<String> getAllMemberToModel() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		List<Member> members = daMember.getAllMember();
		for(Member member: members){
			model.addElement(member.getFullname());
		}
		return model;
	}
	public Member getMemberByID(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return daMember.getMemberByID(id);
	}
	public void insert(Member member) throws ClassNotFoundException, SQLException{
		daMember.insert(member);
	}
	public void update(Member member) throws ClassNotFoundException, SQLException{
		daMember.update(member);
	}
	public void deleteMemberByID(int id) throws ClassNotFoundException, SQLException{
		daMember.deleteMemberByID(id);
	}
	public DefaultTableModel getAllMember() throws ClassNotFoundException, SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Chức Vụ");
		model.addColumn("Họ và Tên");
		model.addColumn("Mô Tả");
		model.addColumn("Ngày Hết Hạn");
		List<Member> members = daMember.getAllMember();
		for(Member member:members){
			String[] cells = new String[5];
			cells[0] = String.valueOf(member.getId());
			cells[1] = bType.getTypeByID(member.getType()).getName();
			cells[2] = member.getFullname();
			cells[3] = member.getDescription();
			String edate = String.valueOf(member.getExpired_date());
			if(edate.equals(null))
				cells[4] = "";
			else
				cells[4] = edate;
			model.addRow(cells);
		}
		return model;
	}
	public Member getMemberByName(String name) throws ClassNotFoundException, SQLException {
		return daMember.getMemberByName(name);
	}
	
}
