package duke.gui;

import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    @FXML
    private Label textBox;
    @FXML
    private Label textBoxList;
    @FXML
    private Label reminderList;

    private Duke duke;

    /**
     * Initialise the stage
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.textBox.setText(Ui.WELCOME_MESSAGE);
        this.textBoxList.setText("Quack Quack, you have not entered any tasks yet!\n"
                + "Create new tasks with the todo, deadline or event command");
        this.reminderList.setText("Quack has not found any deadlines or events today, you are a free quack!");
    }

    /**
     * set the current duke instance and also update the current tasks stored
     */
    public void setDuke(Duke d) {
        this.duke = d;
        String text = this.duke.getResponse("list") + "\n" + this.duke.getStorageLoadErrorMessage();
        this.textBoxList.setText(text);
        String reminders = this.duke.getResponse("reminder 1");
        this.reminderList.setText(reminders);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
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
                new DialogBoxQuack(response));

        if (response.equals(Ui.GOODBYE_MESSAGE)) {
            System.exit(0);
        }
        userInput.clear();
    }
}
