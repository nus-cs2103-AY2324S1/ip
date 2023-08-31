package sally;
import java.util.Scanner;

/**
 * Represents the main class that interacts with the user and coordinates the program execution.
 */
public class Sally {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Sally object.
     * Initializes the user interface, storage, and task list.
     * Load tasks from the specified file path if available.
     *
     * @param filePath The file path for storing and loading tasks.
     */
    public Sally(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile().getTaskList());
        } catch (SallyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main program loop.
     * Shows a welcome message to the user.
     * Reads and processes user commands until the user types "bye".
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }

                Command command = Parser.parse(input);
                command.execute(tasks, storage, ui);
            } catch (SallyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method to start the program.
     * Creates a Sally instance with the specified file path and runs it.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Sally("data/sally.txt").run();
    }
}