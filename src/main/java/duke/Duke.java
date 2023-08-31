package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Duke class represents a simple task management chatbot application.
 * It allows users to interact with a list of tasks and store them in a file.
 */
public class Duke {
    private static final String DATA_FILE_PATH = "./data/duke.txt";

    private final Storage storage;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filepath The path to the file used for storing tasks.
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
    }

    /**
     * Runs the Duke application, allowing users to interact with tasks.
     * This method displays a welcome message, creates a data directory if it doesn't exist,
     * loads tasks from the storage, and repeatedly prompts the user for input until "bye" is entered.
     */
    public void run() {
        // Welcome
        Ui.showWelcome();

        // Creates data folder if doesn't exist
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdir()) {
                System.out.println("        Data directory created successfully.");
            } else {
                System.out.println("        Failed to create data directory.");
            }
            Ui.showLine();
        }

        // Check user input
        Scanner sc = new Scanner(System.in);
        List<Task> userList = new ArrayList<>();
        TaskList taskList = new TaskList(userList);

        this.storage.loadTasks(taskList); // Load tasks to list

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // Exit the loop if the user types "bye"
                storage.saveTasks(taskList);
                Ui.showExit();
                break;
            }

            Parser.parseUserInput(userInput, taskList);
        }
    }

    /**
     * The main method to start the Duke application.
     * Creates an instance of Duke with the data file path and runs the application.
     */
    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}