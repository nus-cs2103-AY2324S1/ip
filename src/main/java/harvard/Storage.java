//write JUnit test for this class

package harvard;
import java.util.ArrayList;
/**
 * Represents a list of tasks.
 */

public class Storage {
    /**
     * The file path.
     */
    private String filePath;
    /**
     * Constructs a Storage object.
     * @param filePath The file path.
     */

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the file.
     * @return The list of tasks.
     */

    public ArrayList<Task> loadTasks() {
        // Implement loading tasks from the file
        TaskReader taskReader = new TaskReader(filePath);
        return taskReader.readTasks();
    }
    /**
     * Saves tasks to the file.
     * @param tasks The list of tasks.
     */

    public void save(TaskList tasks) {
        // Implement saving tasks to the file
        TaskWriter taskWriter = new TaskWriter(filePath);
        taskWriter.write(tasks);
    }
    /**
     * Returns the file path.
     * @return The file path.
     */

    public String getFilePath() {
        return filePath;
    }
}