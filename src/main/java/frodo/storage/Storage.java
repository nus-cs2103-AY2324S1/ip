package frodo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.DukeException;

import frodo.parser.Parser;
import frodo.tasks.Deadlines;
import frodo.tasks.Events;
import frodo.tasks.Task;
import frodo.tasks.TaskList;
import frodo.tasks.ToDos;

/**
 * Manages the storage of the tasks in local files.
 */
public class Storage {
    public static String filePath;

    /**
     * Constructs a Storage instance using the specified file path.
     *
     * @param filePath Path of the storage file.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return List of tasks retrieved from the file.
     * @throws DukeException If there is an issue loading data.
     */
    public List<Task> load() throws DukeException {
        try {
            return loadFromFile(filePath);
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file not found. Starting with an empty task list.");
        } catch (Exception e) {
            throw new DukeException("An error occurred while loading the data.");
        }
    }

    /**
     * Updates the file with the current list of tasks.
     *
     * @param tasks Task list to be saved.
     */
    public void updateData(TaskList tasks) {
        try {
            saveToFile(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    /**
     * Loads tasks from a specified file.
     *
     * @param filename Name of the file.
     * @return List of tasks retrieved from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public List<Task> loadFromFile(String filename) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        Parser parser = new Parser();

        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (!isValidFormat(line)) {
                    throw new DukeException("Data file is corrupted. Line not in expected format: " + line);
                }

                Task task = parser.parseTaskFromFile(line);
                tasks.add(task);
            }
            scanner.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty task list.");
        }
        return tasks;
    }

    /**
     * Validates the format of a line from the file.
     *
     * @param line Line from the file.
     * @return true if the line matches the expected format, false otherwise.
     */
    public boolean isValidFormat(String line) {
        return line.matches("^[TDE] \\| [01] \\| .+");
    }

    /**
     * Saves the tasks to a file.
     *
     * @param tasks Task list to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(taskToFileString(task) + "\n");
            }
        }
    }

    /**
     * Generates the string representation of a task for the file.
     *
     * @param task Task to be converted.
     * @return String representation of the task.
     */
    public String generateTaskStringForFile(Task task) {
        if (task instanceof ToDos) {
           return ("T | ");
        } else if (task instanceof Deadlines) {
            return ("D | ");
        } else if (task instanceof Events) {
            return ("E | ");
        }
        return "";
    }

    /**
     * Generates the date string for a task.
     *
     * @param task Task with date to be converted.
     * @return String representation of the task date.
     */
    public String generateTaskDatesForFile(Task task) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String res ="";
        if (task instanceof Deadlines) {
            res += " | " + (((Deadlines) task).getDate().format(outputFormatter));
        } else if (task instanceof Events) {
            res += " | " + ((Events) task).getStartDate().format(outputFormatter)
            + " | " + ((Events) task).getEndDate().format(outputFormatter);
        }
        return res;
    }

    /**
     * Converts a task to its string representation for storage.
     *
     * @param task Task to be converted.
     * @return String representation suitable for file storage.
     */
    public String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        //append task string
        String taskString = generateTaskStringForFile(task);
        sb.append(taskString);

        //append task completion status
        sb.append(task.isCompleted() ? "1" : "0").append(" | ");

        //append task description
        sb.append(task.getDescription());

        //append task dates
        String dateString = generateTaskDatesForFile(task);
        sb.append(dateString);

        return sb.toString();
    }

    /**
     * Archives the tasks in a new file.
     *
     * @param tasks Task list to be archived.
     * @return Status message after the archiving process.
     */
    public String archiveTasksInNewFile(TaskList tasks) {
        File archiveFile = new File("data/archive.txt");

        try {
            FileWriter fw = new FileWriter(archiveFile, true);
            for (Task task : tasks.getTasks()) {
                fw.write(taskToFileString(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            return "Error archiving tasks " + e.getMessage();
        }
        return "Successfully archived tasks in a new file";
    }

    /**
     * Clears all data from the main storage file.
     *
     * @return Status message after the clearing process.
     */
    public String clearFile() {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            return "Error clearing main tasks for archive action " + e.getMessage();
        }
        return "Successfully cleared data from current file.";
    }
}
