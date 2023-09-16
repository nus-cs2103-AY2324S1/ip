package sillybot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sillybot.tasks.Task;
import sillybot.tasks.TaskList;

/**
 * Represents a Storage object that handles the loading and saving of tasks.
 */
public class Storage {
    private final String filePath;
    private final File file;
    private Scanner sc;

    /**
     * Creates a Storage object.
     *
     * @param filePath The path to the file where the tasks are stored.
     * @throws IOException If the file does not exist and cannot be created.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File does not exist. Creating file...");
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("File created!");
        }
    }

    /**
     * Saves the tasks in the TaskList to the file.
     *
     * @param tasks The TaskList object containing the tasks.
     * @throws IOException If the file does not exist and cannot be created.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

        for (Task task : tasks) {
            fw.write(task.toString() + "\n");
        }

        fw.close();
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The Scanner object containing the tasks.
     * @throws IOException If the file does not exist and cannot be created.
     */
    public Scanner load() throws IOException {
        if (sc == null) {
            sc = new Scanner(this.file);
        }

        return sc;
    }
}
