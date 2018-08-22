import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatements {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String dbUrl = "jdbc:mysql://localhost:3306/TimeManager?useSSL=false&&allowPublicKeyRetrieval=true";
		String userName = "test";
		String password = "test";
		
		try {
			connection = DriverManager.getConnection(dbUrl, userName, password);
			
			preparedStatement = connection.prepareStatement("select * from timemanager.dog where id between ? and ?;");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 8);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				System.out.print(resultSet.getInt(1) + ". ");
				System.out.println(resultSet.getString("name"));
			}
						
		} catch (

		SQLException e) {
			System.out.println("An error occured!");
			e.printStackTrace();
		}
	}
}

