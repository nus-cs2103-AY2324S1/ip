package jarvis.gui;

import jarvis.Jarvis;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class controls the main GUI window of the Jarvis app. It controls the user interface
 * elements and their interactions with the Jarvis chatbot.
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
    @FXML
    private ImageView introImage;

    private Jarvis jarvis = new Jarvis();
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.png"));

    /**
     * Initializes the MainWindow and binds the scrollPane to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Jarvis instance for the MainWindow and starts the introductory conversation.
     *
     * @param jarvis The Jarvis chatbot instance.
     */
    public void setJarvis(Jarvis jarvis) {
        this.jarvis = jarvis;
        startIntro();
    }

    /**
     * Starts the conversation by printing an intro message from Jarvis.
     */
    @FXML
    private void startIntro() {
        setIntroGif();
        String intro = ui.printIntro();
        dialogContainer.getChildren().addAll(DialogBox.getJarvisDialog(intro, jarvisImage));
    }

    private void setIntroGif() {
        Image gifImage = new Image(getClass().getResourceAsStream("/images/giphy.gif"));
        introImage.setImage(gifImage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jarvis.respond(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getJarvisDialog(response, jarvisImage));
        userInput.clear();
    }
}
