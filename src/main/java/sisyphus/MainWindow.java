package sisyphus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sisyphus.ui.Ui;

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

    private Sisyphus sisyphus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image sisyphusImage = new Image(this.getClass().getResourceAsStream("/images/sisyphus.jpg"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getSisyphusDialog(Ui.greet(), sisyphusImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSisyphus(Sisyphus s) {
        sisyphus = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        response = sisyphus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSisyphusDialog(response, sisyphusImage)
        );
        userInput.clear();
    }
}
