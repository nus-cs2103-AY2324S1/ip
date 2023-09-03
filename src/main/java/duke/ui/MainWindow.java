package duke.ui;

//import javafx.application.Platform;
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainWindow Class.
 * Responsible for the Main GUI Interface of Jarvis
 *
 * @author Shishir
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ImageView sendButton;
    @FXML
    private TextArea textField;
    @FXML
    private ScrollPane dialogContainer;
    @FXML
    private VBox dialogBox;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/tony.png"));
    private Image jarvis = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private Duke duke;

    /** Initialises the container for holding messages. */
    @FXML
    public void initialize() {
        this.dialogContainer.setVvalue(1.0);
        this.dialogBox.heightProperty().addListener((observable) -> this.dialogContainer.setVvalue(1.0));
        this.dialogContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.dialogContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.dialogBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogBox.getChildren().add(DialogBox.getDukeDialog("Hi!", this.jarvis));
    }

    /**
     * Handles the event where the user presses the "Send" button.
     */
    @FXML
    public void onClick() {
        Stage mainWindow = (Stage) this.textField.getScene().getWindow();
        String input = this.textField.getText().replace("\n", "");;
        String response = this.duke.getResponse(input);
        textField.clear();
        dialogBox.getChildren().addAll(
                DialogBox.getUserDialog(input, this.user),
                DialogBox.getDukeDialog(response, this.jarvis)
        );
        //  if (input.equals("bye")) {
        //    Platform.exit();
        //  }
    }

    /**
     * Handles the event where the user presses the "Enter" key.
     * @param event Event triggering the function call.
     */
    @FXML
    public void onEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.onClick();
        }
    }

    /**
     * Sets duke object to give object.
     * @param d Duke Object responsible for interaction with user.
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

}
