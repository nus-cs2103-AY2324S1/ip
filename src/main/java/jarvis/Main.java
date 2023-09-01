package jarvis;

import java.util.Scanner;

public class Main {
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