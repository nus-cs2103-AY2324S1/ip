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
     * Load tasks from the specified file path if available..
     */
    public Sally() {
        ui = new Ui();
        storage = new Storage("data/sally.txt");
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
                String response = getResponse(input);
                System.out.println(response);
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
        new Sally().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        return "Sally heard: " + input;
    }
}
