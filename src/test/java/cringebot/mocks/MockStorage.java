package cringebot.mocks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cringebot.datafile.Storage;
import cringebot.exceptions.CringeBotException;
import cringebot.tasks.Task;

/**
 * Mock storage for testing.
 */
public class MockStorage extends Storage {

    /**
     * Constructor for mock storage.
     */
    public MockStorage() {
        super("./src/test/resources/data/MockData.ser");
    }

    @Override
    public ArrayList<Task> loadFromFile() throws CringeBotException {
        // Loading the serialised object
        try {
            this.resetFile(super.filePath);
            FileInputStream fileIn = new FileInputStream(super.filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedList = (ArrayList<Task>) objectIn.readObject();
            return loadedList;
        } catch (IOException | ClassNotFoundException e) {
            throw new CringeBotException("OOPS!!! An error occurred while reading data. :(( ");
        }
    }

    /**
     * Resets the file to an empty ArrayList.
     *
     * @param filePath path to the data storage file.
     */
    public void resetFile(String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(new ArrayList<Task>());
            objectOut.close();
        } catch (IOException e) {
            System.out.println("OOPS!!! An error occurred while writing to file. :((");
        }
    }
}
