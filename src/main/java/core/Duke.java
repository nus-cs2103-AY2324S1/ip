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

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
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
    public void formatWindow(Stage stage) {
        //Step 2. Formatting the window to look as expected
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
        AnchorPane.setBottomAnchor(dialogContainer, 40.0);

        AnchorPane.setLeftAnchor(userInput , 0.0);
        AnchorPane.setRightAnchor(userInput, 60.0);  // leave space for sendButton
        AnchorPane.setBottomAnchor(userInput, 0.0);

        AnchorPane.setBottomAnchor(sendButton, 0.0);
        AnchorPane.setRightAnchor(sendButton, 0.0);
    }

    public void addHandleUserInputFunctionality() {
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