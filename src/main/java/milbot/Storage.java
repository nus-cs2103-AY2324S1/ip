package milbot;
import taskclasses.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File file;
    private FileWriter tasksOutput;
    private String filePath;
    private TaskList taskList;

    public Storage() {
        filePath = "..\\src\\main\\data\\mil.txt";
        file = new File(filePath);
    }

    public void loadTasksFromFile() {
        if (taskList == null) {
            return;
        }

        try (BufferedReader inputFile = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                Task task = Task.createTaskFromData(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void saveTasksToFile(TaskList tasks) {
        try {
            taskList = tasks;
            tasksOutput = new FileWriter(filePath);
            BufferedWriter outputFile = new BufferedWriter(tasksOutput);
            for (Task task : tasks.getTaskList()) {
                outputFile.write(task.formatToFile());
                outputFile.newLine();
            }
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
