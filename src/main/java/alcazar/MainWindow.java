package alcazar;
import alcazar.ui.DialogBox;
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
    private static boolean isExit;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alcazar alcazar;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/Images/master.png"));
    private Image alcazarImage = new Image(this.getClass().getResourceAsStream("/Images/Alcazar.png"));

    @FXML
    public void initialize() {
        MainWindow.isExit = false;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                "Greetings my master! I'm Alcazar\n" + " What can I do for you?", alcazarImage));

    }

    public void setAlcazar(Alcazar a) {
        alcazar = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (MainWindow.isExit) {
            System.exit(0);
        }

        String input = userInput.getText();
        Response response = alcazar.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getResult(), alcazarImage)
        );
        if (response.isUserExiting()) {
            MainWindow.isExit = true;
        }
        userInput.clear();
    }
}