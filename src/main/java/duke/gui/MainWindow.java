package duke.gui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Bobi;
import duke.utility.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow class is a controller for MainWindow.
 * It provides the layout for the other controls.
 *
 * @author ruo-x
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bobi bobi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bobiImage = new Image(this.getClass().getResourceAsStream("/images/Bobi.png"));

    /**
     * Initialize the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialize Bobi, Bobi greets the user.
     *
     * @param d Bobi to initialize.
     */
    public void setBobi(Bobi d) {
        bobi = d;
        dialogContainer.getChildren().add(
                DialogBox.getBobiDialog(Ui.greeting(), bobiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bobi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobiDialog(response, bobiImage)
        );

        // if user input is "bye", GUI closes automatically after 1 second.
        if (response.equals(Ui.exit())) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    timer.cancel();
                }
            }, 1000);
        }

        userInput.clear();
    }
}
