package Stuff.Hash;

import Main.Main;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Hasher implements Hasher, Main.Runner {
    @Override
    public String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(List<String> inputs) {
        for (String input : inputs) {
            System.out.println(hash(input));
        }
    }
}