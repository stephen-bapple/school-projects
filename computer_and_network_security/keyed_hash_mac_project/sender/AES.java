import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AES {
  private static final String IV = "AAAAAAAAAAAAAAAA";
  private static final int BLOCK_SIZE = 16;

  public static void encryptMessageAndSaveToFile(SecretKeySpec key, String sourceFile, String destinationFile) throws Exception {
	Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding", "SunJCE");
	cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
	BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(sourceFile));
	BufferedOutputStream bout = new BufferedOutputStream(
				new FileOutputStream(destinationFile));
	byte[] inputBytes    = new byte[BLOCK_SIZE];
	byte[] cipherBytes = new byte[BLOCK_SIZE];
	int numBytes = 0;
	
	while ((numBytes = bin.read(inputBytes, 0, inputBytes.length)) == 16) {
		cipherBytes = cipher.update(inputBytes);
		bout.write(cipherBytes, 0, cipherBytes.length);
	}	
	
	if (numBytes < 16) {
		byte[] lastBlock = new byte[numBytes];
		for (int i = 0; i < numBytes; i++) {
			lastBlock[i] = inputBytes[i];
		}
		cipherBytes = cipher.update(lastBlock, 0, lastBlock.length);
		bout.write(cipherBytes, 0, cipherBytes.length);
	}
	
	bin.close();
	bout.close();
  }
  
  public static void decryptMessageAndSaveToFile(SecretKeySpec key, String sourceFile, String destinationFile) throws Exception {
	Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding", "SunJCE");
	cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
	BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(sourceFile));
	BufferedOutputStream bout = new BufferedOutputStream(
				new FileOutputStream(destinationFile));
	byte[] inputBytes  = new byte[BLOCK_SIZE];
	byte[] cipherBytes = new byte[BLOCK_SIZE];
	int numBytes = 0;
	
	while ((numBytes = bin.read(inputBytes, 0, inputBytes.length)) == 16) {
		cipherBytes = cipher.update(inputBytes);
		bout.write(cipherBytes, 0, cipherBytes.length);
	}	
	
	if (numBytes < 16) {
		byte[] lastBlock = new byte[numBytes];
		for (int i = 0; i < numBytes; i++) {
			lastBlock[i] = inputBytes[i];
		}
		cipherBytes = cipher.update(lastBlock, 0, lastBlock.length);
		bout.write(cipherBytes, 0, cipherBytes.length);
	}
	
	bin.close();
	bout.close();
  }
}

