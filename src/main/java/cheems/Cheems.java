package cheems;

import java.util.Scanner;

/**
 * Represents the main class of the chatbot.
 */
public class Cheems {
    private final static Scanner scanner = new Scanner(System.in);
    private final Storage storage;
    private final Parser parser;
    private final Tasklist tasklist;

    public Cheems(String filepath) {
        storage = new Storage(filepath);
        tasklist = new Tasklist(storage);
        parser = new Parser(tasklist);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        UI.showWelcomeMsg();
        tasklist.loadTaskFromDatabase();
        String input = UI.getInput(scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                parser.parseAndExecute(input);
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
