package Stuff.Codec;

import Main.Main;
import java.util.List;

public class HexCodec implements Main.Runner {
    public String encode(String input) {
        try {
            byte[] bytes = input.getBytes();
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String decode(String hex) {
        try {
            byte[] bytes = new byte[hex.length() / 2];
            for (int i = 0; i < hex.length(); i += 2) {
                bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                        + Character.digit(hex.charAt(i+1), 16));
            }
            return new String(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void run(List<String> inputs) {
        for (String input : inputs) {
            String encoded = encode(input);
            System.out.println(encoded == null ? "null" : encoded);
            String decoded = decode(input);
            System.out.println(decoded == null ? "null" : decoded);
        }
    }
}