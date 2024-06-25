package Stuff.Codec;

import Main.Main;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class URLEn_Decoder implements Main.Runner {
    public String encode(String input) {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String decode(String encoded) {
        try {
            return java.net.URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
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