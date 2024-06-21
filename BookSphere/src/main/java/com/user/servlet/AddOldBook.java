package com.user.servlet;

import java.io.File;
import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/add_old_books")
@MultipartConfig
/**
 * indicating that instances of the Servlet expect requests that confirm to the
 * multipart/form-data MIME type.
 * 
 * Servlets annotated with MultipartConfig may retrieve the
 * jakarta.servlet.http.Part components of a given multipart/form-data request by
 * calling getPartor getParts.
 */
public class AddOldBook extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("price");
			String categories = "Old";
			String status = "Active";
			Part part = req.getPart("bimg");
			String fileName = part.getSubmittedFileName();
			
			String userEmail = req.getParameter("userEmail");

			BookDtls b = new BookDtls(bookName, author, price, categories, status, fileName, userEmail);
			
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			
			boolean f = dao.addBooks(b);
			
			HttpSession session = req.getSession();
			 if(f) {
				 String path = getServletContext().getRealPath("")+"book";
				
				 File file = new File(path);
				 
				 part.write(path + File.separator + fileName);
				 
				 session.setAttribute("successMsg", "Book Add Successfully");
				 resp.sendRedirect("sell_book.jsp");
			 }else {
				 session.setAttribute("failedMsg", "Something wrong on server");
				 resp.sendRedirect("sell_book.jsp");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
