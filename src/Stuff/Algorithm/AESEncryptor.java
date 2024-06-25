package Stuff.Algorithm;

import Main.Main;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.List;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESEncryptor implements Main.Runner {
    private static final String AES = "AES";

    public String encrypt(String plaintext, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plaintext.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run(List<String> inputs) {
        if (inputs.isEmpty()) {
            System.out.println("Not enough arguments for AES encryption. Expected: <plaintext> <key length>");
            return;
        }
        String[] parts = inputs.get(0).split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Not enough arguments for AES encryption. Expected: <plaintext> <key length>");
            return;
        }
        String plaintext = parts[0];
        int keyLength;
        try {
            keyLength = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid key length. Key length must be an integer.");
            return;
        }
        if (keyLength != 128 && keyLength != 192 && keyLength != 256) {
            System.out.println("Invalid key length for AES encryption. Key length must be 128, 192, or 256 bits.");
            return;
        }
        SecretKey key;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(AES);
            keyGen.init(keyLength);
            key = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error generating AES key: " + e.getMessage());
            return;
        }
        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + encrypted);
        System.out.println("Encryption key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}