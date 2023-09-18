package barbie;

import barbie.types.Deadlines;
import barbie.types.Party;
import barbie.types.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScheduleBox extends HBox {
    @FXML
    private Label desc = new Label("");
    @FXML
    private Label date = new Label("No date");
    Task task;
    @FXML
    private Label type = new Label("T");


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
            type.setText("D");

        } else if (task instanceof Party) {
            Party typedTask = (Party) task;
            date.setText(typedTask.getDate().toString());
            type.setText("P");
        }    }

    private void loadFxml() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Schedulebox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

}
