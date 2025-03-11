import java.sql.*;
import java.text.*;
import java.util.*;

public class Follow {
	public static void main(String[] a) throws Exception{
		String email = a[0];
		String emailToFollow = a[1];

		DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
                java.util.Date today = Calendar.getInstance().getTime();
                String todayAsString = df.format(today);

		Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:centralized.sqlite3");
                String sql = "INSERT INTO follows(email,follow_created,email_to_follow) VALUES(?,?,?)";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,email);
                ps.setString(2,todayAsString);
                ps.setString(3,emailToFollow);
                ps.executeUpdate();

                ps.close();
                connection.close();
	}
}
