import exception.DukeStorageException;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskList {
    private List<Task> taskList;
    private StorageService storageService;

    public TaskList(StorageService storageService) {
        this.storageService = storageService;
        this.taskList = new ArrayList<>(storageService.loadTasks());
    }

    public boolean addTask(Task task) throws DukeStorageException {
        storageService.saveTask(task);
        return this.taskList.add(task);
    }

    public Optional<Task> deleteTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        storageService.deleteTask(index);
        Task removedTask = taskList.remove(index);
        return Optional.of(removedTask);
    }

    public Optional<Task> markTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        task.markAsDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
    }

    public Optional<Task> unmarkTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        task.markAsNotDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }
}
