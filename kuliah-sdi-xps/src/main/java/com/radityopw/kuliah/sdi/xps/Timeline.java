package com.radityopw.kuliah.sdi.xps;

import java.sql.*;

public class Timeline{

	public static void main(String[] a) throws Exception {
		// mengambil parameter
		// parameter ke 0 adalah  email

		String email = a[0];


		String dbName = email+".sqlite3";
		Dist.ensureDbReady(dbName);
		// melakukan koneksi ke sqlite3
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:"+dbName);

		// membaca timeline
		String sql = "SELECT email,post,post_created FROM timeline ORDER BY post_created DESC";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1) + " ("+rs.getString(3)+")");
			System.out.println(rs.getString(2));
			System.out.println();
		}
		// close connection
		rs.close();
		ps.close();
		c.close();

	}

}
