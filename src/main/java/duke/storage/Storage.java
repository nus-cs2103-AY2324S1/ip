package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage manager for saving and loading tasks to/from a file.
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage instance and initializes the file based on the given path.
     *
     * @param path The path to the storage file.
     */
    public Storage(String path) {
        try {
            String[] splitted = path.split("/");
            File directory = new File(splitted[0]);
            if (!directory.exists()) {
                directory.mkdir();
            }

            File savedData = new File(path);
            if (!savedData.exists()) {
                savedData.createNewFile();
            }

            this.file = savedData;
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the storage file.
     * @throws DukeException If an error occurs during reading or parsing the file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> savedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                String current = sc.nextLine();
                String[] splitted = current.split(" \\| ", 4);
                switch (splitted[0]) {
                    case "T": {
                        Task toDo = new ToDo(splitted[2], splitted[1].equals("1"));
                        savedTasks.add(toDo);
                        break;
                    }
                    case "D": {
                        Task deadline = new Deadline(splitted[2], formatInputDate(splitted[3]), splitted[1].equals("1"));
                        savedTasks.add(deadline);
                        break;
                    }
                    case "E": {
                        String[] timeline = splitted[3].split("--");
                        LocalDateTime from = formatInputDate(timeline[0]);
                        LocalDateTime to = formatInputDate(timeline[1]);
                        Task event = new Event(splitted[2], from, to, splitted[1].equals("1"));
                        savedTasks.add(event);
                        break;
                    }
                    default: {
                        throw new DukeException("Error reading data from storage.\n"
                                + "Creating a task list from scratch.");
                    }
                }
            }
            return savedTasks;
        } catch (FileNotFoundException err) {
            System.out.println(err);
        }
        return null;
    }

    /**
     * Updates the storage file with the given list of tasks.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void updateStorage(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : tasks) {
                writer.write(task.toFile() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    /**
     * Formats an input date string into a LocalDateTime object.
     *
     * @param date The input date string.
     * @return A LocalDateTime object representing the formatted date.
     */
    public LocalDateTime formatInputDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime;
    }
}
