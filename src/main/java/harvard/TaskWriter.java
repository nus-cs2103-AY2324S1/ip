package harvard;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Represents a task writer that writes tasks to a file.
 */
public class TaskWriter {
    /**
     * The file path.
     */
    private String filePath;
    /**
     * Constructs a TaskWriter object.
     * @param filePath The file path.
     */

    public TaskWriter(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Writes tasks to the file.
     * @param tasks The list of tasks.
     */

    public void write(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
