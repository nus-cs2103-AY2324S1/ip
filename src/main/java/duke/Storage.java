package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exception.InvalidDateTimeException;
import duke.taskclasses.Task;
import duke.taskclasses.TaskList;

/**
 * Represents the storage mechanism responsible for saving and loading tasks to and from a file.
 */
public class Storage {
    private static final String DATA_DIRECTORY = "./data/";
    private static final String FILE_EXTENSION = ".txt";
    private static final String TODO_PREFIX = "T";
    private static final String DEADLINE_PREFIX = "D";
    private static final String EVENT_PREFIX = "E";

    private final String filePath;

    /**
     * Initializes a new instance of the Storage class.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = DATA_DIRECTORY + filePath + FILE_EXTENSION;
    }

    /**
     * Loads tasks from the storage file and returns them as a TaskList.
     *
     * @return A TaskList containing tasks loaded from the storage file.
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        try {
            processFileContents(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found. Initializing an empty task list.");
        }
        return taskList;
    }

    /**
     * Saves the provided task list to the storage file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void writeToStorage(TaskList taskList) {
        StringBuilder content = buildDbContentFromTaskList(taskList);
        try {
            writeContentToFile(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            try {
                new File(this.filePath).createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException("Error creating a new file.", ex);
            }
        }
    }

    /**
     * Reads each line from the file and processes the tasks.
     */
    private void processFileContents(TaskList taskList) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(this.filePath))) {
            while (scanner.hasNext()) {
                processSingleLine(scanner.nextLine().trim(), taskList);
            }
        }
    }

    /**
     * Process a single line from the file to create the respective Task object.
     */
    private void processSingleLine(String line, TaskList taskList) {
        try {
            String[] taskParts = line.split("\\|");
            boolean isDone = "1".equals(taskParts[1].trim());
            switch (taskParts[0].trim()) {
            case TODO_PREFIX:
                assert taskParts.length == 3 : "ToDo task data format is incorrect";
                taskList.addToDoToList(isDone, taskParts[2].trim());
                break;
            case DEADLINE_PREFIX:
                assert taskParts.length == 4 : "Deadline task data format is incorrect";
                taskList.addDeadlineToList(isDone, taskParts[2].trim(), taskParts[3].trim());
                break;
            case EVENT_PREFIX:
                assert taskParts.length == 5 : "Event task data format is incorrect";
                taskList.addEventToList(isDone, taskParts[2].trim(), taskParts[3].trim(), taskParts[4].trim());
                break;
            default:
                System.out.println("Unexpected task type encountered: " + line);
            }
        } catch (InvalidDateTimeException e) {
            System.out.println("Invalid date format encountered in: " + line);
        }
    }

    /**
     * Build a string representation of the entire task list for saving to file.
     */
    private StringBuilder buildDbContentFromTaskList(TaskList taskList) {
        StringBuilder content = new StringBuilder();
        for (Task task : taskList.getList()) {
            content.append(task.getDbString()).append("\n");
        }
        return content;
    }

    /**
     * Writes the content to the storage file.
     */
    private void writeContentToFile(StringBuilder content) throws IOException {
        Path path = Paths.get(this.filePath);
        Files.write(path, content.toString().getBytes());
    }
}
