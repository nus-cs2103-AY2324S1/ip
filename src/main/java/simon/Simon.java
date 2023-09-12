package simon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simon.command.Parser;
import simon.task.Task;

/**
 * Represents the main class for the {@code Simon} application.
 * The class manages the UI and processes user inputs, interacting with the task list and storage.
 */
public class Simon extends Application {

    /** List of tasks maintained by the {@code Simon} application. */
    private TaskList tasks;

    /** Storage handler for saving and loading tasks from a file. */
    private final Storage storage;

    /** UI handler for displaying messages and prompts to the user. */
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes a new instance of the {@code Simon} application with the default file path.
     */
    public Simon() {
        this("data/simon.txt"); // use default filePath
    }

    /**
     * Constructs a new {@code Simon} application instance.
     *
     * @param filePath Path to the file where tasks will be saved and loaded from.
     */
    public Simon(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty";
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SimonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes the given input command and performs the necessary actions.
     *
     * @param inData The input command string from the user.
     */
    public void run2(String inData) {
        assert inData != null && !inData.trim().isEmpty() : "Input data should not be null or empty";
        ui.showWelcome();

        Parser.Command command = Parser.parseCommand(inData.split(" ")[0]);

        try {
            switch (command) {
            case LIST:
                ui.listTasks(tasks);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newTask = Parser.parseAddTask(inData, command, tasks);
                tasks.addTask(newTask);
                storage.save(tasks.getAllTasks());
                ui.showAddedTask(newTask, tasks.getTaskCount());
                break;
            case UNMARK:
                Task unmarkedTask = tasks.markTask(inData, false);
                storage.save(tasks.getAllTasks());
                ui.showMarkedTask(false, unmarkedTask);
                break;
            case MARK:
                Task markedTask = tasks.markTask(inData, true);
                storage.save(tasks.getAllTasks());
                ui.showMarkedTask(true, markedTask);
                break;
            case DELETE:
                Task deletedTask = tasks.deleteTask(inData);
                storage.save(tasks.getAllTasks());
                ui.showDeletedTask(deletedTask, tasks.getTaskCount());
                break;
            case FIND:
                TaskList matchedTasks = tasks.findTasks(inData);
                ui.showMatchingTasks(matchedTasks);
                break;
            case BYE:
                ui.showGoodbye();
                return;
            case UNKNOWN:
            default:
                ui.showUnknownCommand();
            }
        } catch (SimonException se) {
            ui.showError(se.getMessage());
        }
    }

    /**
     * Starts the JavaFX application. This method sets up the main window and its components.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Simon");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // more code to be added here later
    }

    /**
     * Handles the user's input when a command is entered or the send button is clicked.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        assert userText != null && !userText.trim().isEmpty() : "User text should not be empty";
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Generates a response for the provided user input.
     *
     * @param input The user's input string.
     * @return The response string from the Simon application.
     */
    String getResponse(String input) {
        Ui.clearOutput();
        if (input != null) {
            run2(input);
        }
        String output = this.ui.getOutput();
        assert output != null : "Output from UI should not be null";
        return output;
    }

    /**
     * Logs messages for debugging purposes.
     *
     * @param messages An array of messages to be logged.
     */
    public void logMessages(String... messages) {
        for (String msg : messages) {
            System.out.println("[DEBUG]: " + msg);
        }
    }

}
