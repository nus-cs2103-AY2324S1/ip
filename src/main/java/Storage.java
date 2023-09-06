import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTasks(TaskList taskList) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            System.out.print("Loading...");
            while (scanner.hasNext()) {
                String description = scanner.nextLine();
                Task task = TaskParser.parse(description);
                taskList.addTask(task);
            }
            scanner.close();
        } catch (IOException e) {
            // Handle the case where the file doesn't exist or other IO errors
            System.out.println("No existing tasks file found. Starting with an empty list.");
            System.exit(1);
        }
        System.out.println("Tasks loaded successfully!");
        return taskList;
    }

    public void saveTasks(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList.tasks()) {
                String taskData = TaskParser.serialize(task);
                writer.write(taskData + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to the file.");
        }
    }

}
