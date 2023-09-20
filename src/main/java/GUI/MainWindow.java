package GUI;
import Duke.Duke;
import Duke.DukeException;
import OOP.Ui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private String BYE_TEXT = "Bye. Hope to see you again soon! I'll close the window now.\n";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/eve.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wallE.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // to greet the user:
        Label greetingLabel = new Label(new Ui().getGreeting("wallE"));
        Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wallE.png"));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingLabel.getText(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(duke.getResponse(userInput.getText()));
        DialogBox userDialog = DialogBox.getUserDialog(userText.getText(), userImage);
        if (userText.getText().equals("help")) {
            try {
                handleHelpCommand(dukeText);
                userInput.clear();
                return;
            } catch (IOException e) {
                throw new DukeException("Something went wrong with displaying the help Pop-up!");
            }
        }
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText.getText(), dukeImage);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        if (dukeText.getText().equals(BYE_TEXT)) {
            Stage stage = (Stage) userInput.getScene().getWindow(); // Get the stage
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( e -> stage.close() );
            delay.play();
        }
        userInput.clear();
    }

    @FXML
    private void handleHelpCommand(Label dukeText) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HelpPopup.fxml"));
        Parent root = loader.load();

        // Pass relevant information to the controller
        HelpPopupController helpController = loader.getController();
        helpController.setHelpText(dukeText.getText()); // Assuming dukeText is generated here

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
