package duke.ui;

import duke.application.Application;
import duke.command.Command;
import duke.exception.DukeException;
import duke.message.Message;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles user interactions, input parsing, and displaying messages.
 */
public class Ui {
    private static final double GUI_WIDTH = 800.0;
    private static final double GUI_HEIGHT = 600.0;
    private final InputParser parser = new InputParser();

    private final Application application;
    private Stage primaryStage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    //GUI Window dimensions


    /**
     * Constructor for this class.
     * @param application The application that interfaces with this UI.
     */
    public Ui(Application application) {
        this.application = application;
    }

    /**
     * Parses the next line of user input into a Command object.
     *
     * @return A Command object corresponding to the parsed user input.
     * @throws DukeException If there's an issue with parsing the input or creating the command.
     */
    public Command parseLine(String line) throws DukeException {
        return parser.parseInput(line);
    }
    /**
     * Displays the provided message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(Message message) {
        dialogContainer.getChildren().addAll(message);
    }

    /**
     * Displays an error message generated from the provided DukeException.
     *
     * @param e The DukeException that triggered the error.
     */
    public void showError(DukeException e) {
        dialogContainer.getChildren().addAll(e.generateErrorMessage(e.getMessage()).fromDuke());
    }

    /**
     * Render the GUI.
     * @param stage The primary stage for the GUI.
     */
    public void renderGui(Stage stage) {
        primaryStage = stage;
        //Init text pane
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(GUI_WIDTH - 15.0, GUI_HEIGHT - 65.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        //Init dialog container
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //Init text input
        userInput = new TextField();
        userInput.setPrefWidth(GUI_WIDTH - 75.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        //Init send button
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        //Handle user events
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        //Add nodes to main layout
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);
        //Update node dimensions if window is resized
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        stage.heightProperty().addListener((observable) -> scrollPane.setPrefHeight(stage.getHeight() - 65.0));
        stage.widthProperty().addListener((observable) -> {
            scrollPane.setPrefWidth(stage.getWidth() - 15.0);
            userInput.setPrefWidth(stage.getWidth() - 75.0);
        });
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> application.kill());
    }

    /**
     *
     */
    private void handleUserInput() {
        try {
            dialogContainer.getChildren().addAll(new Message(userInput.getText()).fromUser());
            application.executeCommand(parseLine(userInput.getText()));
        } catch (DukeException de) {
            showError(de);
        }
        userInput.clear();
    }

    public void closeGui() {
        primaryStage.close();
    }
}
