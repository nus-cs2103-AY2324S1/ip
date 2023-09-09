import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean load() {
        if (storage.checkFileExists()) {
            //File does exist
            try {
                tasks = storage.retrieveTasks();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file.");

                return false;
            }
        } else {
            //File does not exist
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
            IndexOutOfBoundsException, IOException {

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
            storage.updateData(tasks, false);

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
            storage.updateData(tasks, false);

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
