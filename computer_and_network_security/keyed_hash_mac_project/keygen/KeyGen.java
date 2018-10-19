/*

 Authors: Stephen Bapple and Holly Radcliffe
 Date: 9/2016
 Computer Networks and Security
 KeyGen class to calculate private
 and public keys for a sender (x)
 and a receiver (y) as well as a
 random symmetric key.  Keys are
 written to files.

 */

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

import java.util.Scanner;

public class KeyGen {

	// Generate all public/private keys and random symmetric key
	public static void main(String[] args) throws Exception {
		createAndSaveKeyPair("X");
		createAndSaveKeyPair("Y");
		createRandomSymmetricKey();
	}// main

	// Public utility method to be called from other programs
	public void generateKeysForDigitalEnvelope() throws Exception {
		createAndSaveKeyPair("X");
		createAndSaveKeyPair("Y");
		createRandomSymmetricKey();
	}

	// Create the random symmetric key
	public static void createRandomSymmetricKey() throws Exception {

		Scanner keyboard = new Scanner(System.in);
		System.out.print("Please enter in 16 characters for the random symmetric key: ");
		String randomChars = keyboard.nextLine();

		// Key must be 16 characters without spaces
		while (randomChars.replaceAll(" +", "").length() != 16) {

			System.out.print("Wrong number of characters, input 16 characters: ");
			randomChars = keyboard.nextLine();

		}// while

		// create AES symmetric key from input
		SecretKeySpec key = new SecretKeySpec(randomChars.getBytes("UTF-8"),
				"AES");

		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("symmetric.key")));

		// write key to the file
		try {
			out.writeObject(key);
		} catch (Exception e) {
			throw new IOException("Unexpected error", e);
		} finally {
			out.close();
		}

	}// createRandom

	// Create private and public keys and write to file
	public static void createAndSaveKeyPair(String filePrefix) throws Exception {
		// Generate a pair of keys
		SecureRandom random = new SecureRandom();
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024, random); // 1024: key size in bits
		KeyPair pair = generator.generateKeyPair();
		Key pubKey = pair.getPublic();
		Key privKey = pair.getPrivate();

		// get the parameters of the keys: modulus and exponent
		KeyFactory factory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubKSpec = factory.getKeySpec(pubKey,
				RSAPublicKeySpec.class);
		RSAPrivateKeySpec privKSpec = factory.getKeySpec(privKey,
				RSAPrivateKeySpec.class);

		// save the parameters of the keys to the files
		saveToFile(filePrefix + "Public.key", pubKSpec.getModulus(),
				pubKSpec.getPublicExponent());
		saveToFile(filePrefix + "Private.key", privKSpec.getModulus(),
				privKSpec.getPrivateExponent());

	} // createKeyPair

	// Write RSA private/public keys and modulus and exponents to files
	public static void saveToFile(String fileName, BigInteger mod,
			BigInteger exp) throws IOException {

		/*System.out.println("Write to " + fileName + ": modulus = "
				+ mod.toString() + ", exponent = " + exp.toString() + "\n");
*/

		ObjectOutputStream oout = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)));

		try {
			oout.writeObject(mod);
			oout.writeObject(exp);
		} catch (Exception e) {
			throw new IOException("Unexpected error", e);
		} finally {
			oout.close();
		}
	} // saveToFile

} // KeyGen
