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
    private final String filePath;

    /**
     * Initializes a new instance of the Storage class.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
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
    public void writeToDB(TaskList taskList) {
        StringBuilder content = buildDbContentFromTaskList(taskList);

        try {
            writeContentToFile(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private void processFileContents(TaskList taskList) throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            processSingleLine(scanner.nextLine().trim(), taskList);
        }

        scanner.close();
    }

    private void processSingleLine(String line, TaskList taskList) {
        try {
            String[] taskParts = line.split("\\|");
            boolean isDone = taskParts[1].trim().equals("1");

            switch (taskParts[0].trim()) {
                case "T":
                    assert taskParts.length == 3 : "ToDo task data format is incorrect";
                    taskList.addToDoToList(isDone, taskParts[2].trim());
                    break;
                case "D":
                    assert taskParts.length == 4 : "Deadline task data format is incorrect";
                    taskList.addDeadlineToList(isDone, taskParts[2].trim(), taskParts[3].trim());
                    break;
                case "E":
                    assert taskParts.length == 5 : "Event task data format is incorrect";
                    taskList.addEventToList(isDone, taskParts[2].trim(), taskParts[3].trim(), taskParts[4].trim());
                    break;
                default:
                    System.out.println("Unexpected task type encountered: " + line);
            }
        } catch (InvalidDateTimeException e) {
            System.out.println("Invalid date format encountered in some tasks. Please check.");
        }
    }

    private StringBuilder buildDbContentFromTaskList(TaskList taskList) {
        StringBuilder content = new StringBuilder();

        for (Task task : taskList.getList()) {
            content.append(task.getDbString()).append("\n");
        }

        return content;
    }

    private void writeContentToFile(StringBuilder content) throws IOException {
        Path path = Paths.get(this.filePath);
        Files.write(path, content.toString().getBytes());
    }
}
