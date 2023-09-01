package storage;

import task.Task;
import task.TaskList;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to saved data to file
 */
public class BocchiSaver {
    private static final String DEFAULT_PATH = "./data/bocchi.txt";
    private final TaskList taskList;
    private final String path;

    public BocchiSaver(TaskList taskList) {
        this.taskList = taskList;
        this.path = DEFAULT_PATH;
    }

    public BocchiSaver(TaskList taskList, String path) {
        this.taskList = taskList;
        this.path = path;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void saveTaskList() {
        try {
            StringBuilder dataToSave = new StringBuilder();
            for (int i = 0; i < this.taskList.size(); i++) {
                Task t = this.taskList.getTask(i + 1);
                dataToSave.append(t.saveTask());
                if (i < this.taskList.size() - 1) {
                    dataToSave.append(System.lineSeparator());
                }
            }
            writeToFile(this.path, dataToSave.toString());
            System.out.println("Task list has been saved successfully!");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
