package storage;

import exceptions.JamesBondException;
import parser.Parser;
import tasks.Task;
import tasks.TaskList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The `Storage` class is responsible for handling the storage of task data in a file and loading tasks from a file.
 */
public class Storage {
   private String file;

    /**
     * Constructs a `Storage` object with the specified file path for task data storage.
     *
     * @param file The file path where task data is stored.
     */
    public Storage(String file) {
       this.file = file;
   }

    /**
     * Saves a list of tasks to the file in append mode.
     *
     * @param taskList The `TaskList` containing the tasks to be saved.
     */
    public void saveTasksToFile(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Append tasks to the end of the file
            for (Task task : taskList.getToDos()) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file and returns a `TaskList` containing the loaded tasks.
     *
     * @return A `TaskList` containing the loaded tasks.
     * @throws JamesBondException If an error occurs while loading tasks from the file.
     */
   public TaskList loadTasksFromFile() throws JamesBondException {
       TaskList tasksList = new TaskList();
       try {
           List<String> lines = Files.readAllLines(Paths.get(file));
           for (String line : lines) {
               Task task = Parser.parseFomSaveFormat(line);
                tasksList.add(task);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return tasksList;
   }
}
