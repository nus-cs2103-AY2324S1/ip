package chad.chatengine;

import chad.task.Task;
import chad.task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.time.format.DateTimeFormatter;

/**
 * Manages storage operations for tasks.
 */
public class Storage {
    private Path filePath;

    /**
     * Constructs a new Storage instance.
     * @param filePath the path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFileExists();
    }

    /**
     * Ensures the file for storage exists.
     */
    private void ensureFileExists() {
        try {
            if (Files.notExists(filePath)) {
                Path parentDir = filePath.getParent();
                if (Files.notExists(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file or directory: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks to the file.
     * @param taskList the list of tasks.
     * @throws IOException if an I/O error occurs.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat(formatter));
                writer.newLine();
            }
        }
    }

    /**
     * Loads tasks from the storage file.
     * @return an ArrayList of Task objects loaded from the storage file.
     * @throws IOException if an I/O error occurs.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line, formatter);
                tasks.add(task);
            }
        }
        return tasks;
    }
}


