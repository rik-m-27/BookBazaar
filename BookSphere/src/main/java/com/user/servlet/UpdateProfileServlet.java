package com.user.servlet;

import java.io.IOException;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("fname");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setPhno(phno);
			
			HttpSession session = req.getSession();
			
			UserDAOImpl userDao = new UserDAOImpl(DBConnect.getConn());
			boolean f = userDao.checkPassword(id, password);
			
			if(f) {
				boolean f2 = userDao.updateProfile(user);
				if(f2) {
					session.setAttribute("successMsg", "Profile Update Successfully");
					resp.sendRedirect("edit_profile.jsp");
				}else {
					session.setAttribute("failedMsg", "Something wrong on server");
					resp.sendRedirect("edit_profile.jsp");
				}
			}else {
				session.setAttribute("failedMsg", "Your Password is Incorrect");
				resp.sendRedirect("edit_profile.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
