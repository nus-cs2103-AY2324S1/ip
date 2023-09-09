import javafx.application.Platform;
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

    private Veda veda;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image vedaImage = new Image(this.getClass().getResourceAsStream("/images/Veda.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setVeda(Veda v) {
        veda = v;
        dialogContainer.getChildren().add(DialogBox.getVedaDialog(v.initialise(), vedaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        //Retrieve user input
        String input = userInput.getText();
        if (input.equals("")) {
            //User enters an empty input
            return;
        }

        //Parse and handle the user input and get a response from the sytem
        String response = veda.getResponse(input);

        //Update frontend to reflect the new convo
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getVedaDialog(response, vedaImage)
        );

        //Clear the text field for the next input text
        userInput.clear();

        if (response.equals("Bye. All the best for your mission!")) {
            Platform.exit();
        }
    }
}
