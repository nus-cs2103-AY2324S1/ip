package friday;

import java.util.Scanner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Represents the main class for the Friday application.
 */
public class Friday {
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs a new instance of the Friday application.
     * Initializes the user interface, task list, input scanner, storage, and parser.
     */
    public Friday() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/tasks.txt");
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Generates a response based on user input.
     * @param input The user's input string.
     * @return A response string.
     */
    public String getResponse(String input) {
        input = input.toLowerCase().trim();


        return parser.processUserCommand(input, taskList, storage);
    }
}
