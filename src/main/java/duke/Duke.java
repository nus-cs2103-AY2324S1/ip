package duke;
import duke.exceptions.DukeException;
import duke.gui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * duke.Main class for the Duke application.
 * This class handles user interactions and manages tasks using the Archive class.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Head.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Rickmorty.png"));

    /**
     * Constructs a Duke instance with the specified file path.
     */
    public Duke() {
        storage = new Storage("data/saved.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Initializes and configures the JavaFX application's user interface.
     * This method sets up the main application window, UI components, and event handlers.
     *
     * @param stage The primary stage representing the main application window.
     */
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

        assert scene != null : "scene must be instantiated before stage can set scene";
        stage.setScene(scene);
        stage.show();

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

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

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String dukeProcessedText = getResponse(input);
        assert dukeProcessedText.length() > 0 : "processed text should not be empty";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getDukeDialog(dukeProcessedText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Processes the input command and generates a response.
     *
     * @param input The input command to be processed.
     * @return A String containing the response generated based on the input.
     */
    public String getResponse(String input) {
        String[] parsedText = Parser.parse(input, tasks);
        String response = manipulateTasks(parsedText);
        storage.save(tasks);
        return response;
    }

    /**
     * This method performs various tasks based on the input command and arguments.
     *
     * @param parsedText An array of strings representing the parsed input command and arguments.
     * @return A string containing the result of the operation or an error message.
     * @throws DukeException If an error occurs during task manipulation, a DukeException is thrown.
     */
    public String manipulateTasks(String[] parsedText) {
        tasks.pushCurr();
        try {
            switch (parsedText[0]) {
            case "undo":
                return tasks.undo();
            case "mark":
                return tasks.markTask(Integer.parseInt(parsedText[1]));
            case "unmark":
                return tasks.unmarkTask(Integer.parseInt(parsedText[1]));
            case "delete":
                return tasks.deleteTask(Integer.parseInt(parsedText[1]));
            case "find":
                return tasks.find(parsedText[1]);
            case "list":
                return tasks.getAll();
            case "exception":
                return parsedText[1];
            case "todo":
            case "deadline":
            case "event":
                return tasks.addTask(parsedText);
            default:
                return "Invalid input";
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

