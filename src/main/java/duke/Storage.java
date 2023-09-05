package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateFormatException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


/**
 * The Storage class handles loading and saving tasks from/to a file.
 */
public class Storage {
    private final String filePath;
    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path to store task data.
     */

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            Files.createDirectories(Paths.get("data"));
            f.createNewFile();
            Scanner s = new Scanner(f);
            list = new ArrayList<Task>();
            while (s.hasNext()) {
                loadTask(s.nextLine(), list);
            }
        } catch (IOException e) {
            list = new ArrayList<Task>();
        }
        return list;
    }

    /**
     * Loads a task from the input string and adds it to the provided list.
     *
     * @param input The input string representing the task.
     * @param list  The list to which the task will be added.
     */
    public void loadTask(String input, List<Task> list) {
        boolean isMarked;
        int markedIndex = input.indexOf("|");
        isMarked = input.charAt(markedIndex + 2) == '1';
        int titleIndex = input.indexOf("|", markedIndex + 1) + 2;
        try {
            if (input.startsWith("T")) {
                String title = input.substring(titleIndex);
                list.add(new Todo(title, isMarked));
            } else if (input.startsWith("D")) {
                int byIndex = input.indexOf("|", titleIndex);
                String title = input.substring(titleIndex, byIndex);
                String dueDateString = input.substring(byIndex + 2);
                list.add(new Deadline(title, parseDate(dueDateString), isMarked));
            } else if (input.startsWith("E")) {
                int fromIndex = input.indexOf("|", titleIndex);
                String title = input.substring(titleIndex, fromIndex);
                int toIndex = input.indexOf("|", fromIndex + 1);
                String from = input.substring(fromIndex + 2, toIndex);
                String to = input.substring(toIndex + 2);
                list.add(new Event(title, parseDate(from), parseDate(to), isMarked));
            } else {
                throw new InvalidInputException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves tasks from the provided TaskList instance to the file specified in the constructor.
     *
     * @param tasks The TaskList instance containing tasks to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.list.size(); i++) {
                fw.write(tasks.list.get(i).toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a date-time string into a LocalDateTime instance.
     *
     * @param input The input date-time string.
     * @return A LocalDateTime instance parsed from the input string.
     * @throws InvalidDateFormatException If the input string has an invalid date-time format.
     */
    public LocalDateTime parseDate(String input) throws InvalidDateFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }
}
