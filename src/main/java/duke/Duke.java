package duke;

import GUI.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * The Duke class represents a command-line chatbot that allows users to manage their tasks. Users can interact
 * with Duke by providing various commands to add, list, mark, unmark, delete, and save tasks. Duke also stores
 * tasks in a file for persistence across sessions.
 *
 * Duke supports the following commands:
 * - "bye": Exits the chatbot and saves the task list to a file.
 * - "list": Lists all tasks in the current task list.
 * - "mark <task_id>": Marks a task as done by its ID.
 * - "unmark <task_id>": Unmarks a previously marked task.
 * - "todo <description>": Adds a to-do task with a description.
 * - "event <description> /from <datetime> /to <datetime>": Adds an event task with a description, start date, and end date.
 * - "deadline <description> /by <datetime>": Adds a deadline task with a description and due date.
 * - "delete <task_id>": Deletes a task by its ID.
 *
 * Duke also provides a graphical user interface (GUI) using JavaFX for a more interactive user experience.
 * Users can input commands in a text field, and Duke's responses are displayed in a scrollable chat interface.
 * The GUI version of Duke includes a send button for user input and displays user and Duke avatars for each message.
 *
 * Duke's chat interface includes support for features like listing tasks, marking tasks as done, adding tasks,
 * deleting tasks, and searching for tasks. Users can also exit the chatbot with the "bye" command.
 *
 * Duke stores tasks in a file to ensure persistence across sessions, and it can load tasks from this file when
 * initialized.
 *
 * @author LuoZYi
 */
public class Duke extends Application {
    private final String filePath = "./data/duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    private AnchorPane mainLayout = new AnchorPane();
    private Scene scene = new Scene(mainLayout);


    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String chatBotResponse;

    /**
     * Constructs a new Duke instance.
     * Initializes the user interface (UI), storage, task list, and parser components. It also attempts to load
     * tasks from a file. If loading fails, it creates an empty task list.
     */
    public Duke() {
        ui = new Ui(scrollPane, dialogContainer, userInput, sendButton, scene, mainLayout);
        storage = new Storage(filePath);
        parser = new Parser();

        // Ensure the file path is not empty
        assert filePath != null && !filePath.isEmpty() : "File path must not be empty";

        // Ensure the storage, UI, and taskList are properly initialized
        assert storage != null : "Storage must be initialized";
        assert ui != null : "UI must be initialized";
        assert taskList != null : "TaskList must be initialized";
        try {
            taskList = new TaskList(storage.load());
        } catch (CustomException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Initializes the JavaFX graphical user interface (GUI) for Duke.
     * Sets up the chat interface, input field, and send button. It also configures the appearance of
     * the GUI window and sets event handlers for user interactions.
     *
     * @param stage The JavaFX stage where the GUI is displayed.
     */
    public void start(Stage stage) {

        ui.start(stage);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }




    /**
     * Handles user input in the GUI.
     * This method processes user input, updates the chat interface with user and Duke's responses,
     * and clears the input field for the next user message.
     */

    private void handleUserInput() {
        String userInputText = userInput.getText();

        // Ensure userInputText is not null
        assert userInputText != null : "User input text must not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputText, user),
                DialogBox.getDukeDialog(getResponse(userInputText), duke)
        );

        userInput.clear();
    }

    /**
     * Retrieves Duke's response for a given user input.
     * This method determines the type of user command, processes it, and generates a response.
     * It handles various commands such as listing tasks, marking tasks as done, adding tasks, and more.
     *
     * @param input The user's input command.
     * @return The response generated by Duke.
     */
    public String getResponse(String input) {
        Parser.CommandType commandType = parser.parseCommandType(input);
        CommandManager commandManager = new CommandManager(ui, input, taskList, parser, storage);
        return commandManager.execute(commandType);
    }

    /**
     * Exits the Duke chatbot and saves tasks before shutting down.
     * This method is called when the user enters the "bye" command. It saves the task list to a file
     * and exits the program gracefully.
     */
    public void exit() {
        try {
            // Ensure storage and taskList are initialized
            assert storage != null : "Storage must be initialized";
            assert taskList != null : "TaskList must be initialized";
            storage.save(taskList.getTaskArrayList());
            chatBotResponse = ui.exit();
        } catch (CustomException e) {
            chatBotResponse = "Error saving tasks: " + e.getMessage();
        }
        System.exit(0);
    }



}
