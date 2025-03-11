import java.util.*;
import java.sql.*;
import java.text.*;

public class Post {

	public static void main(String[] a) throws Exception {

		String email = a[0];
		String post = "";

		for (int i=1;i<a.length;i++) {
			post+=a[i];
			post+=" ";
		}

		DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		java.util.Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);

		Class.forName("org.sqlite.JDBC");
		Connection connection = DriverManager.getConnection("jdbc:sqlite:centralized.sqlite3");
          	String sql = "INSERT INTO posts(email,post_created,post) VALUES(?,?,?)";

          	PreparedStatement ps = connection.prepareStatement(sql);
          	ps.setString(1,email);
          	ps.setString(2,todayAsString);
          	ps.setString(3,post);
          	ps.executeUpdate();

		ps.close();
          	connection.close();

	}
}
