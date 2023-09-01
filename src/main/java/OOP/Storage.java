package OOP;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Duke.DukeException;

public class Storage {
    /** The file path of the storage file for loading and saving tasks */
    private String filePath;

    /**
     * Constructs a Storage instance.
     *
     * @param filePath The file path to the storage file to be used for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks into the storage file with the encapsulated filePath in an appropriate format.
     *
     * @param tasks The TaskList instance that contains the tasks to be saved.
     */
    public void save(TaskList tasks) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task : tasks.getTasks()) {
                writer.write(task.toString(true));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Make sure to close the writer in the finally block
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Loads the tasks from the storage file and returns them as a list.
     *
     * @return tasks The list of tasks that were loaded from the storage file.
     */

    public List<Task> load() {
        String directoryPath = filePath.split("/")[0];
        BufferedReader reader = null;
        List<Task> tasks = new ArrayList<>();

        try {
            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Create the file if it doesn't exist
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line contains task information, parse and create tasks accordingly
                Task task = parseTaskFromString(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Make sure to close the reader in the finally block
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tasks;
        }
    }

    /**
     * Parses a string from the storage file to return the appropriate task.
     *
     * @param line The string containing the current line, i.e., the current task in its storage string representation.
     * @return The task of the appropriate class based on the string from the storage file.
     */

    public static Task parseTaskFromString(String line) {
        String[] taskData = line.split("\\s*\\|\\s*");
        boolean isDone = taskData[1].equals("1");
        switch (taskData[0]) {
            case "T":
                ToDo todo = new ToDo(taskData[2], isDone);
                return todo;
            case "D":
                return parseDeadlineFromString(taskData[2], taskData[3]);
            case "E":
                Event event = new Event(taskData[2], isDone, taskData[3], taskData[4]);
                return event;
        }
        return null;
    }

    /**
     * Parses a name and a string containing a deadline to give a Deadline object.
     *
     * @param name The name of the deadline.
     * @param deadlineString The string in yyyy-MM-dd HHmm format that represents the date/time of the deadline.
     * @return The Deadline object based on the 2 parameters provided.
     */

    public static Deadline parseDeadlineFromString(String name, String deadlineString) {
        DateTimeFormatter formatter = null;
        Deadline deadline = null;
        if (isValidDate(deadlineString)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDeadlineDate = LocalDate.parse(deadlineString, formatter);
            deadline = new Deadline(name, false, parsedDeadlineDate);
        } else if (isValidDateTime(deadlineString)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDeadlineDateTime = LocalDateTime.parse(deadlineString, formatter);
            deadline = new Deadline(name, false, parsedDeadlineDateTime);
        } else {
            throw new DukeException("\t Invalid deadline format. " +
                                    "\n\tExpected format for deadline (time is optional):" +
                                    "\n\t deadline {deadlineName} /by yyyy-MM-dd HHmm");
        }
        return deadline;
    }

    /**
     * Determines if a string is of the format "yyyy-MM-dd" i.e., a valid LocalDate object.
     *
     * @param input The string to be examined.
     * @return A boolean value which tells us if the string represents a LocalDate.
     */

    private static boolean isValidDate(String input) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    /**
     * Determines if a string is of the format "yyyy-MM-dd HHmm" i.e., a valid LocalDateTime object.
     *
     * @param input The string to be examined.
     * @return A boolean value which tells us if the string represents a valid LocalDateTime.
     */
    private static boolean isValidDateTime(String input) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{4}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
