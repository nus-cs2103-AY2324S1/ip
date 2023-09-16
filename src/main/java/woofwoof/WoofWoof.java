package woofwoof;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import command.Command;
import enums.FilePath;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
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
import woof.Woof;

/**
 * The `WoofWoof` class is the main class for the Woof GUI application.
 * It handles user interactions and the core functionality of the application.
 */
public class WoofWoof extends Application {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogArea;
    @FXML
    private Button clearButton;
    @FXML
    private TextArea userInput;
    @FXML
    private Button sendButton;
    private Scene scene;

    private Woof woof;
    private final Image user = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream(FilePath.USER_DISPLAY_PICTURE.toValue()))
    );
    private final Image doggo = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream(FilePath.BOT_DISPLAY_PICTURE.toValue()))
    );

    /**
     * Initializes the WoofWoof application by reading the task list from a file
     * and loading the font.
     */
    @FXML
    public void initialize() {
        this.userInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleUserSubmit();
            }
        });
        this.sendButton.setOnMouseEntered(e -> this.sendButton.setStyle("-fx-base: #C1E1C1"));
        this.sendButton.setOnMouseExited(e -> this.sendButton.setStyle("-fx-base: #C5CBEC"));
        this.clearButton.setOnMouseEntered(e -> this.clearButton.setStyle("-fx-base: #FAA0A0"));
        this.clearButton.setOnMouseExited(e -> this.clearButton.setStyle("-fx-base: #C5CBEC"));
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

    /**
     * Starts the WoofWoof application.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        loadAndSetCustomFont();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WoofWoof.class.getResource("/views/WoofWoof.fxml"));
            this.scene = new Scene(fxmlLoader.load());
            loadAndSetCustomCursor();
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
     * Loads, gets, and sets a custom font.
     */
    private void loadAndSetCustomFont() {
        URL fontResource = getClass().getResource(FilePath.CUSTOM_FONT.toValue());
        Font.loadFont(Objects.requireNonNull(fontResource).toExternalForm(), 13);
    }

    /**
     * Loads, gets, and sets a custom cursor with fixed properties.
     */
    private void loadAndSetCustomCursor() {
        String imagePath = FilePath.CUSTOM_CURSOR.toValue();
        double imageWidth = 40.0;
        double imageHeight = 40.0;
        double hotspotX = 20.0;
        double hotspotY = 20.0;
        Image cursorImage = new Image(imagePath, imageWidth, imageHeight, false, true);
        ImageCursor imageCursor = new ImageCursor(cursorImage, hotspotX, hotspotY);
        this.scene.setOnMouseEntered(e -> scene.setCursor(imageCursor));
    }

    /**
     * Load CSS styles from file paths and apply them to the scene.
     */
    private void loadCssStyles() {
        String[] cssFilePaths = {
            FilePath.ROOT_CSS.toValue(),
            FilePath.SCROLL_PANE_CSS.toValue(),
            FilePath.DIALOG_AREA_CSS.toValue(),
            FilePath.CLEAR_BUTTON_CSS.toValue(),
            FilePath.USER_INPUT_CSS.toValue(),
            FilePath.SEND_BUTTON_CSS.toValue(),
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
        if (!message.trim().isEmpty()) {
            String response = processMessage(message);
            this.dialogArea.getChildren().addAll(
                DialogBox.getUserDialog(Woof.wrapText(message, "\n", 52), this.user),
                DialogBox.getBotDialog(Woof.wrapText(response, "\n", 52), this.doggo)
            );
            this.userInput.clear();
        }
        Platform.runLater(() -> Platform.runLater(()->this.scrollPane.setVvalue(1.0)));
    }

    /**
     * Handles user clear, clears the dialog area and text area.
     */
    @FXML
    private void handleUserClear() {
        this.dialogArea.getChildren().clear();
        this.userInput.clear();
    }


    /**
     * Schedule the closing of the JavaFX stage after a 1-second delay.
     */
    private void scheduleCloseAfterDelay() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> Platform.runLater(() -> {
            Stage currentStage = (Stage) this.dialogArea.getScene().getWindow();
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
        assert message != null : "message cannot be null";

        Command command = Parser.parse(message);
        if (command.isByeCommand()) {
            scheduleCloseAfterDelay();
        }
        return Woof.updateFileAndExecute(command);
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
