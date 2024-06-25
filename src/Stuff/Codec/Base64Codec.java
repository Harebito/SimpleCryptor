package Stuff.Codec;

import Main.Main;
import java.util.Base64;
import java.util.List;

public class Base64Codec implements Main.Runner {
    public String encode(String input) {
        try {
            return Base64.getEncoder().encodeToString(input.getBytes());
        } catch (Exception e) {
            return null;
        }
    }

    public String decode(String input) {
        try {
            return new String(Base64.getDecoder().decode(input));
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