/*
	Authors: Stephen Bapple, Holly Radcliffe
	Computer Network and Security
	9/2016
	Sender:
	1: Takes a file name from the user to get the message from.
	2: Encrypt message with symmetric key to form digital
		envelope.
*/
import java.util.Scanner;
import java.io.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;

import java.security.KeyFactory;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;

import java.math.BigInteger;

import javax.crypto.Cipher;

public class Sender {
	public static void main(String[] args) throws Exception {
		// Get Y's public key and the secret key.
		PublicKey receiverPubKey = readPubKeyFromFile("YPublic.key");
		SecretKeySpec symmetricKey = readSymmetricKeyFromFile("symmetric.key");
		byte[] symBytes = symmetricKey.getEncoded();
		
		// Get the message file name from the user.
		System.out.print("Please input the name of the message file: ");
		String fileName = ((new Scanner(System.in)).nextLine());
		System.out.println();

		// Write Kxy||M||kxy
		BufferedOutputStream bout = new BufferedOutputStream(
				new FileOutputStream("message.kmk"));
		bout.write(symBytes, 0, symBytes.length);
		
		// Write the message to a file
		BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(fileName));
		
		byte[] temp = new byte[1024];
		
		while (bin.read(temp, 0, temp.length) != -1) {
			bout.write(temp, 0, temp.length);
		}
		bout.write(symBytes);
		bout.close();
		
		// Calculate the Hash
		SHA256.saveMessageDigestToFile("message.kmk", "message.khmac");
		
		// Encrypt the message
		AES.encryptMessageAndSaveToFile(symmetricKey, fileName,
				"message.aescipher");
		
		//Encrypt the key
		RSAConfidentiality.encryptKeyAndSaveToFile(receiverPubKey,
				symmetricKey, "kxy.rsacipher");
	}
	
	// Read key parameters from a file and generate the public key
	public static PublicKey readPubKeyFromFile(String keyFileName) 
			throws IOException {
		InputStream file = new FileInputStream(keyFileName);
		InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
		
		try {
			@SuppressWarnings("unchecked")
			BigInteger m = (BigInteger) input.readObject();
			BigInteger e = (BigInteger) input.readObject();

			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey key = factory.generatePublic(keySpec);
			
			return key;
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialization error", e);
		} finally {
			input.close();
		}
	} // End readPubKeyFromFile
	
	// Read the symmetric key from a file
	public static SecretKeySpec readSymmetricKeyFromFile(String keyFileName)
			throws IOException {
		InputStream file = new FileInputStream(keyFileName);
		InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
		
		try {
			SecretKeySpec key = (SecretKeySpec) input.readObject();
	  
			String keyStr = new String(key.getEncoded(), "UTF-8");

			return key;
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialization error", e);
		} finally {
			input.close();
		}
	} // End readSymmetricKeyFromFile
} // End class