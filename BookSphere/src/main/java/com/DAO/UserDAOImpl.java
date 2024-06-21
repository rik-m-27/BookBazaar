package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDAOImpl implements UserDAO{
	
	private Connection conn;
	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public boolean userRegister(User user) {
		boolean f = false;
		try {
			String sql = "insert into user(name,email,phno,password) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhno());
			ps.setString(4, user.getPassword());
			
			int i = ps.executeUpdate();
			if(i==1){
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public User login(String email, String password) {
		User user = null;
		try {
			String sql = "select * from user where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhno(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setAddress(rs.getString(6));
				user.setLandmark(rs.getString(7));
				user.setCity(rs.getString(8));
				user.setState(rs.getString(9));
				user.setPincode(rs.getString(10));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public boolean checkPassword(int id, String password) {
//		try {
//			String sql = "select password from user where id=?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			ResultSet rs = pstmt.executeQuery();
//			if(rs.next()) {
//				return password.equals(rs.getString(1));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
		
		boolean f = false;
		try {
			String sql = "select * from user where id=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	@Override
	public boolean updateProfile(User user) {
		boolean f = false;
		try {
			String sql = "UPDATE user SET name=?, email=?, phno=? WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhno());
			pstmt.setInt(4, user.getId());
			
			int i = pstmt.executeUpdate();
			
			if(i==1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	@Override
	public boolean checkUser(String email) {
		boolean f = true;
		try {
			String sql = "select * from user where email=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				f = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
