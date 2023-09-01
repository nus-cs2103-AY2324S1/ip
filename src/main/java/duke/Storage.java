package duke;
import duke.tasks.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage of the chat bot.
 * A <code>Storage</code> object corresponds to the storage of the chat bot.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a <code>Storage</code> object.
     * @param filePath The path of the file to store the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there is an error saving the tasks to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }
        Files.write(Paths.get(filePath), lines);
    }

    /**
     * Loads the tasks from the file.
     * @return The list of tasks loaded from the file.
     * @throws IOException If there is an error loading the tasks from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(Paths.get(filePath))) {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                tasks.add(Task.fromFileFormat(line));
            }
        }
        return tasks;
    }
}
