package harvard;
import java.io.FileWriter;
import java.io.IOException;

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
            assert fw != null : "FileWriter should not be null";
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                assert task != null : "Task should not be null";
                assert task.toFileString() != null : "Task should not be null";
                assert task.toFileString().length() > 0 : "Task should not be empty";
                assert task.toFileString().charAt(0) == 'T' || task.toFileString().charAt(0) == 'D'
                        || task.toFileString().charAt(0) == 'E' : "Task should start with T, D or E";
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
