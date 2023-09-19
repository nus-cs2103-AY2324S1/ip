package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class manages the loading and saving of task data to a file.
 */
public class Storage {
    private Path dataPath;

    /**
     * Constructs a Storage object with the specified file name.
     * If the file or directory does not exist, it will be created.
     *
     * @param fileName The name of the data file.
     */
    public Storage(String fileName) {
        assert fileName != null : "File name cannot be null";
        createFile(fileName);
        this.dataPath = Path.of(".", "data", fileName);
    }

    /**
     * Creates a new directory or file if either has not existed yet.
     */
    private void createFile(String fileName) {
        try {
            Path dataDirectoryPath = Path.of(".", "data");

            if (!Files.exists(dataDirectoryPath)) {
                Files.createDirectories(dataDirectoryPath);
            }

            Path dataPath = dataDirectoryPath.resolve(fileName);

            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the data file and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects.
     * @throws DukeException If there is an error reading the data file or parsing the tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> fileLines = Files.readAllLines(dataPath);
            for (String line : fileLines) {
                Task task = Parser.convertToTask(line);
                assert task != null;
                tasks.add(task);
            }
        } catch (IOException | DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the updated list of tasks to the data file.
     *
     * @param updatedTasks The TaskList containing the updated tasks.
     */
    public void saveToFile(TaskList updatedTasks) throws DukeException {
        try {
            if (updatedTasks == null) {
                return;
            }

            List<String> lines = new ArrayList<>();
            for (Task task : updatedTasks.tasks) {
                lines.add(task.taskToFileString());
            }

            Files.write(dataPath, lines);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
