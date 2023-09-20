package woofwoof;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import command.Command;
import enums.FilePath;
import enums.WoofMessage;
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
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import parser.Parser;
import woof.Woof;

/**
 * The `WoofWoof` class is the main class for the Woof GUI application.
 * The `WoofWoof` class relies on the existence of the `Woof` class, as it only extends `Woof` to support GUI.
 */
public class WoofWoof extends Application {
    private static final Font FONT = loadCustomFont();

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
    private final Image user = getImageFromFilePath(FilePath.USER_DISPLAY_PICTURE);
    private final Image doggo = getImageFromFilePath(FilePath.BOT_DISPLAY_PICTURE);


    /**
     * Retrieves an image from a file path. If the file path is invalid or the resource is not found,
     * it returns an empty image.
     *
     * @param filePath The path to the image file.
     * @return An Image object loaded from the specified file path or an empty image if not found.
     */
    public Image getImageFromFilePath(FilePath filePath) {
        InputStream inputStream;
        try {
            inputStream = Objects.requireNonNull(this.getClass().getResourceAsStream(filePath.toValue()));
        } catch (NullPointerException e) {
            return getEmptyImage();
        }
        return new Image(inputStream);
    }

    /**
     * Generates an empty image with a single pixel of gray color (0.5, 0.5, 0.5, 1).
     *
     * @return An empty Image object with a single gray pixel.
     */
    public Image getEmptyImage() {
        WritableImage img = new WritableImage(1, 1);
        PixelWriter pw = img.getPixelWriter();
        pw.setColor(0, 0, new Color(0.5, 0.5, 0.5, 1));
        return img;
    }

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

        this.userInput.setFont(getFont());
        this.sendButton.setFont(getFont());
        this.clearButton.setFont(getFont());

        this.dialogArea.getChildren().addAll(
            DialogBox.getBotDialog(
                Woof.wrapText(WoofMessage.HI.toFormattedValue(), "", Woof.getChatWidth()), this.doggo
            )
        );
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WoofWoof.class.getResource(FilePath.FXML_VIEW_PATH.toValue()));
            this.scene = new Scene(fxmlLoader.load());
            setCustomCursorOnApplication();
            loadCssStyles();
            primaryStage.setScene(this.scene);
            primaryStage.setTitle(WoofMessage.WOOF_TITLE.toFormattedValue());
            primaryStage.setResizable(false);
            fxmlLoader.<WoofWoof>getController().setWoof(this.woof);
            primaryStage.show();
        } catch (IOException e) {
            System.out.printf("really oh no\n%s\n", e.getMessage());
        }
    }

    /**
     * Loads the font and sets the custom font for the class.
     */
    private static Font loadCustomFont() {
        URL fontResourceUrl = WoofWoof.class.getResource(FilePath.CUSTOM_FONT.toValue());
        return Font.loadFont(Objects.requireNonNull(fontResourceUrl).toExternalForm(), 14);
    }

    /**
     * Gets the custom font.
     */
    public static Font getFont() {
        return WoofWoof.FONT;
    }

    /**
     * Sets up and applies a custom cursor when entering a JavaFX scene and handles cursor reset on click.
     */
    private void setCustomCursorOnApplication() {
        String imagePath = FilePath.CUSTOM_CURSOR.toValue();
        Image defaultCursorImage = new Image(imagePath, 40, 40, false, true);
        ImageCursor defaultCursor = new ImageCursor(defaultCursorImage, 20, 20);
        this.scene.setCursor(defaultCursor);
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
        String message = this.userInput.getText().trim();
        if (!message.isEmpty()) {
            String response = processMessage(message);
            this.dialogArea.getChildren().addAll(
                DialogBox.getUserDialog(Woof.wrapText(message, "", Woof.getChatWidth()), this.user),
                DialogBox.getBotDialog(Woof.wrapText(response, "", Woof.getChatWidth()), this.doggo)
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
            1, TimeUnit.SECONDS
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
