package sally;
import java.util.Scanner;
import javafx.stage.Stage;

/**
 * Represents the main class that interacts with the user and coordinates the program execution.
 */
public class Sally {
    private final Storage storage;
    private TaskList tasks;
    private Stage stage;

    /**
     * Constructs a Sally object.
     * Initializes the user interface, storage, and task list.
     *
     * @param stage The stage to display the user interface.
     */
    public Sally(Stage stage) {
        this.stage = stage;
        storage = new Storage("data/sally.txt");
        try {
            tasks = new TaskList(storage.loadTasksFromFile().getTaskList());
        } catch (SallyException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main program loop.
     * Shows a welcome message to the user.
     * Reads and processes user commands until the user types "bye".
     *
     * @param input The user input.
     * @return A string indicating the program has exited.
     */
    public String execute(String input) {
        try {
            if (input.equals("bye")) {
                stage.close();
            }
            Command command = Parser.parse(input);
            String res = command.execute(tasks, storage);
            return res;
        } catch (SallyException e) {
            return e.getMessage();
        }

    }
}
