package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage for the Duke application.
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage with the specified file path.
     *
     * @param filePath The path of the file to load from and save to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If there's an error loading from the file.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
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
                    tasks.add(task);
                }
            } catch (Exception e) {
                throw new DukeException("Error loading file");
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public void save(List<Task> tasks) {
        List<String> saveLines = new ArrayList<>();
        for(Task task : tasks) {
            saveLines.add(task.toFile());
        }
        try {
            Files.write(Paths.get(filePath), saveLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
