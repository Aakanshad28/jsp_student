package jsp_student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp_employee.EmployeeCRUD;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String father_name = req.getParameter("father_name");
		String mother_name = req.getParameter("mother_name");
		long phone = Long.parseLong(req.getParameter("phone"));
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String college = req.getParameter("college");

		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setFather_name(father_name);
		student.setMother_name(mother_name);
		student.setPhone(phone);
		student.setAddress(address);
		student.getEmail();
		student.setPassword(password);
		student.setCollege(college);

		StudentCRUD crud = new StudentCRUD();
		int count;
		try {
			count = crud.signUp(student);
			if (count != 0) {
				req.setAttribute("message", "Signup Successful,Please Login");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
