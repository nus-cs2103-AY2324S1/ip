package DukeStorage;

import DukeTaskList.DukeTaskList;
import DukeTasks.Deadline;
import DukeTasks.Event;
import DukeTasks.Task;
import DukeTasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a class which manipulates the database.
 *
 * @author Tan Kerway
 */
public class DukeStorageDatabase {
    // hardcoded file paths
    private static final String DIR_PATH = System.getProperty("user.dir") + "/data";
    private static final String FILE_PATH = System.getProperty("user.dir") + "/data/duke.txt";
    // tasklist class for manipulation
    private final DukeTaskList tasks;

    /**
     * Constructs the database controller.
     *
     * @author Tan Kerway
     * @param tasks the DukeTaskList object tied to the Duke instance that created this instance
     *                 of the class.
     */
    public DukeStorageDatabase(DukeTaskList tasks) {
        this.tasks = tasks;
    }
    /**
     * Loads the database from the file path(if any).
     *
     * @author Tan Kerway
     * @return the list of tasks in the database
     */
    public ArrayList<Task> loadDatabase() throws IOException {
        // get the directory
        File dir = new File(DIR_PATH);
        // if the dir does not exist, we create one
        if (!dir.exists()) {
            dir.mkdir();
        }
        // get the database
        File database = new File(FILE_PATH);
        // task list loaded from the tasks database
        ArrayList<Task> taskList = new ArrayList<>();
        // create the file if it does not exist
        // and if it does not exist, the method will return true
        if(!database.exists()) {
            database.createNewFile();
            // since the database is empty, just return an empty arraylist
            return taskList;
        }
        // else, we need to load the tasks onto the Duke instance
        // use a scanner to read each line
        Scanner reader = new Scanner(database);
        while (reader.hasNextLine()) {
            // get the input
            String[] tokens = reader.nextLine().split(" ");
            // get the input length
            Task currentTask = this.tasks.getTask(tokens);
            if (currentTask == null || currentTask.isValid()) {
                // add the current task to the list of tasks
                taskList.add(currentTask);
            }
        }
        // close the scanner
        reader.close();
        // return the final tasklist loaded from the database
        return taskList;
    }

    /**
     * Saves the latest list of user's tasks to the database
     *
     * @author Tan Kerway
     */
    public void saveTaskList() throws IOException {
        // get the file
        File database = new File(FILE_PATH);
        // delete the file
        database.delete();
        // create new database
        database.createNewFile();
        try (FileWriter writer = new FileWriter(database)) {
            // loop over the tasklist and add the tasks
            for (Task task : this.tasks.getTasks()) {
                // stringBuilder class to parse the task into a string for database storage
                StringBuilder taskString = new StringBuilder();
                if (task instanceof Todo) {
                    taskString.append("t ");
                    taskString.append(task.isDone());
                    taskString.append(" ");
                    taskString.append(task.getDescription());
                } else if (task instanceof Deadline) { // case where deadline
                    taskString.append("d ");
                    taskString.append(task.isDone());
                    taskString.append(" ");
                    taskString.append(task.getDescription());
                    Deadline temp = (Deadline) task;
                    taskString.append(" by ");
                    taskString.append(temp.getBy());
                } else if (task instanceof Event) { // case where event
                    taskString.append("e ");
                    taskString.append(task.isDone());
                    taskString.append(" ");
                    taskString.append(task.getDescription());
                    Event temp = (Event) task;
                    taskString.append(" from ");
                    taskString.append(temp.getFrom());
                    taskString.append(" to ");
                    taskString.append(temp.getTo());
                } else {
                    throw new IOException("Bad task type!");
                }
                // add the parsed string to the database
                writer.write(taskString.toString());
                // shift to next line
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
        }
    }
}
