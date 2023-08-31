package monday.storage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.util.ArrayList;
import monday.task.Task;


/**
 * Class for handling the storage of tasks in a file.
 */
public class Storage {
    private final String FILEPATH;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param fileName the name of the file to store the tasks
     */
    public Storage(String fileName) {
        this.FILEPATH = fileName;
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks the list of tasks to save
     * @throws IOException if an I/O error occurs while saving the tasks
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILEPATH))){
            output.writeObject(tasks);
        }
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return the list of tasks loaded from the file
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     * @throws IOException if an I/O error occurs while loading the tasks
     */
    public ArrayList<Task> load() throws ClassNotFoundException, IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILEPATH))){
            // We know the objects inside are all Tasks,therefore we can suppress the unchecked warning.
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasklists = (ArrayList<Task>) input.readObject();
            return tasklists;
        } catch (EOFException e) {
            return new ArrayList<>();
        }
    }
}