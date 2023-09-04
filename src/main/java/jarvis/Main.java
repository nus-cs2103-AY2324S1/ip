package jarvis;

import java.util.Scanner;

/**
 * The Main class to initialise Jarvis and prompt for userInput.
 */
public class Main {
    /**
     * The main method to start Jarvis.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.start();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        try {
            while (true) {
                userInput = scanner.nextLine();
                jarvis.respond(userInput);
            }
        } finally {
            scanner.close();
        }
    }
}