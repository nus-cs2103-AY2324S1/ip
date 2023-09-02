package sally;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks in the given TaskList to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws SallyException If there's an issue while saving the tasks.
     */
    public void saveTasksToFile(TaskList tasks) throws SallyException {
        try {
            File file = new File(this.filePath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter filewriter = new FileWriter(file);
            for (Task task : tasks.getTaskList()) {
                filewriter.write(task.toFileString() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            throw new SallyException("OOPS! Something went wrong while saving your tasks.");
        }
    }

    /**
     * Loads tasks from the file and returns a TaskList.
     *
     * @return The TaskList loaded from the file.
     * @throws SallyException If there's an issue while loading the tasks.
     */
    public TaskList loadTasksFromFile() throws SallyException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                return tasks;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                tasks.addTask(task);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new SallyException("OOPS! Something went wrong while loading your tasks.");
        }
        return tasks;
    }
}
