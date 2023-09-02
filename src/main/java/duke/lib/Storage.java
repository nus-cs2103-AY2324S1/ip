package duke.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.error.DukeException;
import duke.task.TaskList;

/**
 * Manages reading and writing tasks to a save file.
 */
public class Storage {

    private File saveFile;
    private String directoryPath;
    private String fullFilePath;

    /**
     * Constructs a Storage object with the specified directory and file name.
     *
     * @param directoryName Directory where the file will be stored.
     * @param fileName      Name of the save file.
     */
    public Storage(String directoryName, String fileName) {
        this.directoryPath = "./" + directoryName;
        this.fullFilePath = this.directoryPath + "/" + fileName;

        this.saveFile = new File(this.fullFilePath);
    }

    /**
     * Creates the directory and save file if they are missing.
     *
     * @throws DukeException If an unexpected error occurs while creating the directory or file.
     */
    private void createIfMissing() throws DukeException {
        try {
            File directory = new File(this.directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!this.saveFile.exists()) {
                this.saveFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Unexpected error occurred. Cannot create new save file!");
        }
    }

    /**
     * Loads tasks from the save file.
     *
     * @return ArrayList of serialized task strings.
     * @throws DukeException If the file is corrupted or an error occurs during loading.
     */
    public ArrayList<String> load() throws DukeException {
        ArrayList<String> serializedTasks = new ArrayList<>();
        try {
            Scanner saveFileReader = new Scanner(this.saveFile);
            while (saveFileReader.hasNextLine()) {
                serializedTasks.add(saveFileReader.nextLine());
            }
            saveFileReader.close();
        } catch (FileNotFoundException e1) {
            this.createIfMissing();
        } catch (Exception e) {
            throw new DukeException("File save is corrupted");
        }
        return serializedTasks;
    }

    /**
     * Saves the tasks to the save file.
     *
     * @param tasks TaskList containing tasks to be saved.
     * @throws DukeException If an error occurs during saving.
     */
    public void save(TaskList tasks) throws DukeException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fullFilePath))) {
            tasks.save(writer);
        } catch (IOException e) {
            throw new DukeException("Failed to save!");
        }
    }
}
