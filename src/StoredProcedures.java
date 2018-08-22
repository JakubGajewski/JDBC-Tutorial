import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class StoredProcedures {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo?useSSL=false&&allowPublicKeyRetrieval=true", "test", "test");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM reindeer");
			
			while (resultSet.next() ) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getFloat(4) + " ");
				System.out.println();
			}

			callableStatement = connection.prepareCall("{call rob_the_rich_give_to_the_poor(?)}");
			callableStatement.setDouble(1, 100.00);
			callableStatement.execute();
			
			resultSet = statement.executeQuery("SELECT * FROM reindeer");
			while (resultSet.next() ) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getFloat(4) + " ");
				System.out.println();
			}
						
			callableStatement = connection.prepareCall("{call raise_for_one_team(?,?)}");
			callableStatement.setString(1, "Santa Claus");
			callableStatement.setInt(2, 500);
			callableStatement.execute();
			
			resultSet = statement.executeQuery("SELECT * FROM reindeer");
			while (resultSet.next() ) {
				System.out.print(resultSet.getInt(1) + " ");
				System.out.print(resultSet.getString(2) + " ");
				System.out.print(resultSet.getString(3) + " ");
				System.out.print(resultSet.getFloat(4) + " ");
				System.out.println();
			}
				
			//OUT PARAMETERS
			callableStatement = connection.prepareCall("{call budget_for_one_team(?,?)}");
			callableStatement.setString(1, "Santa Claus");
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			
			int theResult = callableStatement.getInt(2);
		
			System.out.println(theResult);
			
//			//INOUT PARAMETER
//			callableStatement = connection.prepareCall("{call hello(?)}");
//			callableStatement.setString(1, "Rudolph");
//			callableStatement.execute();
//			
//			String reindeerToBeWelcomed = callableStatement.getString(1);
//			System.out.println(theResult);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close (resultSet);
			close (statement);
			close (callableStatement);
			close (connection);
		}
	}
	
	public static void close (AutoCloseable autoCloseable) {
		try {
			autoCloseable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
	

