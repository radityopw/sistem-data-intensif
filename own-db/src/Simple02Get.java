import java.io.*;

public class Simple02Get{

	public static void main(String[] a) throws Exception {
		String namaFileDb = a[0];
		String id = a[1];
		final int jmlKarakterPerBaris = 25;
		final int jmlKarakterId = 5;
		final String encoding = "ISO-8859-1";

		if ( id.length() > jmlKarakterId ) throw new Exception(" id tidak boleh lebih panjang dari "+jmlKarakterId+" karakter ");

		for(int i=id.length();i<jmlKarakterId;i++) {
			id = id + " ";
		}

		//System.out.println(id);
		//System.out.println(id.length());

		RandomAccessFile db = new RandomAccessFile(namaFileDb,"r");

		db.seek(0);
		/*
		System.out.println(db.length());
		System.out.println(db.getFilePointer());
		System.out.println(db.readChar());
		db.close();
		System.exit(0);
		*/

		long offset = -1;

		int iterator = 0;
		String idDb = "";
		byte[] idDbBytes = new byte[jmlKarakterId];

		while( db.getFilePointer() < db.length() ) {
			db.read(idDbBytes,0,jmlKarakterId);
			idDb = idDb + new String(idDbBytes,encoding);
			iterator = iterator + jmlKarakterId;
			//db.seek(db.getFilePointer() + 2);

			if ( iterator == jmlKarakterId ) {
				if ( idDb.equals(id) ) offset = db.getFilePointer() - (jmlKarakterId * 1);
				iterator = 0;
				idDb = "";
				idDbBytes = new byte[jmlKarakterId];
			}
		}


		//System.out.println("offset :"+offset);
		//System.out.println("jml karakter id :"+jmlKarakterId);
		byte[] dataRow = new byte[jmlKarakterPerBaris];
		if ( offset > -1 ) {
			db.seek(offset);
			db.read(dataRow,0,jmlKarakterPerBaris);
			System.out.print(new String(dataRow,encoding));
		}
		db.close();
		System.out.println();
	}
}
