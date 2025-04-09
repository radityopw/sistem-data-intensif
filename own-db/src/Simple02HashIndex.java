import java.io.*;

public class Simple02HashIndex {

	public static void main(String[] a) throws Exception{

		Simple02Config config = new Simple02Config();

		final String namaFileDb = config.namaFileDb();
		final String namaIndexDir = config.namaIndexDir();
		final int jmlKarakterPerBaris = config.jmlKarakterPerBaris();
                final int jmlKarakterId = config.jmlKarakterId();


		File indexDir = new File( namaIndexDir );
		if ( indexDir.exists() ) indexDir.delete();
		indexDir.mkdir();

		RandomAccessFile db = new RandomAccessFile(namaFileDb,"rw");
		db.seek(0);

		byte[] idByte = new byte[jmlKarakterId];
		while ( db.getFilePointer() < db.length() ) {
			db.read(idByte,0,jmlKarakterId);

			File fileDir = new File( namaIndexDir + "/" + new String( idByte ) );
			fileDir.createNewFile();

			RandomAccessFile index = new RandomAccessFile( fileDir,"rw" );
			index.writeBytes(new String("" + (db.getFilePointer() - jmlKarakterId)));
			index.close();

			db.seek(db.getFilePointer() + jmlKarakterPerBaris - jmlKarakterId);
		}

		db.close();
	}

}
