package todo;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyService {
	public void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));

		int age = LocalDate.now().getYear() - dob.getYear();

		if (age < 18) {
			resp.getWriter().print(
					"<h1 align='center' style='color:red'>Sorry! You are not eligible for Creating Account</h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);
		} else {
			MyDao dao = new MyDao();
			Customer customer2 = dao.findByEmail(email);

			if (customer2 == null) {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setAge(age);
				customer.setDob(dob);
				customer.setEmail(email);
				customer.setGender(gender);
				customer.setMobile(mobile);
				customer.setPassword(AES.encrypt(password, "123"));

				dao.saveCustomer(customer);

				resp.getWriter().print("<h1 align='center' style='color:green'>Account Created Success </h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			} else {
				resp.getWriter().print("<h1 align='center' style='color:red'>Account Already Exists with the Email : "
						+ email + " </h1>");
				req.getRequestDispatcher("Signup.html").include(req, resp);
			}
		}

	}

}
