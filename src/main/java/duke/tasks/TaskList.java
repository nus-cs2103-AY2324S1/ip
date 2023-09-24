package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.exception.DukeStorageException;
import duke.service.StorageService;

/**
 * Represents a list of tasks that supports various task management operations.
 * Integrates with a storage service to ensure persistence of tasks.
 */
public class TaskList {
    private List<Task> taskList;
    private StorageService storageService;

    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Loads the stored tasks from the storageService into the taskList.
     *
     * @param storageService the given storageService.
     */
    public void loadFromStorage(StorageService storageService) {
        this.storageService = storageService;
        this.taskList = new ArrayList<>(storageService.loadTasks());
    }

    /**
     * Adds a new task to the list and updates the storage.
     *
     * @param task The task to be added.
     * @return True if the task was added successfully, false otherwise.
     * @throws DukeStorageException If an error occurs while saving to storage.
     */
    public boolean addTask(Task task) throws DukeStorageException {
        assert task != null : "task should not be null!";
        storageService.saveTask(task);
        return this.taskList.add(task);
    }

    /**
     * Deletes a task based on its index in the list and updates the storage.
     *
     * @param index The index of the task to be deleted.
     * @return An optional containing the deleted task, empty if the index was invalid.
     * @throws DukeStorageException If an error occurs while updating storage.
     */
    public Optional<Task> deleteTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        storageService.deleteTask(index);
        Task removedTask = taskList.remove(index);
        assert removedTask != null : "There should not have been null tasks in the list!";
        return Optional.of(removedTask);
    }

    /**
     * Marks a task as completed based on its index and updates the storage.
     *
     * @param index The index of the task to be marked.
     * @return An optional containing the marked task, empty if the index was invalid.
     * @throws DukeStorageException If an error occurs while updating storage.
     */
    public Optional<Task> markTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        assert task != null : "There should not have been null tasks in the list!";
        task.markAsDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
    }

    /**
     * Unmarks a task as completed based on its index and updates the storage.
     *
     * @param index The index of the task to be unmarked.
     * @return An optional containing the unmarked task, empty if the index was invalid.
     * @throws DukeStorageException If an error occurs while updating storage.
     */
    public Optional<Task> unmarkTask(int index) throws DukeStorageException {
        if (index < 0 || index >= taskList.size()) {
            return Optional.empty();
        }
        Task task = taskList.get(index);
        assert task != null : "There should not have been null tasks in the list!";
        task.markAsNotDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
    }

    /**
     * Returns a List of tasks in the taskList with taskName that matches the given keyword.
     *
     * @param keyword the keyword to match in the taskList.
     * @return the list of tasks that contains the keyword in their taskName.
     */
    public List<Task> searchTasks(String keyword) {
        return taskList.stream()
            .filter(task -> task.getTaskName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }

    /**
     * Retrieves the list of tasks currently managed.
     *
     * @return A list of tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the total number of tasks currently managed.
     *
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

}
