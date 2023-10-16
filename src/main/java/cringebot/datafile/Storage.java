package cringebot.datafile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cringebot.exceptions.CringeBotException;
import cringebot.tasks.Task;
import cringebot.tasks.TaskList;

/**
 * Class to handle storage events to the indicated FILEPATH.
 */
public class Storage {
    protected final String filePath; // Path to the data storage file.

    /**
     * Constructor for storage.
     *
     * @param filePath path to the data storage file.
     */
    public Storage(String filePath) {
        assert filePath != null : "filePath should not be null";
        assert !filePath.isEmpty() : "filePath should not be empty";

        this.filePath = filePath;
        try {
            File file = new File(this.filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("OOPS!!! An error occurred while creating the file. :((");
        }
    }

    /**
     * Loads the taskList stored in the file.
     *
     * @return ArrayList representing the taskList.
     * @throws CringeBotException Duke exception to let the user know what went wrong.
     */
    public ArrayList<Task> loadFromFile() throws CringeBotException {
        // Loading the serialised object
        try {
            FileInputStream fileIn = new FileInputStream(this.filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedList = (ArrayList<Task>) objectIn.readObject();
            return loadedList;
        } catch (IOException | ClassNotFoundException e) {
            throw new CringeBotException("OOPS!!! An error occurred while reading data. :(( ");
        }
    }

    /**
     * Writes the current list of task into the storage file.
     *
     * @param tasks list of task to be written.
     * @throws CringeBotException Lets the user know what went wrong.
     */
    public void writeToFile(TaskList tasks) throws CringeBotException {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(tasks.getTasks());
        } catch (IOException e) {
            throw new CringeBotException("OOPS!!! Something went wrong when saving data. :(( ");
        }
    }
}
