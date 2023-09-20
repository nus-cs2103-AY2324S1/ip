package nexus.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nexus.Nexus;

import java.util.Objects;

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

    private Nexus nexus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image nexusImage = new Image(this.getClass().getResourceAsStream("/images/nexus.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNexus(Nexus nexus) {
        this.nexus = nexus;
        dialogContainer.getChildren().add(DialogBox.getNexusDialog(nexus.greetUser(), nexusImage));
        dialogContainer.getChildren().add(DialogBox.getNexusDialog(nexus.getResponse("list"), nexusImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nexus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNexusDialog(response, nexusImage)
        );
        userInput.clear();
    }
}