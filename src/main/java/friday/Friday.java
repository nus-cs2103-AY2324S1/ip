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
    private Scanner input;
    private Ui ui;
    private Parser parser;
    private TextField userInput;
    private VBox dialogContainer;

    /**
     * Constructs a new instance of the Friday application.
     * Initializes the user interface, task list, input scanner, storage, and parser.
     */
    public Friday() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.input = new Scanner(System.in);
        this.storage = new Storage("data/tasks.txt");
        this.parser = new Parser();
        this.userInput = new TextField();
        this.dialogContainer = new VBox();
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
        if (input.equals("hello") || input.equals("hi") || input.equals("hey")) {
            return "Hello! How can I assist you today?";
        }

        if (input.contains("thank")) {
            return "You're welcome!";
        }

        if (input.contains("what can you do")) {
            return "I can manage tasks for you! You can add tasks, mark them as done, or delete them.";
        }

        if (input.contains("bye") || input.contains("goodbye")) {
            return "Goodbye! If you need anything else, just let me know.";
        }

        return parser.processUserCommand(input, taskList, storage);
    }
}
