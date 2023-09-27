package devybot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

import devybot.exceptions.DevyBotException;
import devybot.exceptions.UnknownCommandException;

import devybot.tasks.DeadlineTask;
import devybot.tasks.EventTask;
import devybot.tasks.Task;
import devybot.tasks.TodoTask;

/**
 * The Storage class handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from a file and returns them as a list of Task objects.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DevyBotException If an error occurs during loading.
     */
    public ArrayList<Task> loadTasksFromFile() throws DevyBotException {
        ArrayList<Task> taskList = new ArrayList<>();

        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                Task loadedTask = createTaskFromLine(taskString);
                taskList.add(loadedTask);
            }
        } catch (FileNotFoundException e) {
            Ui.showMessage("No existing tasks file found. Starting with an empty task list.");
        }

        return taskList;
    }

    /**
     * Creates a Task object from a string representation.
     *
     * @param taskString The string representation of the task.
     * @return A Task object representing the task.
     * @throws DevyBotException If the task string is in an invalid format.
     */
    public Task createTaskFromLine(String taskString) throws DevyBotException {
        String[] taskParts = taskString.split(" \\| ");

        if (taskParts.length < 3) {
            throw new DevyBotException("Invalid task format: " + taskString);
        }

        String taskType = taskParts[0].trim();
        String taskStatus = taskParts[1].trim();
        String taskDescription = taskParts[2].trim();

        Task loadedTask;

        switch (taskType) {
        case "T":
            loadedTask = createTodoTask(taskDescription, taskStatus);
            break;
        case "D":
            String taskBy = taskParts[3].trim();
            loadedTask = createDeadlineTask(taskDescription, taskBy, taskStatus);
            break;
        case "E":
            String taskFrom = taskParts[3].trim();
            String taskTo = taskParts[4].trim();
            loadedTask = createEventTask(taskDescription, taskFrom, taskTo, taskStatus);
            break;
        default:
            throw new UnknownCommandException();
        }
        return loadedTask;
    }

    /**
     * Creates a TodoTask object from a description and status.
     *
     * @param description The description of the task.
     * @param status      The status of the task (1 for done, 0 for undone).
     * @return A TodoTask object.
     * @throws DevyBotException If an error occurs during creation.
     */
    public Task createTodoTask(String description, String status) throws DevyBotException {
        Task task = new TodoTask(description);
        if (status.equals("1")) {
            task.markTask();
        }
        return task;
    }

    /**
     * Creates a DeadlineTask object from a description, taskBy, and status.
     *
     * @param description The description of the task.
     * @param taskBy      The task's deadline or due date.
     * @param status      The status of the task (1 for done, 0 for undone).
     * @return A DeadlineTask object.
     * @throws DevyBotException If an error occurs during creation.
     */
    public Task createDeadlineTask(String description, String taskBy, String status) throws DevyBotException {
        Task task;
        if (taskBy.contains(" ")) {
            // Contains time, parse as LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(taskBy,
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            task = new DeadlineTask(description, dateTime);
        } else {
            // No time, parse as LocalDate
            LocalDate date = LocalDate.parse(taskBy, DateTimeFormatter.ofPattern("d/M/yyyy"));
            task = new DeadlineTask(description, date);
        }
        if (status.equals("1")) {
            task.markTask();
        }
        return task;
    }

    /**
     * Creates an EventTask object from a description, taskFrom, taskTo, and status.
     *
     * @param description The description of the task.
     * @param taskFrom    The starting date and time of the event.
     * @param taskTo      The ending date and time of the event.
     * @param status      The status of the task (1 for done, 0 for undone).
     * @return An EventTask object.
     * @throws DevyBotException If an error occurs during creation.
     */
    public Task createEventTask(String description, String taskFrom, String taskTo, String status)
            throws DevyBotException {
        LocalDateTime fromDateTime = LocalDateTime.parse(taskFrom, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        LocalDateTime toDateTime = LocalDateTime.parse(taskTo, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        Task task = new EventTask(description, fromDateTime, toDateTime);
        if (status.equals("1")) {
            task.markTask();
        }
        return task;
    }

    /**
     * Saves tasks from a TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList taskList) {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
                assert dataDir.exists() : "Directory not created";
            }
            FileWriter fileWriter = new FileWriter(filePath);
            assert fileWriter != null : "FileWriter should not be null";
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.getTask(i).toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.showMessage("An error occurred while saving tasks.");
        }
    }
}
