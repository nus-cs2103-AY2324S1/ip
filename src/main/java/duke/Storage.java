package duke;

import duke.exception.InvalidDateTimeException;
import duke.taskClasses.Task;
import duke.taskClasses.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents a storage mechanism for saving and loading task lists.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new storage instance.
     *
     * @param filePath The path to the file where tasks are saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into a TaskList.
     *
     * @return A TaskList containing the loaded tasks.
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                try {
                    String line = s.nextLine().trim();
                    String[] taskParts = line.split("\\|");
                    boolean isDone = taskParts[1].trim().equals("1");

                    switch (taskParts[0].trim()) {
                        case "T":
                            taskList.addToDoToList(isDone, taskParts[2].trim());
                            break;
                        case "D":
                            taskList.addDeadlineToList(isDone, taskParts[2].trim(), taskParts[3].trim());
                            break;
                        case "E":

                            taskList.addEventToList(isDone, taskParts[2].trim(), taskParts[3].trim(), taskParts[4].trim());
                            break;
                        default:
                            System.out.println("Unexpected task type encountered: " + line);
                }
            } catch (InvalidDateTimeException e) {
                    System.out.println("Invalid date format encountered in some tasks. Please check.");
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found. Initializing an empty task list.");
        }
        return taskList;
    }

    /**
     * Writes the given task list to the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void writeToDB(TaskList taskList) {
        StringBuilder content = new StringBuilder();
        for (Task task : taskList.getList()) {
            content.append(task.getDBString()).append("\n");
        }

        try {
            Path path = Paths.get(this.filePath);
            Files.write(path, content.toString().getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
