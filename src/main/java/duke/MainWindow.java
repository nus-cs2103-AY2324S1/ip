package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @FXML
    public void initialize() {

        InputStream userImageStream = this.getClass().getResourceAsStream("/images/DaUser.png");
        InputStream dukeImageStream = this.getClass().getResourceAsStream("/images/DaDuke.png");
        if (userImageStream != null && dukeImageStream != null) {
            System.out.println("aa");
            userImage = new Image(userImageStream);
            dukeImage = new Image(dukeImageStream);
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        } else {
            // Handle the case where the resource files are not found
            System.err.println("One or both of the image files are not found.");
            // You might want to provide default images or take other actions here.
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception{
        try {
            String input = userInput.getText();
            Duke duke = new Duke(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),

                    DialogBox.getDukeDialog(duke.getResponse(), dukeImage)
            );
            userInput.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

