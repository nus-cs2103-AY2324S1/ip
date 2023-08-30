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
 * Represents the storage of the program.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filename) {
        this.filePath = Paths.get(".", "data", filename);
    }

    /**
     * Loads the data from the file.
     */
    public ArrayList<Task> load() throws IOException {
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        // Check if file exists, if not create it
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        // Read file and load data
        List<String> data;
        try {
            data = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return new ArrayList<>();
        }
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : data) {
            String[] parts = line.split("\\]", 3);
            if (parts.length < 3) {
                System.out.println("Invalid duke.task format found in Hard Disk");
                continue;
            }
            char type = parts[0].charAt(parts[0].length() - 1);
            char doneStatus = parts[1].charAt(1);
            boolean isDone = doneStatus == 'X';
            String description = parts[2].trim();
            Task task;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            switch (type) {
            case 'T':
                task = new ToDo(description);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case 'D':
                String[] components = description.split(" \\(by: ");
                String deadlineDescription = components[0].trim();
                String deadlineDateTime = components[1].substring(0, components[1].length() - 1);
                task = new Deadline(deadlineDescription, LocalDateTime.parse(deadlineDateTime, format));
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case 'E':
                String[] eventParts = description.split(" \\(from: ");
                String eventDescription = eventParts[0].trim();
                String[] eventDateTimes = eventParts[1].split(" to: ");
                String eventStartDateTime = eventDateTimes[0];
                String eventEndDateTime = eventDateTimes[1].substring(0, eventDateTimes[1].length() - 1);
                task = new Event(eventDescription,
                        LocalDateTime.parse(eventStartDateTime, format),
                        LocalDateTime.parse(eventEndDateTime, format));
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            default:
                System.out.println("Invalid duke.task type found in Hard Disk");
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves the data to the file.
     * @param tasks The tasks to save.
     */
    public void save(List<Task> tasks) throws IOException {
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        // Check if file exists, if not create it
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        // Write data to file
        List<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(task.toString());
        }
        Files.write(filePath, data);
    }

    /**
     * Deletes the file.
     */
    public void delete() throws IOException {
        Files.delete(filePath);
    }
}
