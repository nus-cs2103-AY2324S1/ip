package ip.storage;
import ip.tasks.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }
        Files.write(Paths.get(filePath), lines);
    }

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
