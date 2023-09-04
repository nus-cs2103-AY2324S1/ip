package urchatbot.storage;

import urchatbot.exception.URChatBotException;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
     * @return tasklist ArrayList<Task> tasklist.
     * @throws URChatBotException if Error occurs in loading.
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
            throw new URChatBotException( "Error loading tasks from file: " + e.getMessage());
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
        try{
            Path directoryPath = Paths.get(".", "data"); // Path to the data directory
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath); // Create the data directory if it doesn't exist
            }

            Path path = directoryPath.resolve("urchatbot.tasks.txt"); // Path to the urchatbot.tasks.txt file within the data directory
            if (!Files.exists(path)) {
                Files.createFile(path); // Create the urchatbot.tasks.txt file if it doesn't exist
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading urchatbot.tasks from file: " + e.getMessage());
        }
    }

}
