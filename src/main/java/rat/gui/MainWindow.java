package rat.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rat.Rat;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * @author Keagan
 */
public class MainWindow extends AnchorPane {

    /**
     * The scroll pane containing the dialog container.
     */
    @FXML
    private ScrollPane scrollPane;

    /**
     * The dialog container containing all the dialog boxes.
     */
    @FXML
    private VBox dialogContainer;

    /**
     * The text field for user input.
     */
    @FXML
    private TextField userInput;

    /**
     * The button to send user input.
     */
    @FXML
    private Button sendButton;

    /**
     * The Rat instance.
     */
    private Rat rat;

    /**
     * The display image of the user.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));

    /**
     * The display image of Rat.
     */
    private Image ratImage = new Image(this.getClass().getResourceAsStream("/images/Megamind.jpeg"));

    /**
     * Initializes the scroll pane to scroll to the bottom of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Rat instance to be used by the GUI.
     * @param rat The Rat instance to be used by the GUI.
     */
    public void setRat(Rat rat) {
        this.rat = rat;
        assert(rat != null);
        dialogContainer.getChildren().addAll(
                LeftDialogBox.getRatDialog(rat.getWelcome(), ratImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rat's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isBlank()) {
            String response = rat.getResponse(input);
            assert userImage != null : "User image should not be null";
            assert ratImage != null : "Rat image should not be null";
            dialogContainer.getChildren().addAll(
                    RightDialogBox.getUserDialog(input, userImage),
                    LeftDialogBox.getRatDialog(response, ratImage)
            );
        }
        userInput.clear();
    }
}
