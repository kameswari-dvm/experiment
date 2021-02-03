package dao;



import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection con = null;

	public static Connection  getConnectionForDb() {
		Connection conn = null;
		try {
			System.out.println("Creating Connection with Db : Host, DbName-Scraping");
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/Scraping?allowMultiQueries=true&characterEncoding=utf8", "raghu", "Sunfra@442");

		} catch (Exception e) {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e1) {
				}
			}
			System.out.println("Problem while creating connection with Db : Host-127.0.0.1:3307, DbName-Scraping");
			return getConnectionForDb();
		}
		return conn;
	}

}
