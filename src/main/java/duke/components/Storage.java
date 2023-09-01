package duke.components;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import java.time.LocalDateTime;

/**
 * Class that manages the storing and loading of the list of task.
 */
public class Storage {
    /**
     * filePath contains the path to the stored file containing the list.
     */
    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The path to the stored file containing the list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task from an existing save file.
     *
     * @return The list stored in the save file.
     * @throws DukeException Invalid path.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            createFileIfNotExist(filePath);

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException | DukeException e) {
                throw new DukeException("There must be an error. I can't seem to locate the file.");
            }
        } catch (IOException | DukeException e) {
            throw new DukeException("There must be an error. I can't seem to locate the file.");
        }

        return tasks;
    }

    /**
     * Create the path to the file and the file itself if they do not exist.
     *
     * @param filePath Path to be created.
     * @throws IOException
     */
    private void createFileIfNotExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File parentDirectory = file.getParentFile();
            if (parentDirectory != null && !parentDirectory.exists()) {
                parentDirectory.mkdirs(); // Recursively create directories if they don't exist
            }
            file.createNewFile();
        }
    }

    /**
     * Updates the save file with the most current state of the list.
     *
     * @param tasks List of tasks to be updated into the save file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.write());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Reads the data stored in the save file and return the corresponding Task.
     *
     * @param taskData Data for Task stored in the save file.
     * @return Task representation of the String stored.
     * @throws DukeException
     */
    private Task parseTask(String taskData) throws DukeException {
        String[] parts = taskData.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskDescription = parts[2];

        switch (taskType) {
        case "T":
            return new Todo(taskDescription, isDone);
        case "D":
            LocalDateTime deadline = Parser.parseDateTime(parts[3]);
            return new Deadline(taskDescription, deadline, isDone);
        case "E":
            LocalDateTime start = Parser.parseDateTime(parts[3]);
            LocalDateTime end = Parser.parseDateTime(parts[4]);
            return new Event(taskDescription, start, end, isDone);
        default:
            return null; // Invalid task type
        }
    }
}
