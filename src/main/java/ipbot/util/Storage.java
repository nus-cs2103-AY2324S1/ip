package ipbot.util;

import ipbot.model.Task;
import ipbot.model.TaskFormatException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * A class to read and write from the task file storage.
 */
public class Storage {

    private String saveFile;

    /**
     * Defines a Storage object with the save file path where the tasks are stored.
     *
     * @param saveFile The save file path where the tasks are stored.
     */
    public Storage(String saveFile) {
        this.saveFile = saveFile;
        this.createFileIfNotExists();
    }

    /**
     * Creates the save file to store tasks if it does not exist.
     */
    public void createFileIfNotExists() {
        File saveFile = new File(this.saveFile);
        if (!saveFile.exists()) {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Reads the tasks from the tasks CSV save file.
     *
     * @return A TaskList containing all the Task in the CSV save file.
     * @throws TaskFormatException
     * @throws DateTimeParseException
     */
    public TaskList readTasksFromCSVFile() throws TaskFormatException, DateTimeParseException {
        TaskList taskList = new TaskList();
        try {
            FileReader fr = new FileReader(this.saveFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                taskList.addTask(Task.fromCommaString(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    /**
     * Writes the tasks to the tasks CSV save file.
     *
     * @param taskList The TaskList object that contains the tasks to write.
     */
    public void writeTasksToCSVFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.saveFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(taskList.toCommaString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
