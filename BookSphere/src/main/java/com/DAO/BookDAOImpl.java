package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.BookDtls;

public class BookDAOImpl implements BookDAO {

	private Connection conn;

	public BookDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean addBooks(BookDtls b) {
		boolean f = false;
		try {
			String sql = "insert into book_dtls(bookname, author, price, bookCategory, status, photo, email) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getBookCategory());
			ps.setString(5, b.getStatus());
			ps.setString(6, b.getPhotoName());
			ps.setString(7, b.getEmail());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<BookDtls> getAllBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls b = null;
		try {
			String sql = "select * from book_dtls";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BookDtls getBookById(int id) {
		BookDtls b = null;
		try {
			String sql = "select * from book_dtls where bookId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean updateEditBooks(BookDtls b) {
		boolean f = false;
		try {
			String sql = "update book_dtls set bookname=?, author=?, price=?, status=? where bookId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setString(3, b.getPrice());
			ps.setString(4, b.getStatus());
			ps.setInt(5, b.getBookId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean deleteBooks(int id) {
		boolean f = false;

		try {
			String sql = "delete from book_dtls where bookId=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {

		}

		return f;
	}

	@Override
	public List<BookDtls> getNewBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls b = null;

		try {
			String sql = "Select * from book_dtls where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "New");
			pstmt.setString(2, "Active");
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next() && i++ < 4) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getRecentBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls b = null;

		try {
			String sql = "Select * from book_dtls where status=? order by bookId DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Active");
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next() && i++ < 4) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getOldBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls b = null;

		try {
			String sql = "Select * from book_dtls where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Old");
			pstmt.setString(2, "Active");
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next() && i++ < 4) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getAllRecentBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls b = null;

		try {
			String sql = "Select * from book_dtls where status=? order by bookId DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Active");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookDtls> getAllNewBooks() {
		List<BookDtls> list = new ArrayList<>();

		BookDtls b = null;

		try {
			String sql = "Select * from book_dtls where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "New");
			pstmt.setString(2, "Active");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<BookDtls> getAllOldBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls b = null;

		try {
			String sql = "Select * from book_dtls where bookCategory=? and status=? order by bookId DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Old");
			pstmt.setString(2, "Active");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BookDtls> getBookByUserSell(String email, String cate){
		
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls b = null;
		
		try {
			String sql = "Select * from book_dtls where bookCategory = ? and email = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setString(2, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean oldBookDelete(int id, String email, String cate) {
		boolean f = false;
		
		try {
			String sql = "delete from book_dtls where bookId=? and bookCategory=? and email=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, cate);
			pstmt.setString(3, email);
			
			int i = pstmt.executeUpdate();
			
			if(i==1){
				f=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	@Override
	public List<BookDtls> getBookBySearch(String searchName) {
		List<BookDtls> list = new ArrayList<BookDtls>();
		BookDtls b = null;
		
		try {
			String sql = "Select * from book_dtls where bookname like ? or author like ? or bookCategory like ? and status = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchName+"%");
			pstmt.setString(2, "%"+searchName+"%");
			pstmt.setString(3, "%"+searchName+"%");
			pstmt.setString(4, "Active");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				b = new BookDtls();
				b.setBookId(rs.getInt(1));
				b.setBookName(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setPrice(rs.getString(4));
				b.setBookCategory(rs.getString(5));
				b.setStatus(rs.getString(6));
				b.setPhotoName(rs.getString(7));
				b.setEmail(rs.getString(8));
				list.add(b);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
