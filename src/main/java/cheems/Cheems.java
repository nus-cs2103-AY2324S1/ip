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
    private final UI ui;

    public Cheems(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasklist = new Tasklist(storage, ui);
        parser = new Parser(tasklist, ui);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcomeMsg();
        tasklist.loadTaskFromDatabase();
        String input = ui.getInput(scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                parser.parseAndExecute(input);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
            input = ui.getInput(scanner);
        }

        ui.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems("data.txt");
        cheems.run();
    }
}
