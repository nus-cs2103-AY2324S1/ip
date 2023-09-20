package GUI;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelpPopupController extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private Button closeButton;
    @FXML
    private Label helpLabel;
    @FXML
    public void initialize() {
        Map<String, String> commandDescriptions = new HashMap<>();
        commandDescriptions.put("help",
                                "- Get help pop-up to display.");
        commandDescriptions.put("list",
                                "- List all of your current tasks.");
        commandDescriptions.put("todo {taskName}",
                                "- Add a ToDo to your task list.");
        commandDescriptions.put("deadline {taskName} /by {yyyy-MM-dd HHmm}",
                                "- Add a Deadline with a specific date to your task list. Time is optional");
        commandDescriptions.put("event {eventName} /from {eventStart} /to {eventEnd}",
                                "- Add an event with a start and end (both strings for now)");
        commandDescriptions.put("mark {taskId}",
                                "- Mark the task with id taskId as done.");
        commandDescriptions.put("unmark {taskId}",
                                "- Mark the task with id taskId as not done.");
        commandDescriptions.put("delete {taskId}",
                                "- Delete the task with id taskId.");
        commandDescriptions.put("find {searchText}",
                                "- Find all tasks that have relevant information to searchText.");
        commandDescriptions.put("bye",
                                "- Bid farewell and close the window.");
        for (String command: commandDescriptions.keySet()) {
            String description = commandDescriptions.get(command);
            Text commandText = new Text(command);
            commandText.setStyle("-fx-font-family: 'Monospaced';");
            Text descriptionText = new Text(description);
            vBox.getChildren().addAll(commandText, descriptionText);
            Separator separator = new Separator();
            vBox.getChildren().add(separator);
        }
        Platform.runLater(() -> {
            scrollPane.setVvalue(0.0);
        });    }

    public void setHelpText(String text) {
        helpLabel.setText(text);
    }
    @FXML
    private void closePopup() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
