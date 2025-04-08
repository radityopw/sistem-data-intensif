import java.io.*;

public class Simple01Get{

	public static void main(String[] a) throws Exception {
		String namaFileDb = a[0];
		String id = a[1];
		final int jmlKarakterPerBaris = 25;
		final int jmlKarakterId = 5;

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

		while( db.getFilePointer() < db.length() ) {
			idDb = idDb + db.readChar();
			iterator = iterator + 1;
			//db.seek(db.getFilePointer() + 2);

			if ( iterator == jmlKarakterId ) {
				if ( idDb.equals(id) ) offset = db.getFilePointer() - (jmlKarakterId * 2);
				iterator = 0;
				idDb = "";
			}
		}


		//System.out.println("offset :"+offset);
		//System.out.println("jml karakter id :"+jmlKarakterId);
		if ( offset > -1 ) {
			db.seek(offset);
			iterator = 0;
			while ( iterator < jmlKarakterPerBaris ) {
				System.out.print(db.readChar());
				iterator = iterator + 1;
				//db.seek(db.getFilePointer() + 2);
			}
		}
		db.close();
		System.out.println();
	}
}
