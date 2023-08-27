import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class TaskWriter {
    private String filePath;

    public TaskWriter(String filePath) {
        this.filePath = filePath;
    }

    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
