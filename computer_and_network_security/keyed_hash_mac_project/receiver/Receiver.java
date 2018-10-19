/**
 * Receiver program. Decrypts a Digital Envelope using
 * RSA, AES, and SHA256 standards to obtain and verify 
 * an encrypted and hashed message.
 *
 * @author Stephen Bapple
 * @author Holly Radcliffe
 * 
 * Submitted October 2016 for Computer Network and Security class.
 * Decrypt digital envelope to decrypt a message.
 */

import java.io.*;
import java.security.Key;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.math.BigInteger;
import javax.crypto.Cipher;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Receiver {

    /**
     * Main method: Decrypts and verifies a Digital Envelope.
     * Relies on the command line to input a filename to write the
     * decrypted message to.
     *
     * @param args Unused.
     */
    public static void main(String[]args) throws Exception {
        PrivateKey receiverPrivKey = readPrivKeyFromFile("YPrivate.key");
        System.out.print("Input the name of the file to save to: ");
        String fileName = ((new Scanner(System.in)).nextLine());
        SecretKeySpec symmetricKey = RSAConfidentiality
              .decryptSymmetricKeyFromFile(
                    receiverPrivKey, "kxy.rsacipher");
        byte[] symBytes = symmetricKey.getEncoded();

        // Write Kxy to message.kmk
        BufferedOutputStream bout = new BufferedOutputStream(
                                    new FileOutputStream("message.kmk"));
        bout.write(symBytes, 0, symBytes.length);

        // Read the ciphertext block by block
        AES.decryptMessageAndSaveToFile(symmetricKey, "message.aescipher",
                                        fileName);
 
        BufferedInputStream bin = new BufferedInputStream(
                                  new FileInputStream(fileName));
        byte[] temp = new byte[1024];

        while (bin.read(temp, 0, temp.length) != -1) {
            bout.write(temp, 0, temp.length);
        }

        // Write Kxy to message.kmk, so that we have: (Kxy||M||kxy)
        bout.write(symBytes, 0, symBytes.length);
        bout.close();

        // Calculate the Hash and save in comparable.khmac
        SHA256.saveMessageDigestToFile("message.kmk", "comparable.khmac");
        FileInputStream fin = new FileInputStream("message.khmac");

        //print received hash
        int len;
        byte data[] = new byte[16];

        // Print received hash in hex
        System.out.println("Receieved digital digest (hash value):");
        do {
            int k = 0;
            len = fin.read(data);
            for (int j = 0; j < len; j++){
                System.out.printf("%2X ", data[j]);
                k++;
                if (j >= 15) {
                    System.out.println("");
                }
            }
        } while (len != -1);

        //compare hash values to determine if decrypted properly
        boolean passed = SHA256.compareMessageDigests("comparable.khmac",
                                                      "message.khmac");

        // Print results
        if (passed) {
            System.out.println("The hash values are identical.");
        } else {
            System.out.println("The hash values DO NOT MATCH!");
        }
    }

    /**
     * Read a private key from a specified file and return it as
     *  a PrivateKey object.
     *
     * @param keyFileName The file to read the private key from.
     * @throws IOException If the filename specified could not be read.
     * @throws RuntimeException If the key could not be read from the file.
     * @return The private key at the file specified. 
     */
    public static PrivateKey readPrivKeyFromFile(String keyFileName) 
          throws IOException {
        InputStream file = new FileInputStream(keyFileName);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        try {
            @SuppressWarnings("unchecked")
            BigInteger m = (BigInteger) input.readObject();
            BigInteger d = (BigInteger) input.readObject();
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, d);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey key = factory.generatePrivate(keySpec);

            return key;
        } catch (Exception e) {
            throw new RuntimeException("Spurious serialization error", e);
        } finally {
            input.close();
        }
    }
}

