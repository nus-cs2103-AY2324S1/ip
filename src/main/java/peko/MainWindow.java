package peko;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * The `MainWindow` class controls the main user interface of the Peko chat application.
 * It manages the layout and interactions of UI components such as the scroll pane, dialog container,
 * user input field, and send button.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Peko peko;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pekoImage = new Image(this.getClass().getResourceAsStream("/images/Dapeko.png"));

    /**
     * Initializes the main window by binding the scroll pane's vertical value to the dialog container's height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Peko instance for this main window.
     *
     * @param p The Peko instance to associate with this main window.
     */
    public void setPeko(Peko p) {
        peko = p;
    }


}
