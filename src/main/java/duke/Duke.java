package duke;

import java.util.ArrayList;

import duke.taskclasses.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main configuration for the Duke program.
 */
public class Duke extends Application {

    private static final double MIN_WIDTH = 400.0;
    private static final double MIN_HEIGHT = 600.0;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks are saved and loaded.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
    }

    /**
     * Activates the Duke instance by initializing the UI and loading tasks.
     *
     * @param mainWindow The main window for the Duke application.
     */
    public void activate(MainWindow mainWindow) {
        ui = new Ui(mainWindow);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Overrides the start method from Application class to set up the UI.
     *
     * @param stage The primary stage of the application.
     */
    @Override
    public void start(Stage stage) {
        initializeComponents();
        configureStage(stage);
        setComponentSizes();
        setComponentLayout();
        addEventHandlers();
    }

    /**
     * Initializes UI components for the Duke application.
     */
    private void initializeComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
    }

    /**
     * Configures the main stage of the application.
     *
     * @param stage The primary stage of the application.
     */
    private void configureStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setScene(new Scene(createMainLayout()));
        stage.show();
    }

    /**
     * Creates and returns the main layout for the application.
     *
     * @return The main AnchorPane layout.
     */
    private AnchorPane createMainLayout() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(MIN_WIDTH, MIN_HEIGHT);
        return mainLayout;
    }

    /**
     * Sets the sizes for the UI components.
     */
    private void setComponentSizes() {
        scrollPane.setPrefSize(385, 535);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Sets the layout configuration for the UI components.
     */
    private void setComponentLayout() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Adds event handlers to the UI components.
     */
    private void addEventHandlers() {
        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles the user input and updates the dialog container.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().add(createDialogLabel(userInput.getText()));
        userInput.clear();
    }

    /**
     * Creates a label with the given text for the dialog.
     *
     * @param text The text to be displayed on the label.
     * @return The created label.
     */
    private Label createDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Generates a response based on the user's input.
     *
     * @param input The user's input.
     * @return A list of strings representing the response.
     */
    ArrayList<String> getResponse(String input) {
        return Parser.run(input, ui, storage, tasks);
    }
}
