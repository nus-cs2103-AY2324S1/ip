package storage;

import java.io.FileWriter;
import java.io.IOException;

import task.Task;
import task.TaskList;

/**
 * Class responsible for saving data to a file.
 */
public class BocchiSaver {
    private static final String DEFAULT_PATH = "./data/bocchi.txt";
    private final TaskList taskList;
    private final String path;

    /**
     * Constructs a BocchiSaver with the provided TaskList and the default file path.
     *
     * @param taskList The TaskList to be saved.
     */
    public BocchiSaver(TaskList taskList) {
        this.taskList = taskList;
        this.path = DEFAULT_PATH;
    }

    /**
     * Constructs a BocchiSaver with the provided TaskList and a specified file path.
     *
     * @param taskList The TaskList to be saved.
     * @param path     The path to the file where data will be saved.
     */
    public BocchiSaver(TaskList taskList, String path) {
        this.taskList = taskList;
        this.path = path;
    }

    /**
     * Writes text data to a file at the specified file path.
     *
     * @param filePath  The path to the file.
     * @param textToAdd The text data to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the TaskList to the specified file path.
     */
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
