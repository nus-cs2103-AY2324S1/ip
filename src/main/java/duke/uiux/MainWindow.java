package duke.uiux;
import duke.ModelViewController;
import duke.Response;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Gui gui;

    // Attribution for userImage
    //<a href="https://www.freepik.com/free-photo/vertical-shot-cute-deer-with-long-antlers-blurred-background
    // _8281082.htm#query=moose&position=4&from_view=search&track=sph">Image by wirestock</a> on Freepik
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));

    // Attribution for dukeImage
    // Image by <a href="https://www.freepik.com/free-photo/creative-assortment-delicious-food_13819431.htm#
    // query=benedict%20cucumber&position=0&from_view=search&track=ais">Freepik</a>
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bot.jpg"));

    /**
     * Initializes the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        createWelcomeMessage();
    }

    /**
     * Sets the gui for the MainWindow
     * @param g the gui to set
     */
    public void setGui(Gui g) {
        gui = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            System.exit(0);
        }
        String response = gui.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates a welcome message for the user
     */
    public void createWelcomeMessage() {
        ModelViewController mvc = new ModelViewController();
        Response welcomeResponse = mvc.createWelcomeMessage();
        String welcomeMessage = welcomeResponse.getResponse();
        DialogBox welcomeDialog = DialogBox.getDukeDialog(welcomeMessage, dukeImage);
        dialogContainer.getChildren().addAll(welcomeDialog);
    }
}

