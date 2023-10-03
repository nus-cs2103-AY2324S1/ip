package simon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import simon.task.Deadline;
import simon.task.Event;
import simon.task.Task;
import simon.task.ToDo;


/**
 * The {@code Storage} class handles the loading and saving of tasks to a specified file.
 * It ensures that tasks are persisted across multiple runs of the Simon application.
 */
public class Storage {

    /** The path to the file where tasks are stored. */
    private final String filePath;

    /**
     * Constructs a new {@code Storage} instance with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, it is created.
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     * @throws SimonException If there is an error parsing the saved data.
     */
    public ArrayList<Task> load() throws SimonException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        // Ensure data directory exists
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Ensure simon.txt file exists
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new SimonException("An error occurred while creating the data file.");
            }
            return loadedTasks; // Return empty list since the file is new
        }

        try (Scanner scanner = new Scanner(file)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            List<Task> tasks = Stream.generate(() -> scanner.hasNext() ? scanner.nextLine() : null)
                    .takeWhile(Objects::nonNull)
                    .map(line -> {
                        try {
                            return parseTask(line, formatter);
                        } catch (SimonException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            loadedTasks.addAll(tasks);
        } catch (FileNotFoundException e) {
            throw new SimonException("Data file not found. Starting with an empty task list.");
        }

        return loadedTasks;
    }

    private Task parseTask(String line, DateTimeFormatter formatter) throws SimonException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            return createTodo(parts);
        case "D":
            return createDeadline(parts, formatter);
        case "E":
            return createEvent(parts, formatter);
        default:
            return null;
        }
    }

    private ToDo createTodo(String[] parts) {
        ToDo todo = new ToDo(parts[2]);
        if ("1".equals(parts[1])) {
            todo.markAsDone();
        }
        return todo;
    }

    private Deadline createDeadline(String[] parts, DateTimeFormatter formatter) throws SimonException {
        LocalDateTime endDateTime = LocalDateTime.parse(parts[3], formatter);
        Deadline deadline = new Deadline(parts[2], endDateTime.format(formatter));
        if ("1".equals(parts[1])) {
            deadline.markAsDone();
        }
        return deadline;
    }

    private Event createEvent(String[] parts, DateTimeFormatter formatter) throws SimonException {
        LocalDateTime startDateTime = LocalDateTime.parse(parts[3], formatter);
        LocalDateTime endDate = LocalDateTime.parse(parts[4], formatter);
        Event event = new Event(parts[2], startDateTime.format(formatter), endDate.format(formatter));
        if ("1".equals(parts[1])) {
            event.markAsDone();
        }
        return event;
    }



    /**
     * Saves the provided list of tasks to the specified file.
     *
     * @param tasks The {@code ArrayList} of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(this.filePath);
            for (Task task : tasks) {
                if (task instanceof ToDo) {
                    writer.println("T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getTaskName());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    writer.println("D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getTaskName() + " | "
                            + deadline.endDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    writer.println("E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getTaskName() + " | "
                            + event.getStartDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + " | "
                            + event.getEndDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
