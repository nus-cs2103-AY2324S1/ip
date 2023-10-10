package barbie;

import java.io.IOException;

import barbie.types.Deadlines;
import barbie.types.Party;
import barbie.types.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents the HBox that consists of a task name, type and due date to be displayed on the schedule viewer.
 */
public class ScheduleBox extends HBox {
    @FXML
    private Label desc = new Label("");
    @FXML
    private Label date = new Label("No date");
    @FXML
    private Label type = new Label("Todo");

    private Task task;



    /**
     * Constructs an instance of a ScheduleBox.
     * @param task task to display
     */
    public ScheduleBox(Task task) {
        this.task = task;
        try {
            loadFxml();
        } catch (IOException e) {
            e.printStackTrace();
        }
        desc.setText(task.getDescription());
        if (task instanceof Deadlines) {
            Deadlines typedTask = (Deadlines) task;
            date.setText(typedTask.getDate().toString());
            type.setText("Deadline");

        } else if (task instanceof Party) {
            Party typedTask = (Party) task;
            date.setText(typedTask.getDate().toString());
            type.setText("Party");
        }
    }

    /**
     * Loads the FXML file of the ScheduleBox.
     * @throws IOException exception thrown when loading FXMLLoader
     */
    private void loadFxml() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Schedulebox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

}
