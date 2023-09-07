package duke;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class handles the storage and retrieval of task data using serialization.
 * It provides methods to save and load ArrayList of Task objects to/from a file on the hard disk.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where task data will be stored and retrieved.
     *                 If the file path does not exist, the necessary directories will be created.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to a file on the hard disk using serialization.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there's an error while writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outputStream.writeObject(tasks);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Loads the list of tasks from a file on the hard disk using deserialization.
     *
     * @return The loaded list of tasks.
     * @throws IOException            If there's an error while reading from the file.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public ArrayList<Task> loadTasks() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filePath));
            // Suppressing unchecked cast warning because we know the deserialized object is an ArrayList<Task>.
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) inputStream.readObject();
            return tasks;
        } catch (EOFException e) {
            // The file is empty: There are no tasks.
            return new ArrayList<Task>();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
