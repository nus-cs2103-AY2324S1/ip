import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String DATA_FILE_PATH = "data.txt";

    public void saveTasksToFile(List<Task> toDoList) throws DukeException{
        try {
            FileWriter writer = new FileWriter(DATA_FILE_PATH);
            for (Task task : toDoList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (NullPointerException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void loadTasksFromFile(List<Task> toDoList) {
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String taskData = scanner.nextLine();
                    Task task = Task.createTaskFromData(taskData);
                    if (task != null) {
                        toDoList.add(task);
                    }
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
