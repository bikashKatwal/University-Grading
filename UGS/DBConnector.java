import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {
	public Connection conn;

	//Connect database from the path provided in DBResource
	public DBConnector() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("DBResource"));
		String dbUrl = props.getProperty("dbUrl");
		String user = props.getProperty("user");
		String pass = props.getProperty("pass");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(dbUrl + "?useSSL=false", user, pass);

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
