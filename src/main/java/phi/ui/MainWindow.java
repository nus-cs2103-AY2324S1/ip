package phi.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import phi.Phi;

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

    private Phi phi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private final Image phiImage = new Image(this.getClass().getResourceAsStream("/images/phi.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // add the greeting
        dialogContainer.getChildren().add(DialogBox.getPhiDialog(Ui.greeting(), phiImage));
    }

    public void setPhi(Phi p) {
        this.phi = p;
        // adds the storage loading text
        dialogContainer.getChildren().add(DialogBox.getPhiDialog(phi.getStorageMsg(), phiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing PHI's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                // Close the JavaFX application after the delay
                Platform.exit();
            });
            pause.play();
        }
        String response = phi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPhiDialog(response, phiImage)
        );
        userInput.clear();

    }
}