package cheems;

import java.util.Scanner;

/**
 * Represents the main class of the chatbot.
 */
public class Cheems {
    private final static Scanner scanner = new Scanner(System.in);
    private final Storage storage;

    public Cheems(String filepath) {
        this.storage = Storage.getInstance(filepath);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        UI.showWelcomeMsg();
        storage.loadData();
        String input = UI.getInput(scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                Parser.parseAndExecute(input);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
            input = UI.getInput(scanner);
        }

        UI.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems("data.txt");
        cheems.run();
    }
}
