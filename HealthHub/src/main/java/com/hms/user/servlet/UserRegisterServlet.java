package com.hms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hms.dao.UserDAO;
import com.hms.db.DBConnection;
import com.hms.entity.User;

@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// ✅ get form data + trim (important)
			String fullName = req.getParameter("fullName").trim();
			String email = req.getParameter("email").trim();
			String password = req.getParameter("password").trim();

			System.out.println("👉 Register Request:");
			System.out.println("Name: " + fullName);
			System.out.println("Email: " + email);

			// ✅ set data to object
			User user = new User(fullName, email, password);

			// ✅ DB connection check
			if (DBConnection.getConn() == null) {
				System.out.println("❌ DB Connection Failed!");
			}

			UserDAO userDAO = new UserDAO(DBConnection.getConn());

			HttpSession session = req.getSession();

			// ✅ call register method
			boolean f = userDAO.userRegister(user);

			if (f) {
				session.setAttribute("successMsg", "Register Successfully ✅");
				resp.sendRedirect("signup.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong ❌");
				resp.sendRedirect("signup.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();

			// 🔥 IMPORTANT: browser pe error dikhao
			resp.setContentType("text/html");
			resp.getWriter().println("<h3 style='color:red;'>ERROR: " + e.getMessage() + "</h3>");
		}
	}
}
