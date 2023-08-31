package duke;

import exceptions.FileUnloadableException;
import exceptions.ParseTaskFromStringException;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Storage class handles reading and writing tasks to/from a file.
 */
public class Storage {
    private static String filePath = "tasks.txt";

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts a date string to a LocalDateTime object.
     *
     * @param by The date string to be converted.
     * @return The LocalDateTime object representing the date.
     * @throws DateTimeParseException If the date string is in an invalid format.
     */
    public static LocalDateTime saveAsDate(String by) throws DateTimeParseException {
        try {
            // Try to parse the input as "yyyy-MM-dd" format
            return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            by += " 0000";
            // If parsing as "yyyy-MM-dd" format fails, try to parse as "yyyy-MM-dd HHmm" format
            return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }
    }

    /**
     * Generates the content for the task file based on a list of tasks.
     *
     * @param taskArray The list of tasks to generate content for.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void generateTaskFileContent(ArrayList<Task> taskArray) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task task : taskArray) {
            content.append(writeTaskLine(task));
        }
        writeToFile("tasks.txt", content.toString());
    }

    /**
     * Writes the given text to the specified file path.
     *
     * @param filePath The path of the file to write to.
     * @param textToAdd The text content to be added to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Generates a formatted string representation of a Task object for writing to the task file.
     *
     * @param task The Task object to generate the formatted string for.
     * @return The formatted string representation of the Task object.
     */
    private static String writeTaskLine(Task task) {
        Boolean isDone = task.isDone();
        String taskLine = task.getType() + " | " + (isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        return taskLine;
    }

    /**
     * Loads tasks from the file and returns them as a list of Task objects.
     *
     * @return A list of Task objects read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws FileUnloadableException If the file cannot be loaded.
     * @throws ParseTaskFromStringException If task parsing from string encounters an error.
     */
    public static ArrayList<Task> load() throws IOException, FileUnloadableException, ParseTaskFromStringException {
        ArrayList<Task> taskArray = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromString(line);
                taskArray.add(task);
            }
        }
        return taskArray;
    }

    /**
     * Parses a task from a string representation and returns the corresponding Task object.
     *
     * @param line The string representation of the task to be parsed.
     * @return A Task object representing the parsed task.
     * @throws ParseTaskFromStringException If an error occurs during task parsing.
     */
    public static Task parseTaskFromString(String line) throws ParseTaskFromStringException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new ParseTaskFromStringException(line);
        }
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String taskDescription = parts[2].trim();

        if (taskType.equals("T") && parts.length == 3) {
            Task task = new ToDo(taskDescription);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("D") && parts.length == 4) {
            LocalDateTime by = Storage.saveAsDate(parts[3].trim());
            Task task = new Deadline(taskDescription, by);
            setStatus(task, isDone);
            return task;
        } else if (taskType.equals("E") && parts.length == 5) {
            LocalDateTime start = Storage.saveAsDate(parts[3].trim());
            LocalDateTime end = Storage.saveAsDate(parts[4].trim());
            Task task = new Event(taskDescription, start, end);
            setStatus(task, isDone);
            return task;
        } else {
            // Handle unrecognized task type
            throw new ParseTaskFromStringException(line);
        }
    }

    /**
     * Sets the status of a Task object based on whether it is marked as done.
     *
     * @param task The Task object for which to set the status.
     * @param isDone Whether the task is marked as done (true) or not (false).
     */
    private static void setStatus(Task task, boolean isDone) {
        if (isDone) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }
}
