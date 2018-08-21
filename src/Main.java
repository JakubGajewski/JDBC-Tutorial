import java.sql.*;

public class Main {

	public static void main(String[] args) {
	
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
	

		String dbUrl = "jdbc:mysql://localhost:3306/TimeManager?useSSL=false&&allowPublicKeyRetrieval=true";
		String userName = "test";
		String password = "test";

		try {
			connection = DriverManager.getConnection(dbUrl, userName, password);
			statement = connection.createStatement();
			
			statement.executeUpdate("INSERT INTO timemanager.note (content) VALUES (\"umyc okna\")");
			
			int rowsAffected = statement.executeUpdate("update timemanager.note set content = 'zmywanie' where id = 6");
					
			resultSet = statement.executeQuery("SELECT * FROM timemanager.note");
						
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString("content"));

			}
		} catch (SQLException e) {
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}
