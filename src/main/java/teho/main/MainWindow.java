package teho.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import teho.exceptions.InvalidCommandException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    //@FXML annotation marks a private or protected member and
    // makes it accessible to FXMLdespite its modifier.
    // Without the annotation, we will have to make everything
    // public and expose our UI to unwanted changes.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TehO tehO;

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/user.png"));
    private Image tehOImage = new Image(this.getClass()
            .getResourceAsStream("/images/tehO.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String helloMessage = "Hello! I'm TehO \nWhat could I do for you today?";
        dialogContainer.getChildren().add(DialogBox.getTehODialog(helloMessage, tehOImage));
    }

    public void setTehO(TehO t) {
        tehO = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InvalidCommandException {
        String input = userInput.getText();
        String response = tehO.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTehODialog(response, tehOImage)
        );
        //clear input field
        userInput.clear();
        //Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}