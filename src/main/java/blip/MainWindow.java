package blip;

import blip.ui.BlipUI;

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

    private Blip blip;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaBlipUser.png"));
    private Image blipImage = new Image(this.getClass().getResourceAsStream("/images/DaBlip.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(getIntroMessage());
    }

    public void setBlip(Blip b) {
        blip = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blip.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, blipImage)
        );
        userInput.clear();
    }

    private DialogBox getIntroMessage() {
        String intro = BlipUI.showIntro();
        return DialogBox.getDukeDialog(intro, blipImage);
    }
}
