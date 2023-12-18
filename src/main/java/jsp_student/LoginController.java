package jsp_student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_employee.Employee;
@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		StudentCRUD crud = new StudentCRUD();
		String dbPassword = null;

		try {
			dbPassword = crud.getStudent(email);
			RequestDispatcher dispatcher = null;
			if (dbPassword != null) {
				if (dbPassword.equals(password)) {
					List<Student> list = crud.getAllStudent();
					req.setAttribute("list", list);
					dispatcher = req.getRequestDispatcher("success.jsp");
				} else {
					req.setAttribute("message", "login unsuccessful,please check your password");
					dispatcher = req.getRequestDispatcher("login.jsp");
				}
			}
			else {
				req.setAttribute("message", "User not found,Please Register");
				dispatcher=req.getRequestDispatcher("signup.jsp");
			}
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
