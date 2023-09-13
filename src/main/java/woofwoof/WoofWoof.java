package woofwoof;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import command.Command;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import parser.Parser;
import storage.TaskFileHandler;
import tasks.TaskList;
import woof.Woof;

/**
 * The `WoofWoof` class is the main class for the Woof GUI application.
 * It handles user interactions and the core functionality of the application.
 */
public class WoofWoof extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextArea userInput;
    @FXML
    private Button sendButton;

    private Scene scene;

    private TaskList taskList;

    private Woof woof;
    private final Image user = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/userDisplayPicture.jpeg"))
    );
    private final Image doggo = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/botDisplayPicture.jpeg"))
    );

    /**
     * Initializes the WoofWoof application by reading the task list from a file
     * and loading the font.
     */
    @FXML
    public void initialize() {
        this.taskList = TaskFileHandler.readFromFile();
        this.userInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleUserSubmit();
            }
        });
        this.sendButton.setOnMouseClicked(event -> handleUserSubmit());
    }

    /**
     * Sets the Woof instance for the application.
     *
     * @param w The Woof instance to set.
     */
    public void setWoof(Woof w) {
        this.woof = w;
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(WoofWoof.class.getResource("/views/WoofWoof.fxml"));
        URL fontResource = getClass().getResource("/fonts/sono/static/sono-light.ttf");
        Font.loadFont(Objects.requireNonNull(fontResource).toExternalForm(), 13);
        this.dialogContainer = new VBox();
        this.scrollPane = new ScrollPane();
        this.userInput = new TextArea();
        this.sendButton = new Button();

        try {
            this.scene = new Scene(fxmlLoader.load());
            loadCssStyles();
            primaryStage.setScene(this.scene);
            primaryStage.setTitle("Woof Woof");
            primaryStage.setResizable(false);
            fxmlLoader.<WoofWoof>getController().setWoof(this.woof);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Load CSS styles from file paths and apply them to the scene.
     */
    private void loadCssStyles() {
        String[] cssFilePaths = {
            "/styles/woof.css",
            "/styles/scrollPane.css",
            "/styles/userInput.css",
            "/styles/dialogContainer.css",
            "/styles/sendButton.css",
        };
        for (String cssFilePath : cssFilePaths) {
            String css = Objects.requireNonNull(getClass().getResource(cssFilePath)).toExternalForm();
            this.scene.getStylesheets().add(css);
        }
    }

    /**
     * Handles user submit, generates responses, and updates the dialog.
     */
    @FXML
    private void handleUserSubmit() {
        String message = this.userInput.getText();
        if (!message.isEmpty()) {
            String response = processMessage(message);
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(message, this.user),
                    DialogBox.getBotDialog(response, this.doggo)
            );
            this.userInput.clear();
        }
        Platform.runLater(() -> this.scrollPane.setVvalue(1.0));
    }

    /**
     * Schedule the closing of the JavaFX stage after a 1-second delay.
     */
    private void scheduleCloseAfterDelay() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> Platform.runLater(() -> {
            Stage currentStage = (Stage) this.dialogContainer.getScene().getWindow();
            currentStage.close();
            executorService.shutdown();
        }),
            2, TimeUnit.SECONDS
        );
    }

    /**
     * Processes the message and generates a response to user input.
     *
     * @param message The user's message.
     * @return The response message.
     */
    private String processMessage(String message) {
        Command command = Parser.parse(message);
        if (command.isByeCommand()) {
            scheduleCloseAfterDelay();
        }
        return updateFileAndExecute(command);
    }

    /**
     * Updates the task list by executing a command, saving the file, and returning the result.
     *
     * @param command The command to be executed.
     * @return The result message of executing the command.
     */
    private String updateFileAndExecute(Command command) {
        this.taskList = TaskFileHandler.readFromFile();
        String result = command.execute(this.taskList);
        TaskFileHandler.saveToFile(this.taskList);
        return result;
    }

    /**
     * The main entry point of the WoofWoof application.
     *
     * @param args The command-line arguments (not used in JavaFX applications).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
