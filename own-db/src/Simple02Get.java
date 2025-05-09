import java.io.*;

public class Simple02Get{

	public static void main(String[] a) throws Exception {
		Simple02Config config = new Simple02Config();

		String namaFileDb = config.namaFileDb();
		String id = a[0];
		final int jmlKarakterPerBaris = config.jmlKarakterPerBaris();
		final int jmlKarakterId = config.jmlKarakterId();

		if ( id.length() > jmlKarakterId ) throw new Exception(" id tidak boleh lebih panjang dari "+jmlKarakterId+" karakter ");

		for(int i=id.length();i<jmlKarakterId;i++) {
			id = id + " ";
		}

		RandomAccessFile db = new RandomAccessFile(namaFileDb,"r");

		db.seek(0);

		long offset = -1;

		int iterator = 0;
		String idDb = "";
		byte[] idDbBytes = new byte[jmlKarakterId];

		while( db.getFilePointer() < db.length() ) {
			db.read(idDbBytes,0,jmlKarakterId);
			idDb = idDb + new String(idDbBytes);
			iterator = iterator + jmlKarakterId;

			if ( iterator == jmlKarakterId ) {
				if ( idDb.equals(id) ) offset = db.getFilePointer() - (jmlKarakterId * 1);
				iterator = 0;
				idDb = "";
				idDbBytes = new byte[jmlKarakterId];
			}
		}


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
