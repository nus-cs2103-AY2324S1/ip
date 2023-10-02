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

    private final String FILE_PATH;
    /**
     * Constructs a Storage object with the default file path.
     *
     * @throws MossException If there's an issue creating the file or directory.
     */
    public Storage() throws MossException {
        this.FILE_PATH = "./data/Moss.txt";
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                Files.createDirectories(Path.of(this.FILE_PATH).getParent());
                Files.createFile(Path.of(this.FILE_PATH));

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
            File file = new File(FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" \\| ");
                assert tokens.length >= 3 : "Invalid file format: Not enough tokens";
                String type = tokens[0];
                boolean marked = Objects.equals(tokens[1], "X");
                String description = tokens[2];

                // Add assertions to validate task type and tokens based on type
                assert type.equals("T") || type.equals("D") || type.equals("E") : "Invalid task type";

                Task task;
                switch (type) {
                case "T":
                    assert tokens.length == 3 : "Invalid ToDo task format111";
                    task = new ToDo(description);
                    break;
                case "D":
                    assert tokens.length == 4 : "Invalid Deadline task format";
                    String by = tokens[3];
                    LocalDate date = LocalDate.parse(by);
                    task = new Deadline(description, date);
                    break;
                case "E":
                    assert tokens.length == 5 : "Invalid Event task format";
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            List<String> data = new ArrayList<>();
            for (Task task : tasks) {
                data.add(task.toString());
            }
            Files.write(Path.of(FILE_PATH), data);
            writer.close();
        } catch (IOException e) {
            throw new MossException("Error saving tasks to file");
        }
    }
}
