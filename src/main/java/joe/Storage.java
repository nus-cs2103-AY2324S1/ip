package joe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import joe.exceptions.JoeException;
import joe.tasks.DeadlineTask;
import joe.tasks.EventTask;
import joe.tasks.Task;
import joe.tasks.TodoTask;

/**
 * Handles the storage of tasks to a file.
 */
public class Storage {
    // Patterns for parsing task file entries
    private static final Pattern TASK_PATTERN = Pattern.compile("^\\[([TDE])\\]\\[([X\\s])\\]\\s(.+)");
    private static final Pattern TODO_PATTERN = Pattern.compile("^\\[T\\]\\[[X\\s]\\]\\s+(.+)$");
    private static final Pattern DEADLINE_PATTERN =
            Pattern.compile("^\\[D\\]\\[[X\\s]\\]\\s+(.+)\\s+\\(by:\\s+(.+)\\)$");
    private static final Pattern EVENT_PATTERN =
            Pattern.compile("^\\[E\\]\\[[X\\s]\\]\\s+(.+)\\s+\\(from:\\s+(.+)\\s+to:\\s+(.+)\\)$");

    private static final String CORRUPT_TASK_FILE_MSG = "Task file is corrupt";
    private static final String DATETIME_FORMAT = "dd MMM yyyy HH:mm";
    private final Path taskFilePath;

    /**
     * Constructs a Storage object with the specified file name.
     *
     * @param fileName The name of the file to store tasks.
     */
    public Storage(String fileName) {
        this.taskFilePath = Paths.get(fileName);
    }

    // Handle different task types
    private void handleTodo(String input, boolean isDone, TaskList tasks) throws JoeException {
        Matcher m = TODO_PATTERN.matcher(input);
        if (!m.matches()) {
            throw new JoeException(CORRUPT_TASK_FILE_MSG);
        }

        TodoTask newTask = new TodoTask(m.group(1));

        if (isDone) {
            newTask.markAsDone();
        }

        tasks.add(newTask);
    }

    private void handleDeadline(String input, boolean isDone, TaskList tasks) throws JoeException {
        Matcher m = DEADLINE_PATTERN.matcher(input);
        if (!m.matches()) {
            throw new JoeException(CORRUPT_TASK_FILE_MSG);
        }

        LocalDateTime by = parseStringToLocalDateTime(m.group(2));

        DeadlineTask newTask = new DeadlineTask(m.group(1), by);

        if (isDone) {
            newTask.markAsDone();
        }

        tasks.add(newTask);
    }

    private void handleEvent(String input, boolean isDone, TaskList tasks) throws JoeException {
        Matcher m = EVENT_PATTERN.matcher(input);
        if (!m.matches()) {
            throw new JoeException(CORRUPT_TASK_FILE_MSG);
        }

        LocalDateTime from = parseStringToLocalDateTime(m.group(2));
        LocalDateTime to = parseStringToLocalDateTime(m.group(3));

        EventTask newTask = new EventTask(m.group(1), from, to);

        if (isDone) {
            newTask.markAsDone();
        }

        tasks.add(newTask);
    }

    private LocalDateTime parseStringToLocalDateTime(String s) throws JoeException {
        try {
            return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(Storage.DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new JoeException(CORRUPT_TASK_FILE_MSG);
        }
    }

    /**
     * Reads tasks from the file and returns them as a TaskList.
     *
     * @return A TaskList containing the tasks read from the file.
     * @throws JoeException If there is a problem parsing the task file.
     * @throws IOException  If there is an I/O error while reading the file.
     */
    public TaskList readTasks() throws JoeException, IOException {
        TaskList tasks = new TaskList();

        if (!Files.exists(taskFilePath)) {
            throw new FileNotFoundException();
        }

        List<String> lines = Files.readAllLines(taskFilePath);

        for (String line : lines) {
            Matcher m = TASK_PATTERN.matcher(line);

            if (!m.matches()) {
                throw new JoeException(CORRUPT_TASK_FILE_MSG);
            }

            String type = m.group(1);
            boolean isDone = m.group(2).equals(Task.DONE_SYMBOL);

            switch (type) {
            case "T":
                handleTodo(line, isDone, tasks);
                break;
            case "D":
                handleDeadline(line, isDone, tasks);
                break;
            case "E":
                handleEvent(line, isDone, tasks);
                break;
            default:
                throw new JoeException(CORRUPT_TASK_FILE_MSG);
            }
        }

        //Every line is correct parsed into a task
        assert lines.size() == tasks.size();

        return tasks;
    }

    /**
     * Saves tasks from a TaskList to the file.
     *
     * @param tasks The TaskList containing tasks to be saved to the file.
     */
    public void saveToFile(TaskList tasks) {
        try {
            List<String> taskStrings = tasks.getStringList();
            Files.write(
                    taskFilePath,
                    taskStrings,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed to save to file: " + e);
        }
    }
}
