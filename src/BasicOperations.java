import java.sql.*;

public class BasicOperations {

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
			
			int rowsAffected = statement.executeUpdate("update timemanager.note set content = 'zmywanie' where id = 6");
			//returns an integer!
			
			System.out.println("There are: " + rowsAffected + " rows updated");
			
			rowsAffected = statement.executeUpdate("delete from timemanager.note where content = 'zakupy'");
						
			System.out.println("There are: " + rowsAffected + " rows deleted");
					
			resultSet = statement.executeQuery("SELECT * FROM timemanager.note");
			//returns a result set
						
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
