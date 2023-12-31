package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String EXIT_MESSAGE = "Bye. Have a productive day and see you soon!";

    // FXML-injected fields
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    // Other fields
    private Duke duke;
    private final Image userImage = loadImage("/images/user.png");
    private final Image serverImage = loadImage("/images/server.png");

    /**
     * Initializes the DialogBox and displays the welcome message.
     */
    @FXML
    public void initialize() {
        bindScrollPane();
        displayWelcomeMessage();
    }

    private void bindScrollPane() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void displayWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getServerDialog(
                "Hey there! This is DaDaYuan, your personal task manager."
                        + "\nWhat can I do for you?", serverImage)
        );
    }

    /**
     * Sets the Duke instance for this window.
     * <p>
     * Assumes that the provided Duke instance is not null.
     * </p>
     *
     * @param duke The Duke instance.
     */
    public void setDuke(Duke duke) {
        assert duke != null : "Duke instance should not be null.";
        this.duke = duke;
    }

    /**
     * Processes user input: displays user message, gets and displays Duke's reply, and handles commands.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        addToDialog(input, response);
        userInput.clear();

        if (EXIT_MESSAGE.equals(response)) {
            exitPlatform();
        }
    }

    private void addToDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getServerDialog(response, serverImage)
        );
    }

    private void exitPlatform() {
        Platform.exit();
    }

    private Image loadImage(String path) {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(path)));
    }
}
