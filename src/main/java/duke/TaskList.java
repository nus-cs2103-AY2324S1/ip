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
    private ArrayList<Task> taskList;
    private int counter;

    private Storage storage;

    /**
     * Initializes a tasklist, with its associated file.
     * @param storage the file associated with the tasklist.
     */
    public TaskList(Storage storage) {
        taskList = new ArrayList<>();
        counter = -1;
        this.storage = storage;
    }

    /**
     * Loads the tasklist from the associated file.
     * @throws IOException if we cannot read from the file.
     * @throws CorruptedFileException if the file cannot be interpreted.
     */
    public void loadFromDisk() throws IOException, CorruptedFileException {
        taskList = stringListToTaskList(storage.loadFromDisk());
        counter = taskList.size() - 1;
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
        counter += 1;
        writeToDisk();
    }

    /**
     * Removes a task from the list.
     * @param index index of the task to be removed.
     * @return whether the index exists in the list.
     * @throws IOException if the index exists and was deleted but we cannot write the change to disk.
     */
    public boolean removeTask(int index) throws IOException {
        if (index > counter) {
            return false;
        }
        taskList.remove(index);
        writeToDisk();
        return true;
    }
    public boolean setMark(int targetIndex, boolean isToBeMarked) throws IOException {
        if (targetIndex > counter || targetIndex < 0) {
            return false;
        }
        if (isToBeMarked) {
            taskList.get(targetIndex).markDone();
        }
        if (!isToBeMarked) {
            taskList.get(targetIndex).markUndone();
        }
        writeToDisk();
        return true;
    }
    private ArrayList<Task> stringListToTaskList(ArrayList<String> stringArrayList) throws CorruptedFileException {
        ArrayList<Task> res = new ArrayList<>();
        for (String s : stringArrayList) {
            if (s.length() >= 1) {
                throw new CorruptedFileException();
            }
            String[] temp = s.split(Task.DIVIDER);
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

    /**
     * Produces a string representation of the list.
     * @return a string representation of the list.
     */
    public String listString() {
        if (counter == -1) {
            return ("No list, silly!");
        } else {
            String res = "Here's the list so far.";
            for (int i = 0; i < counter + 1; i++) {
                res += ("\n" + (i + 1) + ". " + taskList.get(i));
            }
            return res;
        }
    }
}
