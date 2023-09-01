package chatterchicken.storage;

import chatterchicken.CCException;
import chatterchicken.ChatterChicken;
import chatterchicken.parser.Parser;
import chatterchicken.task.Task;
import chatterchicken.tasklist.TaskList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    Parser parser;

    public Storage(Parser parser) {
        this.parser = parser;
    }

    /**
     * Loads tasks from the data file and returns them as an ArrayList.
     * If the data file doesn't exist, a new file is created.
     *
     * @return An ArrayList containing the loaded tasks, or null in case of errors.
     */
    public ArrayList<Task> loadTasksFromFile() {
        try {
            File dataFile = Paths.get(ChatterChicken.PATH).toAbsolutePath().toFile();
            ArrayList<Task> taskList = new ArrayList<>();
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                String currLine = reader.readLine();
                while (currLine != null) {
                    Task task = parser.parseTaskFromFile(currLine);
                    taskList.add(task);
                    currLine = reader.readLine();
                }
                reader.close();
            }
            return taskList;
        } catch (IOException e) {
            System.err.println("An error occurred while loading tasks from file: " + e.getMessage());
        } catch (CCException e) {
            System.err.println("An error occurred while adding tasks: " + e.getMessage());
        }
        return null;
    }

    /**
     * Saves tasks from the provided chatterchicken.tasklist.TaskList to the data file.
     *
     * @param taskList The chatterchicken.tasklist.TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList taskList) {
        try {
            File dataFile = Paths.get(ChatterChicken.PATH).toAbsolutePath().toFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (Task task : taskList) {
                String taskDescription = task.getTaskForSaving() + "\n";
                writer.append(taskDescription);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

}
