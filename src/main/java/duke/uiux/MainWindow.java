package duke.uiux;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import duke.Duke;
import duke.uiux.views.DialogueBox;
import javafx.fxml.FXML;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox dialogueBox;
    @FXML
    private TextField userInputBox;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Image userImage = new Image(Ui.class.getResourceAsStream("/uiux/images/UserIcon.png"));
    private Image dukeImage = new Image(Ui.class.getResourceAsStream("/uiux/images/DukeIcon.png"));

    @FXML
    public void initialize() {
        scroll.vvalueProperty().bind(dialogueBox.heightProperty());
    }

    /**
     * Provides the duke for this window.
     * 
     * @param duke The Duke instance this window will use.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }
    
    @FXML
    private void handleUserInput() {
        String userInput = userInputBox.getText();
        String response = duke.run(userInput);
        dialogueBox.getChildren().add(DialogueBox.getUserDialogue(userInput, userImage));
        dialogueBox.getChildren().add(DialogueBox.getDukeDialogue(response, dukeImage));
        userInputBox.clear();
    }
    
}
