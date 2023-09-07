package robert.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button introButton;
    @FXML
    private Button cmdButton;
    @FXML
    private Button taskButton;
    @FXML
    private VBox introTab;
    @FXML
    private VBox cmdTab;
    @FXML
    private VBox taskTab;


    private Robert robert;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image robertImage = new Image(this.getClass().getResourceAsStream("/images/DaRobert.png"));

    @FXML
    private ImageView introPicture;


    /**
     * Initialises the GUI of Robert with some configurations.
     */
    @FXML
    public void initialize() {
        scrollPane.setFitToWidth(true);
        introPicture.setImage(new Image(this.getClass().getResourceAsStream("/images/DaSecondRobert.png")));
    }

    /**
     * Setter for Robert.
     *
     * @param robert an instance of the chatbot.
     */
    public void setRobert(Robert robert) {
        this.robert = robert;

        if (this.robert.hasReadFileWithError()) {
            dialogContainer.getChildren().add(
                    DialogBox.getRobertDialog(robert.getErrorReadingFileMessage(), robertImage)
            );
        }
        dialogContainer.getChildren().add(
                DialogBox.getRobertDialog(robert.getWelcomeMessage(), robertImage)
        );
    }

    /**
     * Handles user input by funnelling it into Robert. Clears the user input after processing.
     * Gives slight delay when exit is called.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = robert.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRobertDialog(response, robertImage)
        );
        if (!this.robert.isRunning()) {
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

        // manually layout scrollPane for scrolling to the bottom.
        scrollPane.applyCss();
        scrollPane.layout();
        scrollPane.setVvalue(1.0);

        userInput.clear();
    }

    /**
     * Show the introduction tab in GUI.
     */
    @FXML
    private void showIntroduction() {
        cmdButton.toFront();
        introButton.toFront();
        introTab.toFront();
    }

    /**
     * Show the commands tab in GUI.
     */
    @FXML
    private void showCommands() {
        cmdButton.toFront();
        cmdTab.toFront();
    }

    /**
     * Show the tasks tab in GUI.
     */
    @FXML
    private void showTasks() {
        cmdButton.toFront();
        taskButton.toFront();
        taskTab.toFront();
    }
}
