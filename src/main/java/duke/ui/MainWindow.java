package duke.ui;

import duke.Richie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author ruishanteo-reused
//Reused from https://github.com/ruishanteo/ip
// with minor modifications
/**
 * Represents the main page gui which helps to load the user and chat bot profile images as well as handle the
 * user input when the uer presses enter or clicks on the send button
 */
public class MainWindow extends AnchorPane {
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField userInput;
    @FXML
    private VBox dialogContainer;
    private Richie richie;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image richieImage = new Image(this.getClass().getResourceAsStream("/images/Bot.png"));

    public void setRichie(Richie richie) {
        this.richie = richie;
        dialogContainer.getChildren().add(DialogBox.getRichieDialog(richie.init(), richieImage));
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    @FXML
    private void handleUserInput() {
        String userInputText = userInput.getText();
        String response = this.richie.getResponse(userInputText);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userInputText, this.userImage),
                DialogBox.getRichieDialog(response, this.richieImage));
        userInput.clear();
    }
}
//@ruishanteo
