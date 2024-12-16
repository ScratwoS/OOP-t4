package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import entities.Member;

public class DAMember {
//Hàm trả về tất cả các thành viên trong thư viện
	public List<Member> getAllMember() throws ClassNotFoundException, SQLException{
		List<Member> members = new ArrayList<Member>();
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, fullname, type, expired_date, description FROM members";
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		while(result.next()){
			int id = result.getInt(1);
			String fullname = result.getString(2);
			int type = result.getInt(3);
			Date expired_date = result.getDate(4);
			String description = result.getString(5);
			Member member = new Member(id, type, fullname, description, expired_date);
			members.add(member);
		}
		return members;
	}

	public Member getMemberByID(int i) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, fullname, type, expired_date, description FROM members WHERE id =?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, i);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String fullname = result.getString(2);
			int type = result.getInt(3);
			Date expired_date = result.getDate(4);
			String description = result.getString(5);
			Member member = new Member(id, type, fullname, description, expired_date);
			return member;
		}
		return null;
	}

	public void insert(Member member) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = DAConnection.getConnection();
		String query = "INSERT INTO `members`(`fullname`, `type`, `expired_date`, `description`) VALUES (?,?,?,?)";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, member.getFullname());
		stm.setInt(2,member.getType());
		stm.setDate(3,new Date(member.getExpired_date().getTime()));
		stm.setString(4,member.getDescription());
		stm.executeUpdate();
	}
	public void deleteMemberByID(int id) throws ClassNotFoundException, SQLException{
		Connection con = DAConnection.getConnection();
		String query = "DELETE FROM `members` WHERE id=?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id);
		stm.executeUpdate();
	}
	public void update(Member member) throws ClassNotFoundException, SQLException{
		Connection con = DAConnection.getConnection();
		String query = "UPDATE `members` SET `fullname`=?,`type`=?,`expired_date`=?,`description`=? WHERE id =?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, member.getFullname());
		stm.setInt(2, member.getType());
		if(member.getType()!=2)
			stm.setDate(3,new Date(member.getExpired_date().getTime()));
		else
			stm.setDate(3, null);
		stm.setString(4, member.getDescription());
		stm.setInt(5, member.getId());
		stm.executeUpdate();
	}

		public Member getMemberByName(String string) throws ClassNotFoundException, SQLException {
		Connection con = DAConnection.getConnection();
		String query = "SELECT id, fullname, type, expired_date, description FROM members WHERE fullname =?";
		PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, string);
		ResultSet result = stm.executeQuery();
		if(result.next()){
			int id = result.getInt(1);
			String fullname = result.getString(2);
			int type = result.getInt(3);
			Date expired_date = result.getDate(4);
			String description = result.getString(5);
			Member member = new Member(id, type, fullname, description, expired_date);
			return member;
		}
		return null;
	}
}
