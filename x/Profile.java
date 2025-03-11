import java.sql.*;
import java.util.*;

public class Profile{

	public static void main(String[] a) throws Exception{

		String email = a[0];

		Class.forName("org.sqlite.JDBC");
                Connection connection = DriverManager.getConnection("jdbc:sqlite:centralized.sqlite3");

		String sql = "SELECT post,post_created FROM posts WHERE email=? ORDER BY post_created DESC";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1,email);

		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			System.out.println("date created : "+rs.getString(2));
			System.out.println(rs.getString(1));
			System.out.println();
		}

		ps.close();
		connection.close();
	}

}
