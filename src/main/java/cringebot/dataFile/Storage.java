package cringebot.dataFile;

import cringebot.exceptions.CringeBotException;
import cringebot.tasks.Task;
import cringebot.tasks.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;

/**
 * Class to handle storage events to the indicated FILEPATH.
 */
public class Storage {
    private final String FILEPATH;  // Path to the data storage file.

    /**
     * Constructor for storage.
     *
     * @param filepath path to the data storage file.
     */
    public Storage(String filepath) {
        this.FILEPATH = filepath;
        try {
            File file = new File(this.FILEPATH);

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
            FileInputStream fileIn = new FileInputStream(this.FILEPATH);
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
            FileOutputStream fileOut = new FileOutputStream(this.FILEPATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(tasks.getTasks());
        } catch (IOException e) {
            throw new CringeBotException("OOPS!!! Something went wrong when saving data. :(( ");
        }
    }
}
