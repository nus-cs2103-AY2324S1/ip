package pippi;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pippi.ui.Ui;

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

    private Pippi pippi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser-removebg.png"));
    private Image pippiImage = new Image(this.getClass().getResourceAsStream("/images/DaPippi-removebg.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getPippiDialog(Ui.helloMessage(), pippiImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPippi(Pippi p) {
        pippi = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pippi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPippiDialog(response, pippiImage)
        );
        userInput.clear();
    }
}