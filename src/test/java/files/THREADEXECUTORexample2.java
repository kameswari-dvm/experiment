package files;

import static dao.Database.getConnectionForDb;
import static helper.Actions.driver;
import static helper.Actions.getDriver;
import static helper.Actions.getWebElement;
import static helper.Actions.loadUrl;
import static helper.Actions.waitForElement;
import static helper.Actions.waitTill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;

public class THREADEXECUTORexample2 {
	private static final int MYTHREADS = 3;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
		int start=0,end=10;
		for (int i = 0; i < 2; i++) {
			System.out.println(start+","+end);
			Runnable worker = new MyRunnable(start,end);
			executor.execute(worker);
			start = end+1;
			end = end+10;
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			 
		}
		System.out.println("\nFinished all threads");
	}
}
	class MyRunnable implements Runnable {
	  int start, end;
	  MyRunnable(int start,int end) {
			this.start = start;
			this.end = end;
		}
	public void run() {
		Utility utility = new Utility();
		utility.getSellerIDsOfAllPostedAndNewPostedUsersFromDB(start,end);
		utility.loginToTradusAdmin();
	}
}
	class Utility{
		Connection con = null;
		static Map<String, String> tproIDSAndDBCount = new HashMap<String, String>();

		public void getSellerIDsOfAllPostedAndNewPostedUsersFromDB(int start,int end) {
			System.out.println("Entered");
			con = getConnectionForDb();
			String query = "SELECT trpro_seller_id FROM User_Info WHERE tradus_account IN ('Posted','NewPosted') LIMIT "+start+","+end+"";
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					query = "SELECT COUNT(1) FROM Ad_details WHERE Ad_status!= 'Archived' AND Moderated = 'No' AND user_id IN (SELECT user_id FROM User_Info WHERE trpro_seller_id = ?)";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, rs.getString(1));
					ResultSet rs1 = ps.executeQuery();
					if (rs1.next())
						System.out.println(rs.getString(1) + "->" + rs1.getString(1));
					tproIDSAndDBCount.put(rs.getString(1), rs1.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void loginToTradusAdmin() {
			driver = getDriver();
			driver.manage().window().maximize();
			driver = loadUrl(driver, "https://admin.tradus.com/");
			waitForElement(driver, By.xpath("//*[@id ='username']"));
			getWebElement(driver, By.xpath("//*[@id ='username']")).sendKeys("kameswari@sunfra.in");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			getWebElement(driver, By.xpath("//*[@id ='user-password']")).sendKeys("abc123");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				getWebElement(driver,By.xpath("//*[@type ='submit']")).submit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			waitForElement(driver, By.xpath("//*[@id='new_request']"));
			waitTill(1000);
		}
	}
