package ekud.ui;
// MainWindow.java class is taken from https://se-education.org/guides/tutorials/javaFxPart4.html
import ekud.Ekud;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * (https://se-education.org/guides/tutorials/javaFxPart4.html)
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
    private Ekud duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        // Prevent text from being clipped in dialog boxes using setMinHeight(Region.USE_PREF_SIZE), credits to
        // https://stackoverflow.com/questions/35012518/resize-javafx-label-if-overrun
        DialogBox userDialog = DialogBox.getUserDialog(input, this.userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, this.dukeImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);

        userInput.clear();
    }

    /**
     * Adds the chatbot object to the GUI, and shows a Duke Dialog Box to greet
     * the user upon loading the MainWindow.
     *
     * @param d Chatbot object.
     */
    public void setDuke(Ekud d) {
        this.duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                this.duke.loadData() + "\n" + this.duke.getGreeting(), this.dukeImage));
    }
}
