package duke.dataFile;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;

/**
 * Class to handle storage events.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for storage.
     *
     * @param filePath path to the data storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(":(( OOPS!!! An error occurred while creating the file.");
        }
    }

    /**
     * Loads the taskList stored in the file.
     *
     * @return ArrayList representing the taskList.
     * @throws DukeException Duke exception to let the user know what went wrong.
     */
    public ArrayList<Task> load() throws DukeException {
        // Loading the serialised object
        try {
            FileInputStream fileIn = new FileInputStream(this.filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedList = (ArrayList<Task>) objectIn.readObject();
            return loadedList;
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException(":(( OOPS!!! An error occurred while reading data.");
        }
    }

    /**
     * Writes the current list of task into the storage file.
     *
     * @param tasks list of task to be written.
     * @throws DukeException Lets the user know what went wrong.
     */
    public void write(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException(":(( OOPS!!! Something went wrong when saving data");
        }
    }
}
