package bouncybob.util;

import bouncybob.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.stream.Collectors;

public class TaskManager {
    private final ListView<Task> taskListView;
    private final ListView<Task> originalTaskListView;
    private boolean isFindMode;

    public TaskManager(ListView<Task> taskListView) {
        this.taskListView = taskListView;
        this.originalTaskListView = new ListView<>();
        this.originalTaskListView.setItems(taskListView.getItems());
        this.isFindMode = false;
    }

    public void addTask(Task task) {
        resetTaskListViewIfInFindMode();
        taskListView.getItems().add(task);
        saveTasks();
    }

    public void deleteTask() {
        resetTaskListViewIfInFindMode();
        Task selectedItem = taskListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            taskListView.getItems().remove(selectedItem);
        }
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        TaskFileHandler.saveTasksToDisk(tasksFromListView);
    }

    public void saveTasks() {
        resetTaskListViewIfInFindMode();
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        TaskFileHandler.saveTasksToDisk(tasksFromListView);
    }

    public void setNoteForTask(String index, String note) {
        resetTaskListViewIfInFindMode();
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        tasksFromListView.get(Integer.parseInt(index)).setNote(note);
        saveTasks();
        taskListView.refresh();
    }

    public void filterTaskListView(String subString) {
        this.isFindMode = true;
        ObservableList<Task> filteredTasks = taskListView.getItems().stream()
                .filter(task -> task.getName().contains(subString))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        taskListView.setItems(filteredTasks);
    }

    private void resetTaskListViewIfInFindMode() {
        if (!this.isFindMode) {
            return;
        }
        System.out.println("Resetting task list view");
        taskListView.setItems(originalTaskListView.getItems());
        this.isFindMode = false;
    }
}
