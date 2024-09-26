package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles saving and loading tasks to/from a file.
 */
public class Storage {

    /**
     * Saves the list of tasks to a serialized file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileOutputStream file = new FileOutputStream("savedTasks.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(tasks);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("file error occurred when saving");
        }
    }

    /**
     * Loads tasks from a serialized file.
     *
     * @return The list of loaded tasks.
     * @throws FileNotFoundException If the file is not found.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream("savedTasks.ser");
            ObjectInputStream output = new ObjectInputStream(file);
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) output.readObject();
            output.close();
            file.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<Task>();
        }
    }
}
