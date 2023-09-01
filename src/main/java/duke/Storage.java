package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles saving and parsing saved task files.
 */
public class Storage {
    private final String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get the file handle of the save file, or create the directories and the save file if it does not exist.
     *
     * @return A File object referencing the save file.
     * @throws DukeException if the directories or file cannot be created.
     */
    private File getOrCreateFile() throws DukeException {
        Path path = Paths.get(this.filePath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new DukeException(Ui.LINE + e.getMessage() + Ui.LINE);
        }

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new DukeException(Ui.LINE + e.getMessage() + Ui.LINE);
            }
        }

        return file;
    }

    /**
     * Load the tasks from the save file.
     *
     * @return An ArrayList containing all the tasks.
     * @throws DukeException if there is an error accessing the file, or if the file data was corrupted.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        File file = this.getOrCreateFile();
        String regexPattern = "([TDE])\\s\\|\\s"    // match type
                + "([01])\\s\\|\\s"                 // match done or not done
                + "([^/|]*[^/|\\s])"                // match description
                + "(?:\\s\\|\\s([^/|]*[^/|\\s]))?"  // match /from or /by
                + "(?:\\s\\|\\s([^/|]*[^/|\\s]))?"; // match /to
        Pattern pattern = Pattern.compile(regexPattern);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                Matcher matcher = pattern.matcher(str);

                if (!matcher.matches()) {
                    throw new DukeException(Ui.LINE + Messages.MESSAGE_CORRUPT_FILE + Ui.LINE);
                }

                switch (matcher.group(1)) {
                case "T":
                    if (matcher.group(2) == null || matcher.group(3) == null) {
                        throw new DukeException(Ui.LINE + Messages.MESSAGE_CORRUPT_FILE + Ui.LINE);
                    }

                    Todo newTodo = new Todo(matcher.group(3));
                    if (matcher.group(2).equals("1")) {
                        newTodo.markAsDone();
                    }
                    tasks.add(newTodo);
                    break;
                case "D":
                    if (matcher.group(2) == null
                            || matcher.group(3) == null
                            || matcher.group(4) == null) {
                        throw new DukeException(Ui.LINE + Messages.MESSAGE_CORRUPT_FILE + Ui.LINE);
                    }

                    LocalDateTime parsedDate;
                    try {
                        parsedDate = LocalDateTime.parse(matcher.group(4),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    } catch (DateTimeParseException e) {
                        throw new DukeException(Ui.LINE + Messages.MESSAGE_CORRUPT_FILE + Ui.LINE);
                    }

                    Deadline newDeadline = new Deadline(matcher.group(3), parsedDate);
                    if (matcher.group(2).equals("1")) {
                        newDeadline.markAsDone();
                    }
                    tasks.add(newDeadline);
                    break;
                case "E":
                    if (matcher.group(2) == null
                            || matcher.group(3) == null
                            || matcher.group(4) == null
                            || matcher.group(5) == null) {
                        throw new DukeException(Ui.LINE + Messages.MESSAGE_CORRUPT_FILE + Ui.LINE);
                    }

                    Event newEvent = new Event(matcher.group(3), matcher.group(4), matcher.group(5));
                    if (matcher.group(2).equals("1")) {
                        newEvent.markAsDone();
                    }
                    tasks.add(newEvent);
                    break;
                }
            }
        } catch (IOException e) {
            throw new DukeException(Ui.LINE + e.getMessage() + Ui.LINE);
        }

        return tasks;
    }

    /**
     * Save the tasks into the save file
     *
     * @param tasks The ArrayList containing all the tasks.
     * @throws DukeException if there is an error accessing or writing to the save file.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        getOrCreateFile();
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(tasks.getSaveString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(Ui.LINE + e.getMessage() + Ui.LINE);
        }
    }
}
