package Stuff.Algorithm;

import Main.Main;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;

public class BlowfishDecryptor implements Main.Runner {
    private static final String BLOWFISH = "Blowfish";

    public String decrypt(String ciphertext, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(BLOWFISH);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run(List<String> inputs) {
        if (inputs.size() < 2) {
            System.out.println("Not enough arguments for Blowfish decryption. Expected: <ciphertext> <key>");
            return;
        }
        String ciphertext = inputs.get(0);
        byte[] keyBytes = Base64.getDecoder().decode(inputs.get(1));
        SecretKey key = new SecretKeySpec(keyBytes, BLOWFISH);
        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decrypted);
    }
}