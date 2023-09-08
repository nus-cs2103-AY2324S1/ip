package urchatbot.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import urchatbot.exception.URChatBotException;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
/**
 * Stores existing tasks into the tasklist.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs the Storage class.
     *
     * @param filePath Path to store the tasklist.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the existing tasks from the stored tasklist.
     *
     * @return tasklist An Arraylist of tasklist.
     * @throws URChatBotException If Error occurs in loading.
     */
    public static ArrayList<Task> load() throws URChatBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        handleMissingFile(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                Task task = Task.fromString(line);
                tasks.add(task);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new URChatBotException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks to the stored file.
     *
     * @param tasks Tasklist to be saved.
     */
    public static void save(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving urchatbot.tasks to file: " + e.getMessage());
        }
    }

    private static void handleMissingFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Path parentDirectory = path.getParent();

            if (parentDirectory != null && !Files.exists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
            }

            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating or accessing the file: " + e.getMessage());
        }
    }

}
