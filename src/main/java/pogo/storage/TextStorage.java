package pogo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import pogo.tasks.Task;
import pogo.tasks.TaskEncoder;
import pogo.tasks.TaskTextEncoder;

/**
 * The TextStorage class is responsible for saving and loading tasks to and from a text file.
 * It implements the Storage interface for saving and loading tasks.
 */
public class TextStorage implements Storage {
    private static final String TASKS_FILE = "./data/tasks.txt";
    private final TaskEncoder encoder = new TaskTextEncoder();

    private TextStorage() {
    }

    /**
     * Returns a TextStorage instance.
     *
     * @return a TextStorage instance.
     */
    public static TextStorage of() {
        return new TextStorage();
    }

    /**
     * Creates the task file if it does not exist, including all intermediate
     * directories.
     *
     * @throws IOException if there is an error creating the task file.
     */
    private void createTaskFileIfNotExist() throws IOException {
        File file = new File(TASKS_FILE);

        if (file.exists()) {
            return;
        }

        if (!file.getParentFile().exists()) {
            boolean hasCreatedIntermediateDirs = file.getParentFile().mkdirs();
            if (!hasCreatedIntermediateDirs) {
                throw new IOException("Error creating parent directory");
            }
        }

        boolean hasCreatedFile = file.createNewFile();
        if (!hasCreatedFile) {
            throw new IOException("Error creating task file");
        }
    }

    /**
     * Saves the list of tasks to a text file.
     * The tasks are encoded using the {@link TaskTextEncoder}
     *
     * @param tasks the list of tasks to save.
     */
    @Override
    public void save(List<Task> tasks) {
        try {
            createTaskFileIfNotExist();
        } catch (IOException e) {
            System.out.println("Error creating task file");
            return;
        }

        try {
            FileWriter fw = new FileWriter(TASKS_FILE, false);
            System.out.println("Saving tasks to " + TASKS_FILE);
            String encodedTasks = encoder.encode(tasks);
            fw.write(encodedTasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    /**
     * Loads the list of tasks from a text file.
     * The tasks are decoded using the {@link TaskTextEncoder}
     *
     * @return the list of tasks.
     * @throws IOException if there is an error loading the list of tasks.
     */
    @Override
    public List<Task> load() throws IOException {
        createTaskFileIfNotExist();

        File tasksFile = new File(TASKS_FILE);
        assert tasksFile.exists() : "Task file should exist";
        List<String> lines = Files.readAllLines(tasksFile.toPath());
        return encoder.decode(lines);
    }
}
