import java.io.*;
import java.util.List;

public class Storage {
    private FileWriter tasksOutput;
    private String filePath;

    public Storage() {
        filePath = "src\\main\\data\\mil.txt";
    }
    public void loadTasksFromFile(TaskList taskList) {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = inputFile.readLine()) != null) {
                Task task = Task.createTaskFromData(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
            inputFile.close();
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void saveTasksToFile(TaskList taskList) {
        try {
            tasksOutput = new FileWriter(filePath);
            BufferedWriter outputFile = new BufferedWriter(tasksOutput);
            for (Task task : taskList.getTaskList()) {
                outputFile.write(task.formatToFile());
                outputFile.newLine();
            }
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
