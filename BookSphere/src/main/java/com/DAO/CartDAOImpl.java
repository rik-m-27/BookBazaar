package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.BookDtls;
import com.entity.Cart;

public class CartDAOImpl implements CartDAO{
	
	private Connection conn;
	
	public CartDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean addCart(Cart c) {
		boolean f = false;
		try {
			String sql = "insert into cart(bid, uid, bookName, author, price, totalPrice) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getBid());
			pstmt.setInt(2, c.getUserid());
			pstmt.setString(3, c.getBookName());
			pstmt.setString(4, c.getAuthor());
			pstmt.setDouble(5, c.getPrice());
			pstmt.setDouble(6, c.getTotalPrice());
			
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
	public List<Cart> getBookByUser(int userId) {
		List<Cart> list = new ArrayList<>();
		double totalPrice = 0;
		Cart c = null;
		try {
			String sql = "select * from cart where uid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,userId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				c = new Cart();
				c.setCid(rs.getInt(1));
				c.setBid(rs.getInt(2));
				c.setUserid(rs.getInt(3));
				c.setBookName(rs.getString(4));
				c.setAuthor(rs.getString(5));
				c.setPrice(rs.getDouble(6));
				totalPrice = totalPrice + rs.getDouble(7);
				c.setTotalPrice(totalPrice);	//setPrice korechilo..
				
				list.add(c);
			}
			
		} catch (Exception e) {
			
		}
		
		return list;
	}
	
	@Override
	public boolean deleteBook(int bid, int uid, int cid) {
		boolean f = false;
		
		try {
			String sql = "delete from cart where cid=? and bid=? and uid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setInt(2, bid);
			pstmt.setInt(3, uid);
			int i = pstmt.executeUpdate();
			if(i==1) {
				f=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}
