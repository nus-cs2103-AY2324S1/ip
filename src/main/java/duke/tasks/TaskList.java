package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * Constructs a new TaskList instance associated with a given storage service.
     * Loads tasks from storage upon initialization.
     *
     * @param storageService Service responsible for reading and writing tasks to storage.
     */
    public TaskList(StorageService storageService) {
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
        task.markAsNotDone();
        storageService.saveTasks(taskList);
        return Optional.of(task);
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
