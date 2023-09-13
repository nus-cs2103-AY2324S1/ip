package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dukeexception.CorruptedFileException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

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
            String[] temp = s.split(Task.DIVIDER);
            if (temp.length <= 1) {
                throw new CorruptedFileException();
            }
            boolean isComplete;
            if (temp[1].equals("TRUE")) {
                isComplete = true;
            } else if (temp[1].equals("FALSE")) {
                isComplete = false;
            } else {
                throw new CorruptedFileException();
            }
            try {
                switch (temp[0]) {
                case ("TD"):
                    res.add(new ToDo(temp[2], isComplete));
                    break;
                case ("DL"):
                    res.add(new Deadline(temp[2], isComplete, (LocalDate.parse(temp[3]))));
                    break;
                case ("EV"):
                    res.add(new Event(temp[2], isComplete, LocalDate.parse(temp[3]), LocalDate.parse(temp[4])));
                    break;
                default:
                    throw new CorruptedFileException();
                }
            } catch (DateTimeParseException e) {
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
