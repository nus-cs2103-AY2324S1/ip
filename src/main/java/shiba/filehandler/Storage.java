package shiba.filehandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shiba.exceptions.ShibaException;
import shiba.parsers.SpaceSeparatedValuesParser;
import shiba.tasks.ShibaTask;

/**
 * Handles the saving and reading of tasks to and from the disk.
 */
public class Storage {
    private final String dataPath;

    /**
     * Creates a new Storage object.
     *
     * @param dataPath Path to the file to be used for saving and reading tasks.
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * Saves the tasks to the disk, creating the file if it does not exist.
     *
     * @param tasks List of tasks to be saved.
     * @throws ShibaException If there is an error saving the tasks.
     */
    public void saveTasks(List<ShibaTask> tasks) throws ShibaException {
        try {
            File file = new File(dataPath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new ShibaException("Error creating save file!");
        }

        try (FileWriter fw = new FileWriter(dataPath)) {
            boolean firstLineWritten = false;
            for (ShibaTask task : tasks) {
                if (firstLineWritten) {
                    fw.write("\n");
                }
                fw.write(task.toSaveString());
                firstLineWritten = true;
            }
        } catch (IOException e) {
            throw new ShibaException("Error saving tasks to file!");
        }
    }

    /**
     * Reads the saved tasks from the disk.
     *
     * @return List of tasks read from the disk.
     * @throws ShibaException If there is an error reading the tasks.
     */
    public List<ShibaTask> readSavedTasks() throws ShibaException {
        try {
            File file = new File(dataPath);

            ArrayList<ShibaTask> tasks = new ArrayList<>();
            if (!file.exists()) {
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ShibaTask taskParsed = ShibaTask.fromSaveParams(SpaceSeparatedValuesParser.parse(line));
                if (taskParsed != null) {
                    tasks.add(taskParsed);
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new ShibaException("Error reading tasks from file!");
        }
    }
}
