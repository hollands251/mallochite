package mallochite.encryption;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class RSAEncryption {
	

public static KeyPair keyGenerator() throws NoSuchAlgorithmException{
	//Generating a key pair using public key
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA"); // this is how JAVA knows which algorithm to use for the encryption.
			generator.initialize(2048);
			KeyPair keyPair = generator.generateKeyPair();
			
			return keyPair;
}


public static byte[] encrypt(Key pubKey, String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] data = message.getBytes();
		Cipher encryptingCipher = Cipher.getInstance("RSA");
		encryptingCipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] encryptedMessage = encryptingCipher.doFinal(data);
		
		return encryptedMessage;
	}
	
	public static String decrypt(PrivateKey privKey, byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		//Decrypting a message using the private key.
				//String toDecrypt = message;
				Cipher decryptingCipher = Cipher.getInstance("RSA");
				decryptingCipher.init(Cipher.DECRYPT_MODE, privKey);
				byte[] decryptedMessage = decryptingCipher.doFinal(message);
				String output = "";
				//Making it Human Readable.
				for(byte b: decryptedMessage) {
					output = output + (char)b;
				}
				return output;
	}
	
	
	
	//Save to File Part
	
	
	public static void modulus(KeyPair keyPair) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
		
		KeyFactory fact = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pub = fact.getKeySpec(keyPair.getPublic(),
		  RSAPublicKeySpec.class);
		RSAPrivateKeySpec priv = fact.getKeySpec(keyPair.getPrivate(),
		  RSAPrivateKeySpec.class);

		saveToFile("public.key", pub.getModulus(),
		  pub.getPublicExponent());
		saveToFile("private.key", priv.getModulus(),
		  priv.getPrivateExponent());
	}
				
	
	public static void saveToFile(String fileName,
			  BigInteger mod, BigInteger exp) throws IOException {
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
			}
	
	public static PublicKey getpublicKey(String filename)
		    throws Exception {

		      InputStream in = new FileInputStream(filename);
		        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(
		                in));
		        try {
		            BigInteger m = (BigInteger) oin.readObject();
		            BigInteger e = (BigInteger) oin.readObject();
		            KeyFactory fact = KeyFactory.getInstance("RSA");
		                return fact.generatePublic(new RSAPublicKeySpec(m, e));

		        } catch (Exception e) {
		            throw new RuntimeException("Error", e);
		        } finally {
		            oin.close();
		            
		        }

		  }
	
	public static PrivateKey getprivateKey(String filename)
		    throws Exception {

		      InputStream in = new FileInputStream(filename);
		        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(
		                in));
		        try {
		            BigInteger m = (BigInteger) oin.readObject();
		            BigInteger e = (BigInteger) oin.readObject();
		            KeyFactory fact = KeyFactory.getInstance("RSA");
		                return fact.generatePrivate(new RSAPrivateKeySpec(m, e));

		        } catch (Exception e) {
		            throw new RuntimeException("Error", e);
		        } finally {
		            oin.close();
		            
		        }

		  }
	

	public static byte[] rsaEncrypt(byte[] data) throws Exception {
		  PublicKey pubKey = getpublicKey("public.key");
		  Cipher cipher = Cipher.getInstance("RSA");
		  cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		  byte[] cipherData = cipher.doFinal(data);
		  return cipherData;
		}
	
	public static String rsaDecrypt(byte[] data) throws Exception {
		  PrivateKey privKey = getprivateKey("private.key");
		  Cipher cipher = Cipher.getInstance("RSA");
		  cipher.init(Cipher.DECRYPT_MODE, privKey);
		  byte[] cipherData = cipher.doFinal(data);
		  String output = "";
		  for(byte b: cipherData) {
				output = output + (char)b;
			}
			return output;
		}
	
	

}