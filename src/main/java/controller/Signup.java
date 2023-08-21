package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

//import java.time.Period;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MyDao;

// This is to map the action URL to this class (should be same as action and case-sensitive)
@WebServlet("/signup")
// To receive image we need to use this enctype
@MultipartConfig
public class Signup extends HttpServlet
{
	@Override
	// When there is form and we want data to be secured so doPost()
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Logic to receive data from front end
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String country = req.getParameter("country");
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		int age = Period.between(dob, LocalDate.now()).getYears();
		//Logic to Receive image and convert to byte[]
		Part pic = req.getPart("picture");
		byte[] picture=null;
		picture=new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		System.out.println(fullname);
		System.out.println(mobile);
		System.out.println(email);
		System.out.println(country);
		System.out.println(password);
		System.out.println(gender);
		System.out.println(picture);
		System.out.println(dob);
		
		MyDao dao=new MyDao();
//		Logic to verify email and mobile number is not repeated 
		if (dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile)==null) {
//			Loading values inside Object
			Customer customer=new Customer();
			customer.setFullname(fullname);
			customer.setEmail(email);
			customer.setMobile(mobile);
			customer.setGender(gender);
			customer.setCountry(country);
			customer.setDob(dob);
			customer.setPicture(picture);
			customer.setPassword(password);
//			Persisting
			dao.save(customer);
			resp.getWriter().print("<h1 style='color:green'>Account created</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1 style='color:red'>Email and phone number should be unique</h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}
		

		
	}
}
