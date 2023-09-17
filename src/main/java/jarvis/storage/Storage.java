package jarvis.storage;

import static jarvis.exceptions.ExceptionMessages.INVALID_DATE;
import static jarvis.exceptions.ExceptionMessages.INVALID_RANGE;
import static jarvis.exceptions.ExceptionMessages.INVALID_TASK;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import jarvis.exceptions.DukeException;
import jarvis.exceptions.ExceptionMessages;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;

/**
 * Represents the Storage Class.
 * Responsible for File I/O Operations.
 *
 * @author Shishir
 */
public class Storage {

    /** Folder containing the required text file. */
    private File folder;

    /** File containing the list of all tasks. */
    private File file;

    /**
     * Constructs a file and folder with the given path.
     * @param filePath Path of required text file.
     */
    public Storage(String filePath) {
        String[] folder = filePath.split("/");
        this.file = new File(filePath);
        this.folder = new File(folder[0]);

        // Directory doesn't exist
        if (!this.folder.isDirectory()) {
            this.createDirectory();
        }

        // File doesn't exist
        if (!this.file.exists()) {
            this.createFile();
        }
    }

    /** Creates a new folder. */
    public void createDirectory() {
        this.folder.mkdir();
    }

    /** Creates a new file. */
    public void createFile() {
        try {
            this.file.createNewFile();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    /**
     * Returns the list of all tasks by reading from the text file.
     * @return List of all tasks present in the text file.
     */
    public ArrayList<Task> readData() {
        ArrayList<Task> data = new ArrayList<>();
        int lineNumber = 0;
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                lineNumber++;
                Task task = this.stringToTask(scanner.nextLine());
                if (task != null) {
                    data.add(task);
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.printf(ExceptionMessages.FILE_DOESNT_EXIST + "%s )%n", file.getPath());
        } catch (DukeException exc) {
            System.out.printf(ExceptionMessages.INVALID_INPUT + "%s ) at line number %d.", file.getPath(), lineNumber);
        } finally {
            // To remove the invalid input.
            this.writeData(data);
        }
        return data;
    }

    /**
     * Updates the text file with the given list of tasks.
     * @param tasks List of all tasks.
     */
    public void writeData(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task: tasks) {
                writer.write(task.toFile() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }

    /**
     * Converts the given string into a task object.
     * @param line String representation of the task object.
     * @return Task object.
     * @throws DukeException thrown on invalid input.
     */
    public Task stringToTask(String line) throws DukeException {
        String[] split = line.split(" \\| ", 4);

        // Corrupted File
        if (split.length < 3) {
            throw new DukeException(INVALID_TASK);
        }

        String type = split[0];
        String status = split[1];
        String action = split[2];

        // Check status is valid
        if (!status.equals("X") && !status.equals("O")) {
            throw new DukeException(INVALID_TASK);
        }

        // Check action is valid
        if (action.isBlank()) {
            throw new DukeException(INVALID_TASK);
        }

        Task task;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        try {
            switch(type) {
            case "T":
                task = new Todo(action, status.equals("X"));
                break;
            case "D":
                task = new Deadline(action, LocalDateTime.parse(split[3], formatter), status.equals("X"));
                break;
            case "E":
                String[] interval = split[3].split(" - ", 2);
                if (interval.length < 2) {
                    throw new DukeException(INVALID_RANGE);
                } else {
                    task = new Event(action, LocalDateTime.parse(interval[0], formatter),
                            LocalDateTime.parse(interval[1], formatter), status.equals("X"));
                }
                break;
            default:
                throw new DukeException(INVALID_TASK);
            }
        } catch (DateTimeParseException exc) {
            throw new DukeException(INVALID_DATE);
        }

        return task;
    }

}
