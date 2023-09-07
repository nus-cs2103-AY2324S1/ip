package aj;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Aj aj;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAj(Aj d) {
        aj = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
//        String input = userInput.getText();
//        String response = aj.getResponse(input);
//        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input,
//                        userImage),
//                DialogBox.getDukeDialog(response,
//                        dukeImage));
//        userInput.clear();

        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(aj.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText,
                        new ImageView(userImage)),
                DialogBox.getAjDialog(dukeText,
                        new ImageView(dukeImage)));
        userInput.clear();
    }
}

