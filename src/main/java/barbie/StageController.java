package barbie;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import barbie.types.Task;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


/**
 * Represents the Controller for the chatbot stage: controls the scenes.
 */
public class StageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button viewButton;
    @FXML
    private VBox scheduleContainer;

    private Paint color = Color.color(1, 230.0 / 255, 1, 1.0);

    private Barbie barbie = new Barbie();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Mochi.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Barbie.png"));

    /**
     * Switches the scene to scene 1.
     * Scene 1 refers to the chat page.
     * @param event event to switch to
     * @throws IOException exception thrown when loading FXMLLoader
     */
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainWindow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Switches the scene to scene 2.
     * Scene 2 refers to the schedule page which shows items for the day
     * @param event event to switch to
     * @throws IOException exception thrown when loading FXMLLoader
     */
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ScheduleWindow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Handles user input and loads the response onto the scene.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = barbie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (barbie.getDoExit()) {
            Platform.exit();
        }
    }

    /**
     * Loads items from the task list to the schedule viewer.
     */
    @FXML
    private void loadItems() {
        ArrayList<Task> currTaskList = Storage.getLastList();
        ArrayList<Task> todaysList = Utils.getDateList(LocalDate.now(), currTaskList);
        ArrayList<ScheduleBox> boxList = new ArrayList<>();

        for (Task task : todaysList) {
            ScheduleBox box = new ScheduleBox(task);
            boxList.add(box);
        }
        if (todaysList.size() == 0) {
            scheduleContainer.getChildren().add(new Label("No tasks today!"));
        }
        scheduleContainer.getChildren().addAll(boxList);

    }

    /**
     * Sets the local barbie to a specific instance of Barbie.
     * @param b instance of Barbie
     */
    public void setBarbie(Barbie b) {
        barbie = b;
    }

}
