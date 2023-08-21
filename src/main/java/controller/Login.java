package controller;

import java.io.IOException;

import dao.MyDao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Mapping the Url 
@WebServlet("/login")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
//		Verify if email exists
		MyDao dao=new MyDao();
		if (email.equals("admin@jsp.com")&&password.equals("admin")) {
			resp.getWriter().print("<h1>Admin Logged In Successfully</h1>");
//			This is logic to send to next page
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		} else {
			Customer customer=dao.fetchByEmail(email);
			if (customer==null) {
	//			This is logic to direct/send to login page
				resp.getWriter().print("<h1 style='color:red'>Invalid Email</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			} else {
				if (password.equals(customer.getPassword())) {
	//				This is logic to send to customer home page 
					resp.getWriter().print("<h1 style='color:green'>Login Successfully</h1>");
					req.getRequestDispatcher("CustomerHome.html").include(req, resp);
				}
				else {
	//				This is logic to direct/send to login page
	//				If response should be both text and html
	//				resp.setContentType("text/html");
					resp.getWriter().print("<h1 style='color:red'>Invalid Password</h1>");
					req.getRequestDispatcher("login.html").include(req, resp);
				}
			}
		}
	}
}
