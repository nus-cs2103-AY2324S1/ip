package alyssa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private boolean programStopped = false;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ui ui;

    private Alyssa alyssa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hiro2.jpeg"));
    private Image alyssaImage = new Image(this.getClass().getResourceAsStream("/images/zerotwo.png"));

    /**
     * Initialises the UI.
     */
    @FXML
    public void initialize() {
        this.ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getAlyssaDialog(ui.getGreeting(), alyssaImage));
    }

    public void setAlyssa(Alyssa aly) {
        alyssa = aly;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput() {
        if (programStopped) {
            javafx.application.Platform.exit();
        }
        String input = userInput.getText();
        String response = alyssa.getResponse(input);
        DialogBox userBox = DialogBox.getUserDialog(input, userImage);
        DialogBox alyssaBox = DialogBox.getAlyssaDialog(response, alyssaImage);
        HBox.setHgrow(alyssaBox, Priority.ALWAYS);
        alyssaBox.setFillHeight(false);
        dialogContainer.getChildren().addAll(
                userBox, alyssaBox
        );
        userInput.clear();
        if (input.equals("bye")) {
            programStopped = true;
        }
    }
}

