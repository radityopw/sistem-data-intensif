import java.io.*;

public class Simple02Set{

	public void write(String namaFileDb, String id, String nama) throws Exception{

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
		db.writeBytes(id);
		db.writeBytes(nama);

		db.close();

	}

	public static void main(String[] a) throws Exception{
		String id = a[0]; // id maksimal 5 karakter
		String nama = a[1]; // nama maksimal 20 karakter

		Simple02Config config = new Simple02Config();

		final String namaFileDb = config.namaFileDb();

		final int jmlKarakterId = config.jmlKarakterId();
		final int jmlKarakterNama = config.jmlKarakterNama();

		if ( id.length() > jmlKarakterId ) throw new Exception(" id tidak boleh lebih dari "+jmlKarakterId+" karakter");
		if ( nama.length() > jmlKarakterNama ) throw new Exception(" nama tidak boleh lebih dari "+jmlKarakterNama+" karakter");

		Simple02Set set = new Simple02Set();
		set.write(namaFileDb,id,nama);

	}

}
