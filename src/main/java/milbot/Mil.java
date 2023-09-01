package milbot;

import java.util.Scanner;

/**
 * Mil class represents a chatbot application for managing tasks.
 */
public class Mil {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static Parser parser;

    /**
     * Constructs a new instance of the Mil chatbot.
     * Initializes the task list, user interface, storage, and parser.
     */
    public Mil() {
        ui = new Ui();
        storage = new Storage();
        taskList = storage.loadTasksFromFile();
        parser = new Parser(taskList, ui);
    }
    /**
     * The main method that starts the Mil chatbot application.
     * Reads user input and processes it until the user types "bye".
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Mil mil = new Mil();
        Scanner scanner = new Scanner(System.in);
        String input;
        ui.printWelcomeMessage();


        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                storage.saveTasksToFile(taskList);
                ui.printGoodbyeMessage();
                break;
            } else {
                parser.parseInput(input);
            }
        }

    }
}
