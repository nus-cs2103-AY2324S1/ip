package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private Ui ui = new Ui();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private Image travellerImage = new Image(this.getClass().getResourceAsStream("/images/lumine.jpg"));
    private Image paimonImage = new Image(this.getClass().getResourceAsStream("/images/paimon.jpg"));

    /**
     * To initialize the GUI of the chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getPaimonDialog(ui.printWelcome(), paimonImage)
        );
    }

    /**
     * Initialise the variable duke.
     *
     * @param d The Duke object to be set to.
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getTravellerDialog(input, travellerImage),
                DialogBox.getPaimonDialog(response, paimonImage)
        );
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
        userInput.clear();
    }
}
