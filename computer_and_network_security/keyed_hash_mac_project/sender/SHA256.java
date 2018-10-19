import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class SHA256{
  private static int BUFFER_SIZE = 32 * 1024;

  public static void main(String[] args) throws Exception {}

  public static void saveMessageDigestToFile(String sourceFile,
		String destinationFile) throws Exception {
    BufferedInputStream file = new BufferedInputStream(
			new FileInputStream(sourceFile));
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    DigestInputStream in = new DigestInputStream(file, md);
	BufferedOutputStream bout = new BufferedOutputStream(
				new FileOutputStream(destinationFile));
	
    byte[] buffer = new byte[BUFFER_SIZE];
    
	System.out.println("digital digest (hash value):");
	while ((in.read(buffer, 0, BUFFER_SIZE)) != -1) {
		md = in.getMessageDigest();
		byte[] hash = md.digest();
		bout.write(hash, 0, hash.length);
		
		for (int k=0, j=0; k<hash.length; k++, j++) {
			System.out.format("%2X ", new Byte(hash[k])) ;
			if (j >= 15) {
				System.out.println("");
				j=-1;
			}
		}
	} 	
	
	in.close();
	bout.close();
	
  }
  
  public static boolean compareMessageDigests(String ourHashFile, String receivedFile) throws Exception {
	  BufferedInputStream bin1 = new BufferedInputStream(
				new FileInputStream(ourHashFile));
	  BufferedInputStream bin2 = new BufferedInputStream(
				new FileInputStream(receivedFile));
				
	  byte[] temp1 = new byte[1024];
	  byte[] temp2 = new byte[1024];
		
	  while (bin1.read(temp1, 0, temp1.length) != -1 && bin2.read(temp2, 0, temp2.length) != -1) {
		if (temp1.length != temp2.length) {
			return false;
		}
		for (int i = 0; i < temp1.length; i++) {
			if (temp1[i] != temp2[i]) {
				return false;
			}
		}
	  }
	  
	  return true;
  }
}

