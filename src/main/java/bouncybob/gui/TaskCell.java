package bouncybob.gui;

import bouncybob.task.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Represents a cell in the task list.
 */
public class TaskCell extends ListCell<Task> {
    /**
     * Updates the cell with the given task.
     *
     * @param item  The task to update the cell with.
     * @param empty Whether the cell is empty.
     */
    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            Button markDoneButton = new Button("Toggle status");
            markDoneButton.setOnAction(e -> {
                item.toggleDone();
                updateItem(item, false); // Refresh the cell
            });

            Label statusLabel = new Label(item.isDone() ? "✅" : "❌");
            Label taskLabel = new Label(item.getDescription());
            HBox.setHgrow(taskLabel, Priority.ALWAYS);

            Region spacerLeft = new Region();
            HBox.setHgrow(spacerLeft, Priority.ALWAYS);
            Region spacerRight = new Region();
            HBox.setHgrow(spacerRight, Priority.ALWAYS);


            HBox hbox = new HBox(10, statusLabel, spacerLeft, taskLabel, spacerRight, markDoneButton);
            setGraphic(hbox);
        }
    }
}
