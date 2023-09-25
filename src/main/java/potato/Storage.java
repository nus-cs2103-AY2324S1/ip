package potato;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import potato.task.*;

/**
 * The Storage class represents a data storage utility for managing tasks.
 * It provides functionality to save tasks to a file and load tasks from a file.
 */
public class Storage {
    private String path;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        path = filePath;
    }

    /**
     * Saves the provided list of tasks to the file specified during initialization.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (Task t : tasks) {
            writer.write(t.toSave() + "\n");
        }
        writer.close();
    }

    /**
     * Loads tasks from the file specified during initialization.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws IOException If an error occurs while reading from the file.
     */
    public ArrayList<Task> loadTask() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    tasks.add(Task.parseSaved(scanner.nextLine()));
                }
                scanner.close();
            } catch (IOException e) {
                System.out.println("no file");
            }
        }
        return tasks;
    }
}
