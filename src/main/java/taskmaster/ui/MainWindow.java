package taskmaster.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import taskmaster.Taskmaster;

/**
 * A launcher class to workaround classpath issues.
 */
public class MainWindow extends AnchorPane {
    private static final Image USER_PROFILE = new Image(Main.class.getResourceAsStream("/images/profile.png"));
    private static final Image TASKMASTER_PROFILE = new Image(Main.class.getResourceAsStream("/images/robot.png"));

    //XML
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Taskmaster taskmaster;


    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I am to taskmaster, what can i do for you today?", TASKMASTER_PROFILE)
        );
    }

    public void setTaskmaster(Taskmaster tm) {
        taskmaster = tm;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskmaster.getResponse(input);
        if (response == Ui.GOODBYE_MESSAGE) {
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_PROFILE),
                DialogBox.getDukeDialog(response, TASKMASTER_PROFILE)
        );
        userInput.clear();
    }
}
