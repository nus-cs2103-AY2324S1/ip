package jeoe.Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import jeoe.Exceptions.CorruptDataException;
import jeoe.Exceptions.IndexOutOfBoundsException;

/**
 * This class encapsulates the TaskManager class.
 * The StorageManager helps to contain the task list and handle operations on tasks.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class TaskManager {

    /** Contains file path for storage. */
    private String filePath;

    /** Contains list of tasks. */
    private ArrayList<Task> storage;

    /**
     * Constructor for a TaskManager object.
     * Adds the lists of task to the storage field.
     *
     * @param taskListData A file containing stored tasks on local machine.
     */
    public TaskManager(File taskListData, String filePath) {
        this.filePath = filePath;
        try {
            storage = new ArrayList<>(); // everytime you initialize, you start with a new storage
            Scanner fileSc = new Scanner(taskListData);
            while (fileSc.hasNextLine()) { // if no task, storage is empty
                Task task = stringToTask(fileSc.nextLine());
                if (task != null) {
                    storage.add(task);
                }
            }
            fileSc.close();
        } catch (CorruptDataException e) {
            // if file corrupt, create another file for that directory
            try {
                File dataStorageFile = new File(this.filePath);
                // if file doesnt exist, // taskListData.length() == 0; // is used to check length of file
                if (dataStorageFile.exists()) {
                    // Delete the existing file
                    dataStorageFile.delete();
                    dataStorageFile.getParentFile().mkdirs();
                    dataStorageFile.createNewFile();
                    storage = new ArrayList<>();
                }
            } catch (IOException ex) {
                System.out.println("couldn't create the new file exception");
            }
        } catch (FileNotFoundException e) {
            System.out.println("storage manager did not load file properly");
        }
    }

    /**
     * Converts a task in string format to Task type.
     *
     * @param taskInStringForm The task in string format to be converted.
     * @return A task of Task type.
     */
    private Task stringToTask(String taskInStringForm) throws CorruptDataException {
        String[] taskData = taskInStringForm.split(" \\| "); // assuming the description and all that doesnt have |
        // splits into type, mark or not, description, from, to
        try {
            switch (taskData[0]) {
            case "T":
                Task todo = new Todo(taskData[2]);
                if (taskData[1] == "1") {
                    todo.mark();
                }
                return todo;
            case "D":
                Task deadline = new Deadline(taskData[2], LocalDateTime.parse(taskData[3]));
                if (taskData[1] == "1") {
                    deadline.mark();
                }
                return deadline;
            case "E":
                Task event = new Event(taskData[2], taskData[3], taskData[4]);
                if (taskData[1] == "1") {
                    event.mark();
                }
                return event;
            default:
                throw new CorruptDataException("");
            }
        } catch (Exception e) {
            // task was not valid in the DB
            System.out.println("not valid task");
            throw new CorruptDataException("");
        }
    }

    /**
     * Returns the list of task.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.storage;
    }

    /**
     * Returns the task at that index.
     *
     * @param idx Index of the task to be returned.
     * @return The task at that index.
     */
    public Task getTask(int idx) {
        return this.storage.get(idx);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.storage.add(task);
    }

    /**
     * Deletes the task at that index.
     *
     * @param idx Index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteIndex(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= this.storage.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(idx + 1));
        }
        return this.storage.remove(idx);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getTasksSize() {
        return this.storage.size();
    }

    /**
     * Marks a task in the list.
     *
     * @param idxMark Index of the task to be marked.
     */
    public void mark(int idxMark) throws IndexOutOfBoundsException {
        if (idxMark < 0 || idxMark >= this.storage.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(idxMark + 1));
        }
        storage.get(idxMark).mark();
    }

    /**
     * Un-marks a task in the list.
     *
     * @param idxUnmark Index of the task to be un-marked.
     */
    public void unmark(int idxUnmark) throws IndexOutOfBoundsException {
        if (idxUnmark < 0 || idxUnmark >= this.storage.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(idxUnmark + 1));
        }
        storage.get(idxUnmark).unmark();
    }

    /** Sorts the tasks in the list. */
    public void sort() {
        Collections.sort(this.storage);
    }

    /** Returns a string representation of the list of tasks. */
    @Override
    public String toString() {
        String reply = "";
        for (int i = 0; i < this.storage.size(); i++) {
            String num = String.valueOf(i + 1) + ". ";
            Task task = this.storage.get(i);
            reply += num + task.toString() + "\n";
        }
        return reply;
    }
}
