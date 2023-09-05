package duke;

import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.StorageCreationException;
import duke.tasks.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains functionality to read and interact with the application state.
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Gets the number of tasks.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Lists out all the tasks.
     *
     * @return The output string.
     */
    public String listTasks() {
        StringBuilder stringBuilder = new StringBuilder(Messages.LIST_MESSAGE + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            stringBuilder.append(String.format(" %d.%s", i + 1, task));
            if (i < tasks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * Searches for matching tasks.
     *
     * @param input The search string.
     * @return The output string.
     */
    public String searchTasks(String input) {
        StringBuilder stringBuilder = new StringBuilder(Messages.FIND_MESSAGE + "\n");
        int filteredCount = 0;
        for (Task task : this.tasks) {
            if (!task.getDescription().contains(input)) {
                continue;
            }
            filteredCount++;
            stringBuilder.append(String.format(" %d.%s", filteredCount, task));
            stringBuilder.append("\n");
        }
        if (filteredCount > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * Inserts a task into the list of items.
     *
     * @param task The item to be added.
     * @throws IOException If saving tasks to file failed.
     */
    public void insertTask(Task task) throws IOException {
        this.tasks.add(task);
        this.saveTasks();
    }

    /**
     * Marks task based on index.
     *
     * @param index The index of the item to be marked.
     * @return The marked task's string.
     * @throws IOException If saving tasks to file failed.
     */
    public String markTask(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.mark();
        this.saveTasks();
        return task.toString();
    }

    /**
     * Unmarks task based on index.
     *
     * @param index The index of the item to be unmarked.
     * @return The unmarked task's string.
     * @throws IOException If saving tasks to file failed.
     */
    public String unmarkTask(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.unmark();
        this.saveTasks();
        return task.toString();
    }

    /**
     * Deletes task based on index.
     *
     * @param index The index of the item to be deleted.
     * @return The deleted task's string.
     * @throws IOException If saving tasks to file failed.
     */
    public String deleteTask(int index) throws IOException {
        Task task = this.tasks.remove(index);
        this.saveTasks();
        return task.toString();
    }

    /**
     * Loads all tasks from the data/duke.txt file.
     * If the file does not exist, it will be created.
     */

    /**
     * Loads all tasks from storage.
     *
     * @throws InsufficientArgumentsException If there are not enough arguments to create tasks.
     * @throws DateTimeParseException         If the date in storage is formatted wrongly.
     * @throws StorageCreationException       If the storage file and directory creation failed.
     * @throws IOException                    If the storage file creation failed.
     */
    public void loadTasks() throws InsufficientArgumentsException,
            DateTimeParseException,
            StorageCreationException, IOException {
        List<Task> tasks = this.storage.readStorage();
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    /**
     * Saves the tasks into storage.
     *
     * @throws IOException If writing to the file failed.
     */
    public void saveTasks() throws IOException {
        this.storage.updateStorage(this.tasks);
    }

    /**
     * Sets the storage object for the task list.
     *
     * @param storage The storage object.
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
