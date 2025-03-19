package com.radityopw.kuliah.sdi.xps;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.sql.*;

public class Dist{

	public static void ensureDbReady(String dbName) throws Exception {
		Path userDb = Paths.get(dbName);

        	// 1. Cek apakah file database untuk user sudah ada
        	if (Files.exists(userDb)) {
            		//System.out.println("File database untuk user '" + user + "' sudah ada.");
        	} else {
            		// Lokasi file template yang akan digunakan untuk membuat file baru
            		Path templateDb = Paths.get("template.sqlite3");

            		// Pastikan file template ada
            		if (!Files.exists(templateDb)) {
                		throw new FileNotFoundException("File template tidak ditemukan: " + templateDb.toString());
            		}

            		// 2. Copy file template ke lokasi file database user
            		Files.copy(templateDb, userDb, StandardCopyOption.REPLACE_EXISTING);
            		//System.out.println("File database untuk user '" + user + "' telah dibuat dari template.");
		}
	}

	public static void main(String[] a) throws Exception{
		Class.forName("org.sqlite.JDBC");
                Connection conDb1 = DriverManager.getConnection("jdbc:sqlite:db1.sqlite3");

		//ambil semua user dulu
		String sql = "SELECT email FROM user";
		PreparedStatement ps = conDb1.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			//ambil yang difollow
			//System.out.println(rs.getString(1));
			String dbName = rs.getString(1)+".sqlite3";
			ensureDbReady(dbName);
			Connection conUser = DriverManager.getConnection("jdbc:sqlite:"+dbName);
			sql = "SELECT email_to_follow FROM follows";
			PreparedStatement psUser = conUser.prepareStatement(sql);
			ResultSet rsUser = psUser.executeQuery();
			while(rsUser.next()){
				sql = "SELECT email,post,post_created FROM posts WHERE email=? ORDER BY post_created DESC LIMIT 1";
				PreparedStatement psPostFollow = conDb1.prepareStatement(sql);
				psPostFollow.setString(1,rsUser.getString(1));
				ResultSet rsPostFollow = psPostFollow.executeQuery();
				if ( rsPostFollow.next() ) {
					//System.out.println("memproses "+rsPostFollow.getString(1));
					//hapus dulu di timeline user untuk email follow
					PreparedStatement psUserHapusTl = conUser.prepareStatement("DELETE FROM timeline WHERE email=?");
					psUserHapusTl.setString(1,rsPostFollow.getString(1));
					psUserHapusTl.executeUpdate();
					psUserHapusTl.close();
					psUserHapusTl = null;

					//tambahkan timeline
					PreparedStatement psUserAddTl = conUser.prepareStatement("INSERT INTO timeline(email,post,post_created) VALUES(?,?,?)");
					psUserAddTl.setString(1,rsPostFollow.getString(1));
					psUserAddTl.setString(2,rsPostFollow.getString(2));
					psUserAddTl.setString(3,rsPostFollow.getString(3));
					psUserAddTl.executeUpdate();
					psUserAddTl.close();
					psUserAddTl = null;
				}
				psPostFollow.close();
				psPostFollow = null;
				rsPostFollow.close();
				rsPostFollow = null;
			}
			psUser.close();
			psUser = null;
			rsUser.close();
			rsUser = null;
			conUser.close();
			conUser = null;
		}
		ps.close();
		ps = null;
		rs.close();
		rs = null;
		//ambil last post dari yang di follow masukkan ke timeline 

		conDb1.close();
	}

}
