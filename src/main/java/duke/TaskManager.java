package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String FILE_PATH = "./data/duke.txt";
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        saveTasks();
    }

    public void loadTasks() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String[] tokens = line.split("\\s*\\|\\s*");
                    String type = tokens[0];
                    boolean isDone = tokens[1].equals("1");
                    String description = tokens[2];
                    Task task = new Task("");
                    switch (type) {
                        case "T":
                            task = new Todo(description);
                            break;
                        case "D":
                            task = new Deadline(description, tokens[3]);
                            break;
                        case "E":
                            task = new Event(description, tokens[3], tokens[4]);
                            break;
                    }
                    if (isDone) {
                        task.markAsDone();
                    }
                    this.tasks.add(task);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTasks() {
        List<String> saveLines = new ArrayList<>();
        for(Task task : this.tasks) {
            saveLines.add(task.toFile());
        }
        try {
            Files.write(Paths.get(FILE_PATH), saveLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
        saveTasks();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public void printList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
        }
    }
}
