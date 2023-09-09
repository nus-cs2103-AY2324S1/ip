package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * This class encapsulates a local database for Duke bot.
 */
public class Storage {
    private final Path DATABASE_PATH;

    public Storage(String filePath) {
        this.DATABASE_PATH = java.nio.file.Paths.get(
            System.getProperty("user.dir"), filePath);

        if (!databaseExist()) {
            createDatabase();
        }
    }

    /**
     * Creates a local text file for saving the lists of tasks if it does not exist.
     */
    public void createDatabase() {
        File database = new File(DATABASE_PATH.toString());

        try {
            database.getParentFile().mkdirs();
            database.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the database exists.
     *
     * @return True if the text file exists.
     */
    public boolean databaseExist() {
        File database = new File(DATABASE_PATH.toString());
        return database.exists();
    }


    /**
     * Parses the string of tasks from the database into Task objects.
     *
     * @return The list of tasks stored in the database.
     * @throws DukeException If corrupted database.
     */
    protected ArrayList<Task> readFromDatabase() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(DATABASE_PATH.toString());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(stringToTask(line));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return taskList;
    }

    /**
     * Updates the database.
     *
     * @param taskList The list containing the tasks to be saved.
     */
    public void writeToDatabase(ArrayList<Task> taskList) {
        assert databaseExist();

        try {
            FileWriter writer = new FileWriter(DATABASE_PATH.toString(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : taskList) {
                bufferedWriter.write(task.toDatabaseRepresentation());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task stringToTask(String line) throws DukeException {
        try {
            Task newTask;
            String[] taskInfo = line.split(" \\| ");

            switch (taskInfo[0]) {
                case "T":
                    newTask = new Todo(taskInfo[2]);
                    break;
                case "D":
                    newTask = new Deadline(taskInfo[2], taskInfo[3]);
                    break;
                case "E":
                    newTask = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                    break;
                default:
                    throw new DukeException("Database is corrupted.");
            }

            if (taskInfo[1].equals("1")) {
                newTask.markAsDone();
            }

            return newTask;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Converts a predefined format of string to LocalDateTime.
     *
     * @param input The user input.
     * @return The LocalDateTime containing the date written in the input.
     */
    public static LocalDateTime stringToDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Converts LocalDateTime to a format that is more tilable in the database.
     *
     * @param date The date to be converted.
     * @return The database representation of the date inputted.
     */
    public static String dateToDatabaseRepresentation(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);
    }

    /**
     * Converts LocalDateTime to a format that is more readable.
     *
     * @param date The date to be converted.
     * @return Date in a more readable format.
     */
    public static String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
        return date.format(formatter);
    }
}
