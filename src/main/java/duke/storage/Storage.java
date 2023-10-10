package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage class to load and save data.
 */
public class Storage {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final Path filePath;

    /**
     * Constructor for Storage.
     * @param filename The name of the file to load and save data.
     */
    public Storage(String filename) {
        this.filePath = Paths.get(".", "data", filename);
    }

    /**
     * Loads the data from the file.
     * @return The tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ensureDirectoryAndFileExist();
        List<String> data = readDataFromFile();
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : data) {
            Task task = parseTaskFromLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the data to the file.
     * @param tasks The tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void save(List<Task> tasks) throws IOException {
        ensureDirectoryAndFileExist();
        // Write data to file
        List<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(task.toString());
        }
        Files.write(filePath, data);
    }

    /**
     * Ensures that the directory and file exist.
     * If not, create them.
     * @throws IOException If an I/O error occurs.
     */
    private void ensureDirectoryAndFileExist() throws IOException {
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Parses the task from the line.
     * @param line The line to parse.
     * @return The task parsed from the line.
     */
    private Task parseTaskFromLine(String line) {
        String[] parts = line.split("\\]", 3);
        if (parts.length < 3) {
            System.out.println("Invalid duke.task format found in Hard Disk");
            return null;
        }
        char type = parts[0].charAt(parts[0].length() - 1);
        boolean isDone = parts[1].charAt(1) == 'X';
        String description = parts[2].trim();

        switch (type) {
        case 'T':
            return createToDoTask(isDone, description);
        case 'D':
            return createDeadlineTask(isDone, description);
        case 'E':
            return createEventTask(isDone, description);
        default:
            System.out.println("Invalid duke.task type found in Hard Disk");
            return null;
        }
    }

    /**
     * Creates a todo task.
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     * @return The todo task.
     */
    private Task createToDoTask(boolean isDone, String description) {
        Task task = new ToDo(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a deadline task.
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     * @return The deadline task.
     */
    private Task createDeadlineTask(boolean isDone, String description) {
        String[] components = description.split(" \\(by: ");
        String deadlineDescription = components[0].trim();
        String deadlineDateTime = components[1].substring(0, components[1].length() - 1);
        Task task = new Deadline(deadlineDescription, LocalDateTime.parse(deadlineDateTime, FORMAT));
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates an event task.
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     * @return The event task.
     */
    private Task createEventTask(boolean isDone, String description) {
        String[] eventParts = description.split(" \\(from: ");
        String eventDescription = eventParts[0].trim();
        String[] eventDateTimes = eventParts[1].split(" to: ");
        String eventStartDateTime = eventDateTimes[0];
        String eventEndDateTime = eventDateTimes[1].substring(0, eventDateTimes[1].length() - 1);
        Task task = new Event(eventDescription,
                LocalDateTime.parse(eventStartDateTime, FORMAT),
                LocalDateTime.parse(eventEndDateTime, FORMAT));
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Reads the data from the file.
     * @return The data read from the file.
     */
    private List<String> readDataFromFile() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Deletes the file.
     * @throws IOException If an I/O error occurs.
     */
    public void delete() throws IOException {
        Files.delete(filePath);
    }
}
