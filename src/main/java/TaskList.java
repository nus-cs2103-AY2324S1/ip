import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.SavedDataFormatException;
import exceptions.UpdateDataException;
import tasks.Task;

/**
 * TaskList encapsulates the task lists and operations related to the task lists.
 *
 * @author Sebastian Tay
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(100);
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the saved data from file.
     *
     * @return true if successful. Otherwise, false.
     */
    public boolean load() throws FileNotFoundException, SavedDataFormatException, StringIndexOutOfBoundsException {
        if (storage.checkFileExists()) {

            tasks = storage.retrieveTasks();
        } else {

            storage.addFile();
        }

        return true;
    }

    /**
     * Deletes the task at the given index of the list.
     *
     * @param taskIndex is null if encounter an exception.
     * @return Task that was deleted from the list.
     * @throws IndexOutOfBoundsException when taskIndex is invalid.
     * @throws IOException when the program is unable to write to the saved file.
     */
    public Task deleteTask(int taskIndex) throws
            IndexOutOfBoundsException, IOException, UpdateDataException {

        Task task = tasks.remove(taskIndex);
        storage.updateData(tasks, false);

        return task;
    }

    /**
     * Adds the given task into the list of tasks.
     *
     * @param task to be added.
     * @return String denoting status of the operation.
     */
    public String addTask(Task task) {
        if (task != null && tasks.add(task)) {
            try {
                storage.updateData(tasks, true);
                return "added in mission:\n" + task;
            } catch (IOException e) {
                return "Error writing to file.";
            } catch (UpdateDataException e) {
                return e.toString();
            }
        } else {
            return "System is unable to accommodate the new mission";
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param taskIndex is the index of the task to be marked.
     * @return String indicating the status from this operation.
     */
    public String markAsDone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);

            if (task.isDone()) {
                //Events.Task already marked as done
                return "Mission has been completed previously.";
            }

            task.updateCompletionStatus();

            try {
                storage.updateData(tasks, false);
            } catch (UpdateDataException e) {
                return e.toString();
            }

            return "Mission status updated! Mission completed successfully.\n" + task;

        } catch (IndexOutOfBoundsException e) {
            return "Invalid index! Please ensure you correctly key in your target index.";
        } catch (IOException e) {
            return "Unable to update file.";
        }
    }

    /**
     * Marks the task at the given index as undone.
     *
     * @param taskIndex is the index of the task to be unmarked.
     * @return String indicating the status from this operation.
     */
    public String markAsUndone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);

            if (!(task.isDone())) {
                //task already marked as undone
                return "Mission is already marked as undone!";
            }

            task.updateCompletionStatus();
            try {
                storage.updateData(tasks, false);
            } catch (UpdateDataException e) {
                return e.toString();
            }

            return "Mission status updated! Mission completion status reverted.\n" + task;

        } catch (IndexOutOfBoundsException e) {
            return "Invalid index! Please ensure you correctly key in your target index.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Unable to update file.";
        }
    }

    /**
     * Returns an ArrayList containing tasks that contains the keyword.
     *
     * @param keyword is the word that we are searching for amongst the tasks.
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> findKeyword(String keyword) {
        //Filter out the tasks without the keyword using Stream
        List<Task> results = tasks
                .stream()
                .filter((task) -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        return new ArrayList<>(results);
    }
}
