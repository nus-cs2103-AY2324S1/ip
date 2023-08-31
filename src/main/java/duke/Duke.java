package duke;
import java.io.FileNotFoundException;

/**
 * The Duke class represents a task management application that allows users to interact with tasks.
 * It provides methods to manage tasks, mark them as done, and perform various operations on tasks.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Duke instance with the specified file path to load task data from.
     *
     * @param filePath The path to the file containing task data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * The main method that starts the Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./src/main/data/tasklist.txt").startChat();
    }

    /**
     * Starts the chat interface for interacting with the Duke application.
     * Manages user inputs and performs corresponding actions on tasks.
     */
    public void startChat() {
        ui.greet();
        Parser parser = new Parser();
        String userInput = parser.getUserInput();
        parser.setUserInput(userInput);
        while (true) {
            try {
                if (parser.bye()) {
                    break;
                }
                if (parser.list()) {
                    tasks.printFileContents();
                } else if (parser.mark()) {
                    tasks.mark(userInput);
                } else if (parser.unMark()) {
                    tasks.unMark(userInput);
                } else if (parser.delete()) {
                    tasks.delete(userInput);
                } else if (parser.todo()) {
                    tasks.handleTodo(userInput);
                } else if (parser.deadline()) {
                    tasks.handleDeadline(userInput);
                } else if (parser.event()) {
                    tasks.handleEvent(userInput);
                } else {
                    throw new DukeException("Error: Invalid Command!");
                }
            } catch (DukeException exception) {
                System.out.println(Ui.line + exception.getMessage() + "\n" + Ui.line);
            }
            userInput = parser.getUserInput();
            parser.setUserInput(userInput);
        }
        ui.goodbye();
        parser.goodbye();
    }
}
