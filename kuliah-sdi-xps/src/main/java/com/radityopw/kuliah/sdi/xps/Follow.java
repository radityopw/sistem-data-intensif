package com.radityopw.kuliah.sdi.xps;

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

		String dbName = email+".sqlite3";
		Dist.ensureDbReady(dbName);
		// melakukan koneksi ke sqlite3
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:"+dbName);


		// insert data ke database
		String sql = "INSERT INTO follows(email,follow_created,email_to_follow) VALUES(?,?,?)";
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1,email);
		stmt.setString(2,formattedDate);
		stmt.setString(3,emailToFollow);
		stmt.executeUpdate();

		Connection cDb1 = DriverManager.getConnection("jdbc:sqlite:db1.sqlite3");
		sql = "SELECT email FROM user WHERE email=?";
                stmt = cDb1.prepareStatement(sql);
                stmt.setString(1,email);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                        // skip karena sudah ada usernya
                        stmt.close();
                        stmt = null;
                } else {
                        stmt.close();
                        stmt = null;

                        sql = "INSERT INTO user(email) VALUES (?)";
                        stmt = cDb1.prepareStatement(sql);
                        stmt.setString(1,email);
                        stmt.execute();
                        stmt.close();
                        stmt = null;
                }
		rs.close();
		rs = null;

		// close connection
		c.close();
		cDb1.close();

	}

}
