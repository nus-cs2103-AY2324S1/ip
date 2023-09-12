package anto;

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

    private Anto anto;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/student.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/anto.jpg"));

    /**
     * Initialises controller for MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Anto to the one given and print hello message and load file message.
     *
     * @param a Anto class to set to.
     */
    public void setAnto(Anto a) {
        anto = a;
        dialogContainer.getChildren().addAll(
                DialogBox.getAntoDialog(this.anto.getUi().greet(), dukeImage)
        );

        if (a.hasNoTasks()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getAntoDialog(this.anto.getUi().printNoSavedFile(), dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getAntoDialog(this.anto.getUi().printSavedFileFound(
                            anto.getTaskList().getTaskArrayList()),
                            dukeImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Anto's reply and then appends them to
     * the dialog container. Clears the user input after processing. Ends if Anto response is bye message.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = anto.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAntoDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals(this.anto.getUi().sayBye())) {
            System.exit(0);
        }
    }
}
