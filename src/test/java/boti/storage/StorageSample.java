package boti.storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import boti.task.Task;


/**
 * The Storage class for sampling for unit testing
 */
public class StorageSample extends Storage {
    /**
     * Instantiates the StorageSample
     *
     * @param directoryName the directory name
     * @param fileName the file name
     */
    public StorageSample(String directoryName, String fileName) {
        super(directoryName, fileName);
    }

    @Override
    public void addTask(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(super.getFilePath(), true));
            writer.close();
        } catch (IOException e) {
            assert false : "The task could not be added properly";
            System.out.println("Failed to add task: " + e.getMessage());
        }
    }
}
