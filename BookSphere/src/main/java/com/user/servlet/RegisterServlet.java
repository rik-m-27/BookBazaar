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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("fname");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			String check = req.getParameter("check");
			
//			System.out.println(name+" "+email+" "+phno+" "+password+" "+check);
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPhno(phno);
			user.setPassword(password);
			
			HttpSession session = req.getSession();
			
			if(check != null) {
				UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
				
				boolean isPossible = dao.checkUser(email);
				if(isPossible) {
					boolean f = dao.userRegister(user);
					
					if(f) {
//						System.out.println("User Register Success..");
						session.setAttribute("succMsg", "Registration Successfully..");
						resp.sendRedirect("register.jsp");
					}else {
//						System.out.println("Something wrong on server..");
						session.setAttribute("failedMsg", "Something wrong on server..");
						resp.sendRedirect("register.jsp");
					}
				}else {
					session.setAttribute("failedMsg", "Email already registered");
					resp.sendRedirect("register.jsp");
				}
			}else {
//				System.out.println("Please check agree terms & conditions.");
				session.setAttribute("failedMsg", "Please check agree terms & conditions..");
				resp.sendRedirect("register.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
