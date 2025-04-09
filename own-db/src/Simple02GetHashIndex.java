import java.io.*;

public class Simple02GetHashIndex{

	public static void main(String[] a) throws Exception {
		Simple02Config config = new Simple02Config();

		final String namaFileDb = config.namaFileDb();
		final String namaIndexDir = config.namaIndexDir();
		String id = a[0];
		final int jmlKarakterPerBaris = config.jmlKarakterPerBaris();
		final int jmlKarakterId = config.jmlKarakterId();

		if ( id.length() > jmlKarakterId ) throw new Exception(" id tidak boleh lebih panjang dari "+jmlKarakterId+" karakter ");

		for(int i=id.length();i<jmlKarakterId;i++) {
			id = id + " ";
		}

		RandomAccessFile index = new RandomAccessFile(namaIndexDir+"/"+id,"r");
		index.seek(0);
		long offset = Long.parseLong(index.readLine());
		index.close();

		RandomAccessFile db = new RandomAccessFile(namaFileDb,"r");

		byte[] dataRow = new byte[jmlKarakterPerBaris];
		if ( offset > -1 ) {
			db.seek(offset);
			db.read(dataRow,0,jmlKarakterPerBaris);
			System.out.print(new String(dataRow));
		}
		db.close();
		System.out.println();
	}
}
