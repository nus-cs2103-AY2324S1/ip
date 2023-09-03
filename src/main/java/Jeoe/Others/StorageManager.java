package Jeoe.Others;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Jeoe.Tasks.Task;


/**
 * This class encapsulates the StorageManager class.
 * The StorageManager helps to manage the loading & saving of tasks, to and from the local storage.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class StorageManager {

    /** Contains file path to local storage containing saved tasks. */
    private String filePath;

    /**
     * Constructor for a StorageManager object.
     *
     * @param filePath The file path which the storage manager will save tasks to.
     */
    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from local storage for usage in the Jeoe program.
     *
     * @return File that contains list of tasks, to be handled by the TaskManager object.
     * @throws IOException If the file doesn't exist.
     */
    public File load() throws IOException {
        try {
            // add items into the storage when loading the app
            // find the file
            File taskListData = new File(filePath);
            // if file doesnt exist, // taskListData.length() == 0; // is used to check length of file
            if (!taskListData.exists()) {
                System.out.println("task list data doesnt exist");
                taskListData.getParentFile().mkdirs();
                taskListData.createNewFile();
            }
            return taskListData;
        } catch (IOException e) {
            System.out.println("couldn't create the new file exception");
            throw new IOException();
        }
    }

    /**
     * Converts a task of Task type, to a string to be stored in local storage.
     *
     * @param t Task to be converted to string form.
     * @return String that is to be stored in local storage.
     */
    private static String taskToStringForStorage(Task t) {
        // splits into type, mark or not, description, from, to
        String deLim = " | ";
        switch (t.taskType()) {
        case TODO:
            String todo = "T" + deLim;
            todo += t.isDone() ? ("1" + deLim) : ("0" + deLim);
            todo += t.getDescription();
            return todo;
        case DEADLINE:
            String deadline = "D" + deLim;
            deadline += t.isDone() ? ("1" + deLim) : ("0" + deLim);
            deadline += t.getDescription() + deLim;
            deadline += t.getEndDateTimeString();
            return deadline;
        case EVENT:
            String event = "E" + deLim;
            event += t.isDone() ? ("1" + deLim) : ("0" + deLim);
            event += t.getDescription() + deLim;
            event += t.getEndDateTimeString();
            return event;
        default:
            return null;
        }
    }

    /**
     * Saves a list of tasks in local storage.
     *
     * @param tasks An ArrayList of tasks to be converted to string form and stored in local storage.
     */
    public void save(ArrayList<Task> tasks) { // for saving into the storage, just rewrite the entire file
        // to overwrite the file
        try {
            // concatenate all the tasks in string form
            String listOfTasksString = "";
            for (Task t : tasks) {
                String tString = taskToStringForStorage(t);
                listOfTasksString += (tString + "\n");
            }

            // write to the file
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(listOfTasksString);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Deletes all tasks in local storage. */
    public void deleteAllInFile() {
        ArrayList<Task> arr = new ArrayList<Task>();
        this.save(arr);
        System.out.println("deleted all in file");
    }
}
