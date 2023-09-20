package barbie;

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
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

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

    private Barbie barbie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Elizabeth.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Barbie.png"));

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainWindow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ScheduleWindow.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

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

    public void setBarbie(Barbie b) {
        barbie = b;
    }

}
