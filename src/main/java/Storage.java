import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        // Implement loading tasks from the file
        TaskReader taskReader = new TaskReader(filePath);
        return taskReader.readTasks();
    }

    public void save(TaskList tasks) {
        // Implement saving tasks to the file
        TaskWriter taskWriter = new TaskWriter(filePath);
        taskWriter.write(tasks);
    }
}