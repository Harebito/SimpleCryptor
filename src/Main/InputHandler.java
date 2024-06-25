package Main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

//    public static List<String> readFromConsole() {
//        List<String> inputs = new ArrayList<>();
//        System.out.println("Enter strings to process (enter an empty line to finish): ");
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.isEmpty()) {
//                break;
//            }
//            inputs.add(input);
//        }
//        return inputs;
//    }

    public static List<String> readFromFile(String filename) {
        List<String> inputs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader((new FileReader(filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return inputs;
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
//    private static List<String> getInputs() {
//        String input = InputHandler.getInput("Enter a string to process or a filename (including extension): ");
//        if (Files.exists(Paths.get(input))) {
//            return InputHandler.readFromFile(input);
//        } else {
//            return Collections.singletonList(input);
//        }
//    }
}