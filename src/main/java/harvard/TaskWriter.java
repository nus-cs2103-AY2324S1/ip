package harvard;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class TaskWriter {
    private String filePath;

    public TaskWriter(String filePath) {
        this.filePath = filePath;
    }

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
