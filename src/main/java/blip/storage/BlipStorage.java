package blip.storage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import blip.exceptions.*;
import blip.tasks.*;

/**
 * Represents a storage for Blip ChatBot.
 */
public class BlipStorage {
    /**
     * Path to the data file.
     */
    private static String filePath;

    /**
     * Constructor of BlipStorage
     * @param filePath The data file path for storage
     */
    public BlipStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data file's tasks.
     * @return The TaskList of tasks from data file
     * @throws BlipException If there are any loading errors
     */
    public static TaskList loadFile() throws BlipException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader finalReader = new BufferedReader(fileReader);
            String lineToRead;
            while ((lineToRead = finalReader.readLine()) != null) {
                Task task = Task.loadTaskFromFile(lineToRead);
                tasks.addTask(task);
            }
            finalReader.close();
        } catch (IOException e) {
            System.out.println("Error reading line: " + e.getMessage());
        } catch (DateTimeFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Please format your date time to be yyyy-mm-dd HH:mm");
        }
        return tasks;
    }

    /**
     * Saves list of tasks to data file.
     * @param tasks The TaskList of tasks to save
     */
    public static void saveToFile(TaskList tasks) {
        try {
            File file = new File(filePath);
            File fileDirectory = file.getParentFile();

            if (!fileDirectory.exists()) {
                fileDirectory.mkdirs();
            }
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                fileWriter.write(task.saveToFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e2) {
            System.out.println("Error saving to file: " + e2.getMessage());
        }
    }
}
