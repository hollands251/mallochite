package mallochite.encryption;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryption {
	
public static void output(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		//Generating a key pair using public key
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA"); // this is how JAVA knows which algorithm to use for the encryption.
		generator.initialize(512);
		KeyPair keyPair = generator.generateKeyPair();
		Key publicKey = keyPair.getPublic();
		Key privateKey = keyPair.getPrivate();
		
		//Encrypting the message.
		byte[] data = message.getBytes();
		Cipher encryptingCipher = Cipher.getInstance("RSA");
		encryptingCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedMessage = encryptingCipher.doFinal(data);
		System.out.println(message);
		System.out.println(encryptedMessage);
		
		//Decrypting a message using the private key.
		byte[] toDecrypt = encryptedMessage;
		Cipher decryptingCipher = Cipher.getInstance("RSA");
		decryptingCipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedMessage = decryptingCipher.doFinal(toDecrypt);
		
		//Making it Human Readable.
		for(byte b: decryptedMessage) {
			System.out.print((char)b);
		}
		

}

}
