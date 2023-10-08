package robert.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import robert.Robert;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Lee Zhan Peng
 */
public class MainWindow extends AnchorPane {
    private static final int MAX_INPUT_LENGTH = 200;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ImageView displayPicture;

    private Robert robert;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image robertImage = new Image(this.getClass().getResourceAsStream("/images/DaRobert.png"));

    /**
     * Initialises the GUI of Robert with some configurations.
     */
    @FXML
    public void initialize() {
        displayPicture.setImage(new Image(this.getClass().getResourceAsStream("/images/DaRobert.png")));
    }

    /**
     * Sets Robert as the chatbot.
     *
     * @param robert an instance of the chatbot.
     */
    public void setRobert(Robert robert) {
        this.robert = robert;

        if (this.robert.hasReadFileWithError()) {
            displayRobertTextBubble(robert.getErrorReadingFileMessage());
        }

        displayRobertTextBubble(robert.getWelcomeMessage());
    }

    /**
     * Handles user input by funnelling it into Robert. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = robert.getResponse(input);

        if (!response.isEmpty()) {
            displayUserTextBubble(input);
            displayRobertTextBubble(response);
        }

        scrollDown();
        userInput.clear();

        if (!this.robert.isRunning()) {
            initialiseClosingSequence();
            return;
        }
    }

    /**
     * Displays command written by user.
     */
    @FXML
    private void displayUserTextBubble(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(text, userImage)
        );
    }

    /**
     * Displays reply by Robert.
     */
    @FXML
    private void displayRobertTextBubble(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getRobertDialog(text, robertImage)
        );
    }

    /**
     * Initialises buffer time for exiting GUI.
     */
    @FXML
    private void initialiseClosingSequence() {
        userInput.setVisible(false);
        new Thread(() -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.exit();
        }).start();
    }

    /**
     * Scrolls down the GUI for new text bubbles.
     */
    @FXML
    private void scrollDown() {
        scrollPane.applyCss();
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }

    /**
     * Restricts the input length of user commands.
     */
    @FXML
    private void restrictInputLength() {
        String input = userInput.getText();

        if (input.length() > MAX_INPUT_LENGTH) {
            userInput.setText(input.substring(0, MAX_INPUT_LENGTH));
            userInput.positionCaret(MAX_INPUT_LENGTH);
        }
    }
}
