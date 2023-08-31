package harvard;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a task reader that reads tasks from a file.
 */
public class TaskReader {
    /**
     * The file path.
     */
    private String filePath;
    /**
     * Constructs a TaskReader object.
     * @param filePath The file path.
     */

    public TaskReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from the file.
     * @return The list of tasks.
     */

    public ArrayList<Task> readTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println("Error parsing file: " + e.getMessage());
        }
        return tasks;
    }
}
