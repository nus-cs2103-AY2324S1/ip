package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath  The file path where the task data will be saved.
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Enum representing the task types.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Saves the tasks from the TaskList to the file path.
     *
     * @param tasks  The taskList containing tasks to be saved.
     */
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
