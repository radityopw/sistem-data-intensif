import java.io.*;

public class Test{

	public static void main(String[] a) throws Exception{

		String s1 = "";
		String s2 = "";
		String s3 = "";

		String file1 = "test1.radit.db";
		String file2 = "test2.radit.db";
		String file3 = "test3.radit.db";

		RandomAccessFile db1 = new RandomAccessFile(file1,"rw");
		RandomAccessFile db2 = new RandomAccessFile(file2,"rw");
		FileWriter db3 = new FileWriter(file3);

		for (int i=0; i<25; i++) {

			s1+="a";
			s2+="b";
			s3+="c";

		}

		db1.writeChars(s1);
		db1.writeChars(s2);
		db1.writeChars(s3);

		db2.writeBytes(s1);
		db2.writeBytes(s2);
		db2.writeBytes(s3);

		db3.write(s1);
		db3.write(s2);
		db3.write(s3);

		db1.close();
		db2.close();
		db3.close();

	}

}
