package core;
import Duke.storage.Storage;
import Duke.parser.Parser;
import Duke.tasks.TaskList;
import Duke.tasks.TaskManager;
import Duke.ui.Ui;
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
import javafx.scene.layout.Region;
import visual.DialogBox;


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
        UNKNOWN
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }
    

    public Duke(String filePath) {
        assert filePath != null : "File path cannot be null";
        ui = new Ui();
        storage = new Storage(filePath);  
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        tm = new TaskManager(tasks);
    }

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

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            String userCommand = ui.readUserCommand();
            Parser p = new Parser();
            CommandType commandType = p.getCommandType(userCommand);
            assert commandType != CommandType.UNKNOWN : "Parsed command should always be known";
            switch (commandType) {
                case LIST:
                    ui.showTasks(tasks);
                    break;
                case BYE:
                    ui.showGoodbye();
                    storage.updateData(tasks);
                    isRunning = false;
                    break;
                case DELETE:
                    ui.showMessage(tm.handleDelete(userCommand));
                    break;
                case MARK:
                    ui.showMessage(tm.handleMark(userCommand));
                    break;
                case UNMARK:
                    ui.showMessage(tm.handleUnmark(userCommand));
                    break;
                case EVENT:
                case DEADLINE:
                    case TODO:
                    ui.showMessage(tm.addTask(userCommand));
                    break;
                case FIND:
                    ui.showMessage(tm.findTasks(userCommand));
                    break;
                case UNKNOWN:
                    ui.showError("unknown command.");
                    break;    
            }
        }
    }

    @Override
    public void start(Stage stage) {
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
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

          //Step 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
        dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
        userInput.clear();
    });

    userInput.setOnAction((event) -> {
        dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
        userInput.clear();
    });

    //Scroll down to the end every time dialogContainer's height changes.
    dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    //Part 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
        handleUserInput();
    });

    userInput.setOnAction((event) -> {
        handleUserInput();
    });

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
 * You should have your own function to generate a response to user input.
 * Replace this stub with your completed method.
 */
public String getResponse(String input) {
    return "Duke heard: " + input;
}



    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}