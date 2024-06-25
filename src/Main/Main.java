package Main;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public interface Runner {
        void run(List<String> inputs);
    }

    public static void main(String[] args) {
        Map<String, String> options = getStringStringMap();

        while (true) {
            System.out.println("Choose from the following options:");
            System.out.println("1. Base64 (Codec)");
            System.out.println("2. Hex (Codec)");
            System.out.println("3. URL (Codec)");
            System.out.println("4. MD5 (Hash)");
            System.out.println("5. SHA-1 (Hash)");
            System.out.println("6. SHA-256 (Hash)");
            System.out.println("7. SHA-512 (Hash)");
            System.out.println("8. CRC32 (Checksum)");
            System.out.println("9. AES Encrypt (Algorithm)");
            System.out.println("10. AES Decrypt (Algorithm)");
            System.out.println("11. Blowfish Encrypt (Algorithm)");
            System.out.println("12. Blowfish Decrypt (Algorithm)");

            String choice = InputHandler.getInput("Enter your choice (1-12 or package name)");



            String className = options.get(choice);
            if (className != null) {
                try {
                    Class<?> clazz = Class.forName(className);
                    Runner runner = (Runner) clazz.getDeclaredConstructor().newInstance();
                    List<String> inputs = getInputs(choice);
                    runner.run(inputs);
                    break; // Exit the loop if execution is successful
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                         | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    System.out.println("Error executing choice. Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
            if ("9".equals(choice) || "11".equals(choice)) {
                System.out.println("Available key lengths for AES and Blowfish encryption: 128, 192, 256 bits");
            }
            if ("10".equals(choice) || "12".equals(choice)) {
                System.out.println("Enter the key in Base64 format");
            }
        }

        InputHandler.close();
    }

    private static Map<String, String> getStringStringMap() {
        Map<String, String> options = new HashMap<>();
        options.put("1", "Stuff.Codec.Base64Codec");
        options.put("base64", "Stuff.Codec.Base64Codec");
        options.put("2", "Stuff.Codec.HexCodec");
        options.put("hex", "Stuff.Codec.HexCodec");
        options.put("3", "Stuff.Codec.URLEn_Decoder");
        options.put("url", "Stuff.Codec.URLEn_Decoder");
        options.put("4", "Stuff.Hash.MD5Hasher");
        options.put("md5", "Stuff.Hash.MD5Hasher");
        options.put("5", "Stuff.Hash.SHA1Hasher");
        options.put("sha-1", "Stuff.Hash.SHA1Hasher");
        options.put("6", "Stuff.Hash.SHA256Hasher");
        options.put("sha-256", "Stuff.Hash.SHA256Hasher");
        options.put("7", "Stuff.Hash.SHA512Hasher");
        options.put("sha-512", "Stuff.Hash.SHA512Hasher");
        options.put("8", "Stuff.Hash.CRC32Checksum");
        options.put("crc32", "Stuff.Hash.CRC32Checksum");
        options.put("9", "Stuff.Algorithm.AESEncryptor");
        options.put("aes", "Stuff.Algorithm.AESEncryptor");
        options.put("10", "Stuff.Algorithm.AESDecryptor");
        options.put("aes decrypt", "Stuff.Algorithm.AESDecryptor");
        options.put("11", "Stuff.Algorithm.BlowfishEncryptor");
        options.put("blowfish", "Stuff.Algorithm.BlowfishEncryptor");
        options.put("12", "Stuff.Algorithm.BlowfishDecryptor");
        options.put("blowfish decrypt", "Stuff.Algorithm.BlowfishDecryptor");
        return options;
    }

    private static List<String> getInputs(String choice) {
        if ("9".equals(choice) || "10".equals(choice) || "11".equals(choice) || "12".equals(choice)) {
            System.out.println("For options 9-12, input should be in the format: <string> <key>");
        }
        String input = InputHandler.getInput("Enter a string to process or a filename (including extension): ");
        if (Files.exists(Paths.get(input))) {
            return InputHandler.readFromFile(input);
        } else {
            return Arrays.asList(input.split(" "));
        }
    }
}