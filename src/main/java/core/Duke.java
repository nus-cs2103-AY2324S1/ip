package core;

import frodo.storage.Storage;
import frodo.parser.Parser;
import frodo.tasks.TaskList;
import frodo.tasks.TaskManager;
import frodo.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import visual.DialogBox;

/**
 * Duke is the main class for a task management application using a GUI.
 * It manages tasks, interacts with the user, and handles storage.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TaskManager tm;
    private final String FILE_PATH = "data/tasks.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        FIND,
        ARCHIVE,
        UNKNOWN
    }

    /**
     * Initializes a new Duke instance, sets up UI, storage, and task management components.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);  
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        tm = new TaskManager(tasks);
    }

    /**
     * Sets the properties and layout constraints for the main window.
     *
     * @param stage Primary stage of the application.
     */
    public void formatWindow(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        AnchorPane.setTopAnchor(dialogContainer, 0.0);
        AnchorPane.setLeftAnchor(dialogContainer, 0.0);
        AnchorPane.setRightAnchor(dialogContainer, 0.0);

        AnchorPane.setLeftAnchor(userInput , 0.0);
        AnchorPane.setRightAnchor(userInput, 60.0);  // leave space for sendButton
        AnchorPane.setBottomAnchor(userInput, 0.0);

        AnchorPane.setBottomAnchor(sendButton, 0.0);
        AnchorPane.setRightAnchor(sendButton, 0.0);
    }

    /**
     * Adds functionality to handle user inputs both from the TextField and Button.
     */
    public void addHandleUserInputFunctionality() {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Initializes the main components of the GUI.
     *
     * @param stage Primary stage of the application.
     */
    public void initializeComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the main application window.
     *
     * @param stage Primary stage of the application.
     */
    @Override
    public void start(Stage stage) {
        initializeComponents(stage);
        formatWindow(stage);
        addHandleUserInputFunctionality();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Processes and responds to user input, updating the UI accordingly.
     */
    private void handleUserInput() {
        assert userInput != null : "UserInput field should be initialized";
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText,user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Processes user input and returns Duke's response.
     *
     * @param input Text entered by the user.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        String response = "";
        Parser p = new Parser();
        CommandType commandType = p.getCommandType(input);
        assert commandType != CommandType.UNKNOWN : "Parsed command should always be known";
        switch (commandType) {
        case LIST:
            response += tasks.toString();
            break;
        case BYE:
            response = "Goodbye. See you again";
            storage.updateData(tasks);
            break;
        case DELETE:
            response += tm.handleDelete(input);
            break;
        case MARK:
            response += tm.handleMark(input);
            break;
        case UNMARK:
            response += tm.handleUnmark(input);
            break;
        case EVENT:
        case DEADLINE:
        case TODO:
            response += tm.addTask(input);
            break;
        case FIND:
            response += tm.findTasks(input);
            break;
        case ARCHIVE:
            response += storage.archiveTasksInNewFile(tasks);
            response += tm.clearTasks();
            response += storage.clearFile();
            break;
        case UNKNOWN:
            response += "What do you mean? Make sure you speak my language asdfjkasd!";
            break;
        }
        return "Frodo heard: " + input + "\n" + response;
    }
}