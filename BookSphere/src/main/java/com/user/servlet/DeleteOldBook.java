package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete_old_book")
public class DeleteOldBook extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String email = req.getParameter("email");
			BookDAOImpl bookDao = new BookDAOImpl(DBConnect.getConn());
			
			boolean f = bookDao.oldBookDelete(id, email, "Old");
			
			HttpSession session = req.getSession();
			if(f) {
				session.setAttribute("successMsg", "Old Book Delete Successfully!!");
				resp.sendRedirect("old_book.jsp");
			}else {
				session.setAttribute("failedMsg", "Something wrong on server!!");
				resp.sendRedirect("old_book.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
