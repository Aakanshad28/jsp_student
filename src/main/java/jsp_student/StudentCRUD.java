package jsp_student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCRUD {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp","root","root");
		return connection;
	}
	public int signUp(Student student) throws ClassNotFoundException, SQLException {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?,?)");
		preparedStatement.setInt(1,student.getId() );
		preparedStatement.setString(2, student.getName());
		preparedStatement.setString(3, student.getFather_name());
		preparedStatement.setString(4, student.getMother_name());
		preparedStatement.setLong(5, student.getPhone());
		preparedStatement.setString(6, student.getAddress());
		preparedStatement.setString(7, student.getEmail());
		preparedStatement.setString(8, student.getPassword());
		
		
		int result=preparedStatement.executeUpdate();
		connection.close();
		return result;
	}
	public String getStudent(String email) throws Exception {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FORM STUDENT WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		String password=null;
	    while (resultSet.next()) {
	    	password=resultSet.getString("password");
	    }
	    connection.close();
		return password;
	  }
	public List<Student> getAllStudent() throws Exception {
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM STUDENT");
		ResultSet resultSet=preparedStatement.executeQuery();
		
		List<Student> list=new ArrayList<Student>();
		while(resultSet.next()) {
		Student student=new Student();
		student.setId(resultSet.getInt("id"));
		student.setName(resultSet.getString("name"));
		student.setFather_name(resultSet.getString("father_name"));
		student.setMother_name(resultSet.getString("mother_name"));
		student.setPhone(resultSet.getLong("phone"));
		student.setAddress(resultSet.getString("address"));
		student.setEmail(resultSet.getString("email"));
		student.setPassword(resultSet.getString("password"));
		
		list.add(student);
		}
		connection.close();
		return list;
	}
	
}
