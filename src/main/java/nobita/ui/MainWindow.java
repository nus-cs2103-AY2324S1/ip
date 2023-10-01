package nobita.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nobita.Nobita;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Zheng Chenglong
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
    private Nobita nobita;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image NobitaImage = new Image(this.getClass().getResourceAsStream("/images/DaNobita.jpg"));

    /**
     * Initialize the GUI of Nobita.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Nobita.
     *
     * @param nobita An instance of Nobita chatbot.
     */
    public void setNobita(Nobita nobita) {
        this.nobita= nobita;
        onStart();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nobita's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nobita.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNobitaDialog(response, NobitaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Greet the user when Nobita is launched.
     */
    @FXML
    private void onStart() {
        dialogContainer.getChildren().addAll(
                DialogBox.getNobitaDialog(Ui.showWelcome(), NobitaImage)
        );
    }
}
