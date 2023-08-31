package bee;

import java.util.Scanner;

/**
 * The main class representing the chatbot application.
 * Orchestrates the initialization, execution, and termination of the chatbot.
 */
public class Bee {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DATA_FILE_PATH = "./data/bee.txt";

    /**
     * Constructs a new Bee chatbot instance.
     * Initializes the user interface, storage, and task list based on the provided
     * data file path.
     *
     * @param filePath The path to the data file for storing tasks.
     */
    public Bee(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (BeeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the execution of the chatbot.
     * Displays a greeting, reads user input, and processes commands until the
     * chatbot is terminated.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            String userInput = scanner.nextLine();
            isRunning = Parser.parseUserCommand(userInput, tasks, storage, ui);
        }
        scanner.close();
    }

    /**
     * The entry point of the chatbot application.
     * Creates a new Bee instance and starts its execution.
     *
     * @param args The command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Bee(DATA_FILE_PATH).run();
    }
}