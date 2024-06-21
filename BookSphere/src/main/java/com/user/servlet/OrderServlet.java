package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.DAO.BookOrderDAOImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.Book_Order;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession();

			int id = Integer.parseInt(req.getParameter("id"));

			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String paymentType = req.getParameter("paymentType");

			String fullAdd = address + ", " + landmark + ", " + city + ", " + state + ", " + pincode;

//			System.out.println(name+" "+email+" "+phno+" "+fullAdd+" "+paymentType);

			CartDAOImpl cartDao = new CartDAOImpl(DBConnect.getConn());

			List<Cart> blist = cartDao.getBookByUser(id);
			if (blist.isEmpty()) {
				session.setAttribute("failedMsg", "Your cart is empty !!");
				resp.sendRedirect("checkout.jsp");
			} else {

				BookOrderDAOImpl bookOrderDao = new BookOrderDAOImpl(DBConnect.getConn());

				int i = bookOrderDao.getOrderNo();

				ArrayList<Book_Order> orderList = new ArrayList<Book_Order>();
				Book_Order book_order = null;
				for (Cart c : blist) {
//					System.out.println(c.getBookName()+" "+c.getAuthor()+" "+c.getPrice());
					book_order = new Book_Order();
					i++;
					book_order.setOrderId("BOOK-ORD-00" + i);
					book_order.setUserName(name);
					book_order.setEmail(email);
					book_order.setPhno(phno);
					book_order.setFullAdd(fullAdd);
					book_order.setBookName(c.getBookName());
					book_order.setAuthor(c.getAuthor());
					book_order.setPrice(c.getPrice() + "");
					book_order.setPaymentType(paymentType);

					orderList.add(book_order);

				}

				if ("noSelect".equals(paymentType)) {
					session.setAttribute("failedMsg", "Choose Payment Method");
					resp.sendRedirect("checkout.jsp");
				} else {
					boolean f = bookOrderDao.saveOrder(orderList);
					if (f) {
						resp.sendRedirect("order_success.jsp");
					} else {
						session.setAttribute("failedMsg", "Your order failed");
						resp.sendRedirect("checkout.jsp");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
