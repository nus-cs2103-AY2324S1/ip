package duke.gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    @FXML
    private Text textBox;
    @FXML
    private Text textBoxList;

    private Duke duke;

    /**
     * Initialise the stage
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.textBox.setText(Ui.WELCOME_MESSAGE);
    }

    public void setDuke(Duke d) {
        duke = d;
        this.textBoxList.setText(this.duke.getResponse("list"));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        String response = duke.getResponse(input);


        dialogContainer.getChildren().addAll(
                new DialogBox(input),
                new DialogBoxQuack(response)
        );

        if (response.equals(Ui.GOODBYE_MESSAGE)) {
            System.exit(0);
        }
        userInput.clear();
    }
}
