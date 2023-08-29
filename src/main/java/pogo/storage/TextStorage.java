package pogo.storage;

import pogo.tasks.Task;
import pogo.tasks.TaskEncoder;
import pogo.tasks.TaskTextEncoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TextStorage implements Storage {
    private static final String TASKS_FILE = "./data/tasks.txt";
    private final TaskEncoder encoder = new TaskTextEncoder();

    private TextStorage() {
    }

    public static TextStorage of() {
        return new TextStorage();
    }

    private void createTaskFileIfNotExist() throws IOException {
        File file = new File(TASKS_FILE);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        file.createNewFile();
    }

    public void save(List<Task> tasks) {
        try {
            createTaskFileIfNotExist();
        } catch (IOException e) {
            System.out.println("Error creating task file");
            return;
        }

        try {
            FileWriter fw = new FileWriter(TASKS_FILE);
            System.out.println("Saving tasks to " + TASKS_FILE);
            fw.write(encoder.encode(tasks));
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    public List<Task> load() throws IOException {
        createTaskFileIfNotExist();

        File f = new File(TASKS_FILE);
        List<String> lines = Files.readAllLines(f.toPath());
        return encoder.decode(lines);
    }
}
