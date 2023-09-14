package duke;

import java.io.IOException;
import java.util.ArrayList;

import dukeexception.CorruptedFileException;
import dukeexception.InvalidVarException;
import task.Task;

/**
 * A tasklist that stores a group of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Initializes a tasklist, with its associated file.
     * @param storage the file associated with the tasklist.
     */
    public TaskList(Storage storage) {
        taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Loads the tasklist from the associated file.
     * @throws IOException if we cannot read from the file.
     * @throws CorruptedFileException if the file cannot be interpreted.
     */
    public void loadFromDisk() throws IOException, CorruptedFileException {
        taskList.addAll(stringListToTaskList(storage.loadFromDisk()));
    }

    /**
     * Writes the tasklist to the associated file.
     * @throws IOException if the file cannot be written to.
     */
    public void writeToDisk() throws IOException {
        storage.writeToDisk(taskListToStringList(taskList));
    }


    /**
     * Adds a task to the list.
     * @param task task to be added.
     * @throws IOException if we cannot write the task to disk.
     */
    public void addTask(Task task) throws IOException {
        assert (task != null);
        taskList.add(task);
        writeToDisk();
    }

    /**
     * Removes a task from the list.
     * @param index index of the task to be removed.
     * @return whether the index exists in the list.
     * @throws IOException if the index exists and was deleted, but we cannot write the change to disk.
     */
    public boolean removeTask(int index) throws IOException {
        if (index > taskList.size()) {
            return false;
        }
        taskList.remove(index);
        writeToDisk();
        return true;
    }

    /**
     * Clears the tasklist and saves that information to the disk.
     * @throws IOException if the disk cannot be written to.
     */
    public void clear() throws IOException {
        taskList.clear();
        writeToDisk();
    }

    /**
     * sets the done status of the indicated task.
     * @param targetIndex the index to have its mark set.
     * @param isToBeMarkedAs the status that the mark is to be changed to.
     * @return a boolean representing if the marking was successful.
     * @throws IOException if we cannot save the marking to the disk.
     */
    public boolean setMark(int targetIndex, boolean isToBeMarkedAs) throws IOException {
        if (targetIndex > taskList.size() || targetIndex < 0) {
            return false;
        }
        if (isToBeMarkedAs) {
            taskList.get(targetIndex).markDone();
        } else {
            taskList.get(targetIndex).markUndone();
        }
        writeToDisk();
        return true;
    }

    /**
     * Converts an ArrayList containing strings representing tasks to an arraylist that contains Task objects.
     * @param stringArrayList the target of conversion.
     * @return the converted version of stringArrayList.
     * @throws CorruptedFileException if any of the strings cannot be converted to a task object.
     */
    private ArrayList<Task> stringListToTaskList(ArrayList<String> stringArrayList) throws CorruptedFileException {
        ArrayList<Task> res = new ArrayList<>();
        for (String s : stringArrayList) {
            try {
                res.add(Parser.taskFromString(s));
            } catch (InvalidVarException e) {
                throw new CorruptedFileException();
            }
        }
        return res;
    }

    /**
     * Converts an Arraylist containing Tasks to an ArrayList that holds the string representation of those tasks.
     * @param taskArrayList the ArrayList to be converted.
     * @return the converted ArrayList.
     */
    private ArrayList<String> taskListToStringList(ArrayList<Task> taskArrayList) {
        ArrayList<String> res = new ArrayList<>();
        for (Task s : taskArrayList) {
            res.add(s.fileFormat());
        }
        return res;
    }

    /**
     * Finds any matching tasks that contain the keyword in their string representation.
     * @param keyword the keyword that is to be searched for.
     * @return an arrayList containing the tasks that contain the keywords (can be empty.)
     */
    public ArrayList<Task> findTasksMatching(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t: taskList) {
            if (t.toString().contains(keyword)) {
                res.add(t);
            }
        }
        return res;
    }

    /**
     * Produces a string representation of the list.
     * @return a string representation of the list.
     */
    public String listString() {
        if (taskList.size() == 0) {
            return ("No list, silly!");
        } else {
            StringBuilder res = new StringBuilder("Here's the list so far.");
            for (int i = 0; i < taskList.size(); i++) {
                res.append("\n");
                res.append(i + 1);
                res.append(". ");
                res.append(taskList.get(i));
            }
            assert !(res.toString().isBlank()); // Should not be blank if we passed the taskList.isEmpty() check.
            return res.toString();
        }
    }
}
