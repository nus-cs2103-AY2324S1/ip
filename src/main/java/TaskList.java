import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.SavedDataFormatException;
import exceptions.UpdateDataException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * TaskList encapsulates the task lists and operations related to the task lists.
 *
 * @author Sebastian Tay
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(100);
    private Storage storage;

    /**
     * Public constructor of TaskList that takes in a storage and initialise the tasklist.
     *
     * @param storage is the Storage that the data will be managed by.
     */
    public TaskList(Storage storage) {
        assert storage != null : "storage is null";
        this.storage = storage;
    }

    public ArrayList<Task> getTasks() {
        assert tasks != null : "tasks is null";
        return this.tasks;
    }

    /**
     * Retrieves the saved data from file.
     *
     * @return true if successful. Otherwise, an exception will be thrown.
     * @throws FileNotFoundException if storage file is not found.
     * @throws SavedDataFormatException if saved file has a wrong formatting in the data.
     * @throws StringIndexOutOfBoundsException if there is an error in parsing the saved data and converting to task.
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
     * @throws UpdateDataException when there is an issue in updating the saved data.
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
                assert storage != null : "Storage is not assigned to anything yet";

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
                return "Mission has been completed previously.";
            }

            task.updateCompletionStatus();

            try {
                assert storage != null : "Storage is not assigned to anything yet";
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
                return "Mission is already marked as undone!";
            }

            task.updateCompletionStatus();
            try {
                assert storage != null : "Storage is not assigned to anything yet";
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

    /**
     * Edit the details of the task in the list and return a String showing the new task details.
     *
     * @param index is the index of the task we are editing.
     * @param newDetails is the new details of the task that we are editing.
     * @return a String containing the new task details.
     */
    public String editTask(int index, String newDetails) {
        if (newDetails.trim().equals("")) {
            return "Please key in the new details of the task!";
        } else if (index < 0 || index >= tasks.size()) {
            return "Task index cannot be found!";
        }

        final int offset = 1;
        Task task = tasks.get(index);
        final String message = "Successfully updated task at index " + (index + offset) + " to:\n";

        if (task instanceof ToDo) {
            ((ToDo) task).edit(newDetails);

        } else if (task instanceof Event) {
            ((Event) task).edit(newDetails);

        } else if (task instanceof Deadline) {
            ((Deadline) task).edit(newDetails);
        }

        try {
            assert storage != null : "Storage is not assigned to anything yet";
            storage.updateData(tasks, false);

            return message + task;
        } catch (IOException e) {
            return "Error writing to file.";
        } catch (UpdateDataException e) {
            return e.toString();
        }
    }
}
