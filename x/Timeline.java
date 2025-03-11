import java.sql.*;
import java.util.*;

public class Timeline{

	public static void main(String[] a) throws Exception{

		String email = a[0];

		Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:centralized.sqlite3");

		String sql = "SELECT email_to_follow FROM follows WHERE email=?";

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1,email);
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			sql = "SELECT post,post_created FROM posts WHERE email=? ORDER BY post_created DESC";

			PreparedStatement ps2 = connection.prepareStatement(sql);
			ps2.setString(1,rs.getString(1));

			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				System.out.println("user : "+rs.getString(1));
				System.out.println("date : "+rs2.getString(2));
				System.out.println(rs2.getString(1));
				System.out.println();
			}
			rs2.close();
			ps2.close();
		}

		rs.close();
		ps.close();
		connection.close();
	}

}
