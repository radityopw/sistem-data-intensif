package com.radityopw.kuliah.sdi.x;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Follow{

	public static void main(String[] a) throws Exception {
		// mengambil parameter
		// parameter ke 0 adalah email
		// parameter ke 1 adalah email yang akan di follow

		String email = a[0];
		String emailToFollow = a[1];

		// dapatkan tanggal sekarang
		LocalDateTime now = LocalDateTime.now();
        
        	// Definisikan format
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        	// Format tanggal ke string
        	String formattedDate = now.format(formatter);

		//System.out.println(email);
		//System.out.println(message);

		// melakukan koneksi ke sqlite3
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:db1.sqlite3");


		// insert data ke database
		String sql = "INSERT INTO follows(email,follow_created,email_to_follow) VALUES(?,?,?)";
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1,email);
		stmt.setString(2,formattedDate);
		stmt.setString(3,emailToFollow);
		stmt.executeUpdate();
		
		// close connection
		stmt.close();
		c.close();

	}

}
