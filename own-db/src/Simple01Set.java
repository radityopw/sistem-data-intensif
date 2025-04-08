import java.io.*;

public class Simple01Set{

	public static void main(String[] a) throws Exception{
		String namaFileDb = a[0];
		String id = a[1]; // id maksimal 5 karakter
		String nama = a[2]; // nama maksimal 20 karakter

		if ( id.length() > 5 ) throw new Exception(" id tidak boleh lebih dari 5 karakter");
		if ( nama.length() > 20 ) throw new Exception(" nama tidak boleh lebih dari 20 karakter");

		// melakukan penyamaan length setiap kolom
		for (int i=id.length(); i< 5; i++) {
			id = id + " ";
		}

		for (int i=nama.length(); i< 20; i++ ) {
			nama = nama + " ";
		}

		File fileDb = new File(namaFileDb);
		fileDb.createNewFile();

		RandomAccessFile db = new RandomAccessFile(fileDb,"rw");

		db.seek(db.length());
		db.writeChars(id);
		db.writeChars(nama);

		db.close();
	}

}
