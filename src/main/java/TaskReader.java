import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskReader {
    private String filePath;

    public TaskReader(String filePath) {
        this.filePath = filePath;
    }

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
