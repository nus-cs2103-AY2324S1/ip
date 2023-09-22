package com.nyanbot.dukestorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.nyanbot.duketasklist.DukeTaskList;
import com.nyanbot.duketasks.Deadline;
import com.nyanbot.duketasks.Event;
import com.nyanbot.duketasks.Task;
import com.nyanbot.duketasks.Todo;

/**
 * Encapsulates a class which manipulates the database.
 *
 * @author Tan Kerway
 */
public class DukeStorageDatabase {
    private static final String DIR_PATH = System.getProperty("user.dir") + "/data";
    private static final String FILE_PATH = System.getProperty("user.dir") + "/data/duke.txt";
    // tasklist class for manipulation
    private final DukeTaskList tasks;

    /**
     * Constructs the database controller.
     *
     * @author Tan Kerway
     * @param tasks the DukeTaskList object tied to the DukeLauncher.Duke instance that created this instance
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
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File database = new File(FILE_PATH);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!database.exists()) {
            database.createNewFile();
            return taskList;
        }
        // we need to load the tasks onto the DukeLauncher.Duke instance
        // use a scanner to read each line
        Scanner reader = new Scanner(database);
        while (reader.hasNextLine()) {
            // get the input
            String[] tokens = reader.nextLine().split(" ");
            Task currentTask = this.tasks.getTask(tokens);
            if (currentTask == null || currentTask.isValid()) {
                taskList.add(currentTask);
            }
        }
        reader.close();
        return taskList;
    }

    /**
     * Saves the latest list of user's tasks to the database
     *
     * @author Tan Kerway
     */
    public void saveTaskList() throws IOException {
        File database = new File(FILE_PATH);
        assert database.exists();
        database.delete();
        database.createNewFile();
        try (FileWriter writer = new FileWriter(database)) {
            for (Task task : this.tasks.getTasks()) {
                writer.write(getTaskString(task));
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
        }
    }

    /**
     * Compiles the task into a standard string for storage in the database
     * @author Tan Kerway
     * @param task the task to be added
     * @return the String version of the task depending on what it is
     */
    private String getTaskString(Task task) throws IOException {
        StringBuilder taskString = new StringBuilder();
        if (task instanceof Todo) {
            taskString.append("t ");
            taskString.append(task.isDone());
            taskString.append(" ");
            taskString.append(task.getDescription());
        } else if (task instanceof Deadline) {
            taskString.append("d ");
            taskString.append(task.isDone());
            taskString.append(" ");
            taskString.append(task.getDescription());
            Deadline temp = (Deadline) task;
            taskString.append(" by ");
            taskString.append(temp.getBy());
        } else if (task instanceof Event) {
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
        return taskString.toString();
    }
}
