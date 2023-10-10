package sidtacphi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sidtacphi.ui.Ui;
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

    private Sidtacphi sidtacphi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/SidUser.png"));
    private Image sidImage = new Image(this.getClass().getResourceAsStream("/images/Sid.png"));


    @FXML
    public void initialize() {
        assert userImage != null : "User image must be present";
        assert sidImage != null : "Sid bot's image must be present";
        
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getSidDialog(Ui.getHello(), sidImage));
    }

    public void setSid(Sidtacphi s) {
        sidtacphi = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sidtacphi.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getSidDialog(response, sidImage));
        userInput.clear();
    }
}