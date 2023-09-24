package Forgotten.GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Thread.sleep;

/**
 * Controller for Forgotten.GUI.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private ForgottenGUI forgottenGUI;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image fogottenImage = new Image(this.getClass().getResourceAsStream("/images/Forgotten.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setForgottenGUI(ForgottenGUI forgottenGUI) {
        this.forgottenGUI = forgottenGUI;
        dialogContainer.getChildren().add((DialogBox.getForgottenDialog(forgottenGUI.printGreetMessage(), fogottenImage)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Forgotten.GUI.ForgottenGUI's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = forgottenGUI.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getForgottenDialog(response, fogottenImage)
        );
        userInput.clear();
        if (response.equals(forgottenGUI.printByeMessage())) {
            exitApplication();;
        }
    }

    private void exitApplication() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
            Platform.exit();
        }));
        timeline.play();
    }
}