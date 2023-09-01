package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage for saving and loading task data to and from disk.
 */
public class Storage {

    /**
     * The path to the file where the task data is stored.
     */
    private final String filePath;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The path to the file where the task data is stored.
     */
    public Storage(String filePath) {
        // Create data folder and file if they don't exist
        File file = new File(filePath);
        File folder = file.getParentFile();

        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.filePath = filePath;
    }

    /**
     * Loads the task data from the file.
     *
     * @return List of tasks loaded from the file.
     * @throws InvalidCommandException If the task data in the file is invalid.
     */
    public List<Task> load() throws InvalidCommandException {
        List<Task> list = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Task task = parseFromFile(line);
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Saves the task data to the file.
     *
     * @param list List of tasks to be saved.
     */
    public void save(TaskList list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : list) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to disk.");
        }
    }

    /**
     * Parses a line from the storage file to generate a Task object.
     *
     * @param line The line of task data to be parsed.
     * @return TA Task object corresponding to the given line.
     * @throws InvalidCommandException If the task data in the file is invalid.
     */
    private static Task parseFromFile(String line) throws InvalidCommandException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String deadline = parts[3];
            task = new Deadline(description, deadline);
            break;
        case "E":
            String eventTime = parts[3];
            int toIndex = eventTime.indexOf("to");
            String from = eventTime.substring(0, toIndex).trim();
            String to = eventTime.substring(toIndex + 2).trim();
            task = new Event(description, from, to);
            break;
        default:
            throw new InvalidCommandException();
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }
}
