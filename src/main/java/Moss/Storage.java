package moss;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the default file path.
     *
     * @throws MossException If there's an issue creating the file or directory.
     */
    public Storage() throws MossException {
        this.filePath = "./data/Moss.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                Files.createDirectories(Path.of(this.filePath).getParent());
                Files.createFile(Path.of(this.filePath));
            } catch (IOException e) {
                throw new MossException("Could not create file or directory");
            }
        }
    }

    /**
     * Loads tasks from the file and returns a list of tasks.
     *
     * @return A list of tasks loaded from the file.
     * @throws MossException If there's an issue reading the file or parsing tasks.
     */
    public List<Task> loadTasks() throws MossException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(" \\| ");
                    String type = tokens[0];
                    boolean marked = Objects.equals(tokens[1], "X");
                    String description = tokens[2];
                    Task task;
                    switch (type) {
                        case "T":
                            task = new ToDo(description);
                            break;
                        case "D":
                            String by = tokens[3];
                            LocalDate date = LocalDate.parse(by);
                            task = new Deadline(description, date);
                            break;
                        case "E":
                            String from = tokens[3];
                            LocalDate fromDate = LocalDate.parse(from);
                            String to = tokens[4];
                            LocalDate toDate = LocalDate.parse(to);

                            task = new Event(description, fromDate, toDate);
                            break;
                        default:
                            throw new MossException("Invalid task type");
                    }

                    if (marked) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
                reader.close();
            }
        } catch (IOException e) {
            throw new MossException("Error loading tasks from file");
        }
        return tasks;
    }

    /**
     * Saves a list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws MossException If there's an issue writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws MossException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            List<String> data = new ArrayList<>();
            for (Task task : tasks) {
                data.add(task.toString());
            }
            Files.write(Path.of(filePath), data);
            writer.close();
        } catch (IOException e) {
            throw new MossException("Error saving tasks to file");
        }
    }
}


// Example usage


