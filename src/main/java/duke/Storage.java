package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * The Storage class is responsible for reading and writing tasklists
 * to and from the Duke.txt file.
 */
public class Storage{

    /**
     * Saves the tasks in the task list to the specified file path.
     * @param filePath The path to the file where tasks will be saved.
     * @param tasks    The task list to be saved.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void saveTasks(String filePath, TaskList tasks) throws FileNotFoundException{
        try {
            createDirectoryIfNotExists(filePath);

            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                String taskData = formatTaskData(task);
                fileWriter.write(taskData);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close(); // Don't forget to close the FileWriter when done.
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    /**
     * Formats a task into a string representation suitable for saving to a file.
     * @param task The task to be formatted.
     * @return A formatted string representation of the task.
     */
    static String formatTaskData(Task task) {
        // Customize this method based on your Task class structure.
        String str = task.saveTaskString();
        return str;
    }
    /**
     * Loads tasks from the specified file path and returns them as a TaskList.
     * @param filePath The path to the file from which tasks will be loaded.
     * @return A TaskList containing the loaded tasks.
     */
    public static TaskList loadTasks(String filePath) {
        TaskList taskList = new TaskList();
        try {
            createDirectoryIfNotExists(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String taskType = components[0];
                boolean isDone = components[1].equals("1");
                String description = components[2];
                switch (taskType) {
                    case "T":
                        taskList.addTask(new ToDo(description, isDone));
                        break;

                    case "D":
                        LocalDateTime by = LocalDateTime.parse(components[3]);
                        taskList.addTask(new Deadline(description, isDone, by));
                        break;

                    case "E":
                        LocalDateTime from = LocalDateTime.parse(components[3]);
                        LocalDateTime to = LocalDateTime.parse(components[4]);
                        taskList.addTask(new Event(description, isDone, from, to));
                        break;
                }
            }
            reader.close(); // Close the reader when done.
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Creates the necessary directory and file if they do not exist.
     * @param filePath The path to the file to be created.
     * @throws IOException If an error occurs during directory or file creation.
     */
    private static void createDirectoryIfNotExists(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        Path parentDir = path.getParent();

        if (parentDir != null) {
            if (!Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        }
    }
}



