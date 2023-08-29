package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

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
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                Task task = this.stringToTask(scanner.nextLine());
                if (task != null) {
                    data.add(task);
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.println(exc);
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
     */
    public Task stringToTask(String line) {
        String[] split = line.split(" \\| ", 4);

        // Corrupted File
        if (split.length < 3) {
            System.out.println("Error!");
            return null;
        }

        String type = split[0];
        String status = split[1];
        String action = split[2];

        Task task;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

        switch(type) {
        case "T":
            task = new Todo(action, status);
            break;
        case "D":
            task = new Deadline(action, LocalDateTime.parse(split[3], formatter), status);
            break;
        case "E":
            String[] interval = split[3].split(" - ", 2);
            if (interval.length < 2) {
                task = null;
            } else {
                task = new Event(action, LocalDateTime.parse(interval[0], formatter),  LocalDateTime.parse(interval[1], formatter), status);
            }
            break;
        default:
            task = null;
        }

        return task;
    }

}
