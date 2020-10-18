import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDerbyClientTest {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public JdbcDerbyClientTest() throws Exception {
	try {

	    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
	    connect = DriverManager.getConnection("jdbc:derby://localhost:1527/F:/apache-derby-db-instance/firstdb");
	    PreparedStatement statement = connect.prepareStatement("SELECT * from employee");

	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		String user = resultSet.getString("NAME");
		String number = resultSet.getString("GENDER");
		System.out.println("NAME = " + user + ", GENDER = " + number);
	    }
	} catch (Exception e) {
	    throw e;
	} finally {
	    close();
	}

    }

    private void close() {
	try {
	    if (resultSet != null) {
		resultSet.close();
	    }
	    if (statement != null) {
		statement.close();
	    }
	    if (connect != null) {
		connect.close();
	    }
	} catch (Exception e) {

	}
    }

    public static void main(String[] args) throws Exception {
	JdbcDerbyClientTest dao = new JdbcDerbyClientTest();
    }

}