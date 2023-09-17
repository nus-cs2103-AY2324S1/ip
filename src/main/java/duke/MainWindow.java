package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Duke duke;

    @FXML
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    @FXML
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays a greeting message with a logo and initial introduction in a dialog box.
     */
    public void greet() {
        String logo =
                "  ____       _       ____        _      \n"
                + " / __\"| uU  /\"\\  uU |  _\"\\ u U  /\"\\  u  \n"
                + "<\\___ \\/  \\/ _ \\/  \\| |_) |/  \\/ _ \\/   \n"
                + " u___) |  / ___ \\   |  _ <    / ___ \\   \n"
                + " |____/>>/_/   \\_\\  |_| \\_\\  /_/   \\_\\  \n"
                + "  )(  (__)\\\\    >>  //   \\\\_  \\\\    >>  \n"
                + " (__)    (__)  (__)(__)  (__)(__)  (__) \n";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(logo + "Hello! I'm Sara\n" + "What can I do for you?", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        }
    }

}
