package duke;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * The Duke class represents a task management application that allows users to interact with tasks.
 * It provides methods to manage tasks, mark them as done, and perform various operations on tasks.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final String filePath = "./src/main/data/tasklist.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke instance with the specified file path to load task data from.
     */
    public Duke() {
        this.ui = new Ui();
        Storage storage = new Storage(this.filePath);
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
        new Duke().startChat();
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
                } else if (parser.find()) {
                    tasks.handleFind(userInput);
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

    public String startChat(String userInput) {
        Parser parser = new Parser();
        parser.setUserInput(userInput);
        while (true) {
            try {
                if (parser.bye()) {
                    break;
                }
                if (parser.list()) {
                    return tasks.printFileContents();
                } else if (parser.mark()) {
                    return tasks.mark(userInput);
                } else if (parser.unMark()) {
                    return tasks.unMark(userInput);
                } else if (parser.delete()) {
                    return tasks.delete(userInput);
                } else if (parser.todo()) {
                    return tasks.handleTodo(userInput);
                } else if (parser.deadline()) {
                    return tasks.handleDeadline(userInput);
                } else if (parser.event()) {
                    return tasks.handleEvent(userInput);
                } else if (parser.find()) {
                    return tasks.handleFind(userInput);
                } else {
                    throw new DukeException("Error: Invalid Command!");
                }
            } catch (DukeException exception) {
                return Ui.line + exception.getMessage() + "\n" + Ui.line;
            }
        }
        ui.goodbye();
        parser.goodbye();
        return "yo";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return startChat(input);
    }
}
