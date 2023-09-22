package bouncybob.util;

import bouncybob.task.Task;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class TaskManager {
    private final ListView<Task> taskListView;

    public TaskManager(ListView<Task> taskListView) {
        this.taskListView = taskListView;
    }

    public void addTask(Task task) {
        taskListView.getItems().add(task);
        saveTasks();
    }

    public void deleteTask(Task task) {
        taskListView.getItems().remove(task);
        saveTasks();
    }

    public void saveTasks() {
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        TaskFileHandler.saveTasksToDisk(tasksFromListView);
    }

    public void setNoteForTask(String index, String note) {
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        tasksFromListView.get(Integer.parseInt(index)).setNote(note);
        saveTasks();
        taskListView.refresh();
    }
}
