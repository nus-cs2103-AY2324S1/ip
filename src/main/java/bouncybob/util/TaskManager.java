package bouncybob.util;

import bouncybob.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.stream.Collectors;

/**
 * Utility class for managing tasks.
 */
public class TaskManager {
    private final ListView<Task> taskListView;
    private final ListView<Task> originalTaskListView;
    private boolean isFindMode;

    /**
     * Initializes a new TaskManager with the given task list view.
     *
     * @param taskListView The task list view to be managed.
     */
    public TaskManager(ListView<Task> taskListView) {
        this.taskListView = taskListView;
        this.originalTaskListView = new ListView<>();
        this.originalTaskListView.setItems(taskListView.getItems());
        this.isFindMode = false;
    }

    /**
     * Adds a task to the task list view.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        resetTaskListViewIfInFindMode();
        taskListView.getItems().add(task);
        saveTasks();
    }

    /**
     * Deletes a task from the task list view.
     */
    public void deleteTask() {
        resetTaskListViewIfInFindMode();
        Task selectedItem = taskListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            taskListView.getItems().remove(selectedItem);
        }
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        TaskFileHandler.saveTasksToDisk(tasksFromListView);
    }

    /**
     * Save all tasks to disk.
     */
    public void saveTasks() {
        resetTaskListViewIfInFindMode();
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        TaskFileHandler.saveTasksToDisk(tasksFromListView);
    }

    /**
     *
     * Add a note to a task.
     *
     * @param index
     * @param note
     */
    public void setNoteForTask(String index, String note) {
        resetTaskListViewIfInFindMode();
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        tasksFromListView.get(Integer.parseInt(index)).setNote(note);
        saveTasks();
        taskListView.refresh();
    }

    /**
     * Filter the task list view by the given substring.
     *
     * @param subString
     */
    public void filterTaskListView(String subString) {
        this.isFindMode = true;
        ObservableList<Task> filteredTasks = taskListView.getItems().stream()
                .filter(task -> task.getName().contains(subString))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        taskListView.setItems(filteredTasks);
    }

    /**
     * Resets the task list view to the original list of tasks if it is currently in find mode.
     */
    public void resetTaskListViewIfInFindMode() {
        if (!this.isFindMode) {
            return;
        }
        System.out.println("Resetting task list view");
        taskListView.setItems(originalTaskListView.getItems());
        this.isFindMode = false;
    }
}
