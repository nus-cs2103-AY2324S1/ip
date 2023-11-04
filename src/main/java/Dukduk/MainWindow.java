package dukduk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Dukduk dukduk;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukdukImage = new Image(this.getClass().getResourceAsStream("/images/DaDukduk.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukdukDialog("Quack Quack! I'm Dukduk!\nWhat can I do for you?", dukdukImage)
        );
    }

    /**
     * Sets dukduk for the main window.
     *
     * @param dukduk
     */
    public void setDukduk(Dukduk dukduk, Stage stage) {
        this.dukduk = dukduk;
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dukduk's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukdukDialog(response, dukdukImage)
        );
        userInput.clear();
    }

    /**
     * Returns the response from dukduk.
     *
     * @param input the user input
     * @return the response from dukduk
     */
    private String getResponse(String input) {
        return dukduk.reply(input, stage);
    }
}
