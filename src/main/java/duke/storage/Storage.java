package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import duke.exceptions.StorageException;
import duke.exceptions.TimeParsingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents the storage component in the Duke application.
 * This class is responsible for loading tasks from a file and saving tasks back to that file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new instance of the Storage class with the specified file path.
     *
     * @param filePath The path to the file where tasks are to be stored and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file. If the file doesn't exist, a new file will be created.
     *
     * @return A {@link TaskList} populated with tasks loaded from the storage file.
     * @throws StorageException If there are any issues with creating or reading from the file.
     */
    public TaskList load() throws StorageException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                throw new StorageException("Unable to create new file at " + filePath);
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                throw new StorageException("Unable to create new file at " + filePath);
            } catch (TimeParsingException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks;
    }

    /**
     * Parses a line from the storage file and constructs a {@link Task} object from it.
     *
     * @param line The line from the storage file to be parsed.
     * @return A Task object constructed from the provided line, or null if the line format is unrecognized.
     * @throws TimeParsingException If there's an issue parsing the time from the line.
     */
    private Task parseTask(String line) throws TimeParsingException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            return new ToDo(parts[2], "1".equals(parts[1]));
        case "D":
            return new Deadline(parts[2], parts[3], "1".equals(parts[1]));
        case "E":
            String[] eventTimes = parts[3].split("--");
            return new Event(parts[2], eventTimes[0], eventTimes[1], "1".equals(parts[1]));
        default:
            return null;
        }
    }

    /**
     * Saves a {@link TaskList} back to the storage file.
     *
     * @param tasks The TaskList to be saved.
     * @throws StorageException If there's an issue saving to the file.
     */
    public void save(TaskList tasks) throws StorageException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(tasks.toStorage());
        } catch (IOException e) {
            throw new StorageException(filePath);
        }
    }

}
