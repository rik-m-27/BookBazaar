package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.Book_Order;

public class BookOrderDAOImpl implements BookOrderDAO{
	
	private Connection conn;
	
	public BookOrderDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public int getOrderNo() {
		int count=0;
		try {
			String sql = "select * from book_order";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count++;
			}
			
		} catch (Exception e) {
			
		}
		return count;
	}
	
	@Override
	public boolean saveOrder(List<Book_Order> blist) {
		boolean f = false;
		
		try {
			String sql = "insert into book_order(order_id, user_name, email, address, phone, book_name, author, price, payment) values(?,?,?,?,?,?,?,?,?)";
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for(Book_Order b : blist) {
				pstmt.setString(1, b.getOrderId());
				pstmt.setString(2, b.getUserName());
				pstmt.setString(3, b.getEmail());
				pstmt.setString(4, b.getFullAdd());
				pstmt.setString(5, b.getPhno());
				pstmt.setString(6, b.getBookName());
				pstmt.setString(7, b.getAuthor());
				pstmt.setString(8, b.getPrice());
				pstmt.setString(9, b.getPaymentType());
				pstmt.addBatch();
			}
			
			int[] count = pstmt.executeBatch();
			conn.commit();
			f = true;
			conn.setAutoCommit(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	
	@Override
	public List<Book_Order> getBook(String email){
		List<Book_Order> blist = new ArrayList<>();
		try {
			Book_Order book_order = null;
			String sql = "select * from book_order where email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				book_order = new Book_Order();
				book_order.setId(rs.getInt(1));
				book_order.setOrderId(rs.getString(2));
				book_order.setUserName(rs.getString(3));
				book_order.setEmail(rs.getString(4));
				book_order.setFullAdd(rs.getString(5));
				book_order.setPhno(rs.getString(6));
				book_order.setBookName(rs.getString(7));
				book_order.setAuthor(rs.getString(8));
				book_order.setPrice(rs.getString(9));
				book_order.setPaymentType(rs.getString(10));
				blist.add(book_order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blist;
	}
	
	@Override
	public List<Book_Order> getAllBook() {
		List<Book_Order> blist = new ArrayList<>();
		try {
			Book_Order book_order = null;
			
			String sql = "SELECT * FROM book_order";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			
			while(rs.next()) {
				book_order = new Book_Order();
				book_order.setId(rs.getInt(1));
				book_order.setOrderId(rs.getString(2));
				book_order.setUserName(rs.getString(3));
				book_order.setEmail(rs.getString(4));
				book_order.setFullAdd(rs.getString(5));
				book_order.setPhno(rs.getString(6));
				book_order.setBookName(rs.getString(7));
				book_order.setAuthor(rs.getString(8));
				book_order.setPrice(rs.getString(9));
				book_order.setPaymentType(rs.getString(10));
				blist.add(book_order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blist;
	}
	
}
