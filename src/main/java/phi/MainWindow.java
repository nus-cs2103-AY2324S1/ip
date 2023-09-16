package phi;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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

    private Ui ui = new Ui();
    private Phi phi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image phiImage = new Image(this.getClass().getResourceAsStream("/images/phi.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getPhiDialog(ui.greeting(), phiImage));
    }

    public void setPhi(Phi phi) {
        this.phi = phi;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            //System.out.println("I could definitely do something here");
            PauseTransition pause = new PauseTransition(Duration.seconds(1)); // 1 second delay
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