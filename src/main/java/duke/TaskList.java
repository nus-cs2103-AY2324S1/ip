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
    public void clear() throws IOException {
        taskList.clear();
        writeToDisk();
    }
    public boolean setMark(int targetIndex, boolean isToBeMarked) throws IOException {
        if (targetIndex > taskList.size() || targetIndex < 0) {
            return false;
        }
        if (isToBeMarked) {
            taskList.get(targetIndex).markDone();
        } else {
            taskList.get(targetIndex).markUndone();
        }
        writeToDisk();
        return true;
    }
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
    private ArrayList<String> taskListToStringList(ArrayList<Task> taskArrayList) {
        ArrayList<String> res = new ArrayList<>();
        for (Task s : taskArrayList) {
            res.add(s.fileFormat());
        }
        return res;
    }

    public ArrayList<Task> findTasksMatching(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t: taskList) {
            if (t.toString().contains(keyword)){
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
            return res.toString();
        }
    }
}
