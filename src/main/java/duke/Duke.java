package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;

/**
 * Main to class to handle duke operations
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.handleReadAllTasksFromFile());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        if (input.equals("bye")) {
            return input;
        }
        try {
            String[] commandType = parser.handleUserInput(input);
            String str = handleCommand(commandType[0], commandType[1]);
            storage.handleChangesInFile(tasks.getTasks());
            return str;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to get the user input and run the respective methods.
     */
    public void run() {
        ui.greet();
        Scanner obj = new Scanner(System.in);
        while (true) {
            try {
                String userInput = obj.nextLine();

                if (userInput.equals("bye")) {
                    break;
                }

                String[] commandType = parser.handleUserInput(userInput);
                ui.print(handleCommand(commandType[0], commandType[1]));
                storage.handleChangesInFile(tasks.getTasks());
            } catch (DukeException | IOException | InvalidInputExpression e) {
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
    }

    /**
     * Method to handle input
     * @param command command of user
     * @param input inout of user
     * @return String representing the process performed
     * @throws DukeException
     * @throws IOException
     */

    public String handleCommand(String command, String input) throws DukeException, IOException {
        if (input.equals("")) {
            throw new DukeException("Invalid Command!");
        }

        switch (command) {
        case "mark":
            return tasks.markTask(input);
        case "unmark":
            return tasks.unmarkTask(input);
        case "list":
            return tasks.getAllToDo();
        case "todo":
            return tasks.handleTodoTask(input, "user");
        case "deadline":
            return tasks.handleDeadlineTask(input, "user");
        case "event":
            return tasks.handleEventTask(input, "user");
        case "delete":
            return tasks.deleteTask(input);
        case "find":
            return tasks.handleFindTask(input);
        default:
            throw new DukeException("Invalid Command!");
        }
    }
}
