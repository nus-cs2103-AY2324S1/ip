package duke.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Responsible for reading from and writing to the
 * file that stores the list of tasks in the String format.
 */
public class Storage {
    private String filepath;
    private Path path;

    /**
     * Constructs a new Storage instance with a specified file path.
     *
     * @param filepath The path to the file that stores the list of tasks in the String format.
     */
    public Storage(String filepath) {
        assert filepath != ""; // Check that filepath entered is not empty string.
        this.filepath = filepath;
        this.path = Paths.get(this.filepath);
    }

    /**
     * Ensures that the necessary directories and file used for storage exist.
     * If the directory or file does not exist, it creates them.
     */
    public void createFileIfNotExists() {
        Path directoryPath = this.path.getParent();

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!Files.exists(this.path)) {
            try {
                Files.createFile(this.path);
                System.out.println("file created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the current list of tasks to the file right before the program ends.
     *
     * @param tasks The current list of tasks right before the program ends.
     */
    public void saveTasksToFile(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path.toFile()))) {
            for (int i = 0; i < tasks.size(); i++) {
                int currentNumber = i + 1;
                writer.write(tasks.get(currentNumber).toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the list of tasks from the file in the String format
     * and returns it as an ArrayList of Task objects.
     *
     * @return The list of tasks read from the file.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String taskType = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                // Convert String back to Task object
                if (taskType.equals("T")) {
                    tasks.add(ToDo.parseFromString(line));
                } else if (taskType.equals("D")) {
                    tasks.add(Deadline.parseFromString(line));
                } else {
                    tasks.add(Event.parseFromString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
