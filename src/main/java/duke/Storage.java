package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public void saveTasks (TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                writer.write(task.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}
