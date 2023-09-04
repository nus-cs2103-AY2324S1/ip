package alyssa;

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

    private Ui ui;

    private Alyssa alyssa;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hiro2.jpeg"));
    private Image alyssaImage = new Image(this.getClass().getResourceAsStream("/images/zerotwo.png"));

    @FXML
    public void initialize() {
        this.ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getAlyssaDialog(ui.getGreeting(), alyssaImage));
    }

    public void setAlyssa(Alyssa aly) {
        alyssa = aly;
    }

    boolean programStopped = false;
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlyssaDialog(response, alyssaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            programStopped = true;
        }
    }
}

