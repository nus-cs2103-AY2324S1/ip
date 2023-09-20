package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.FishronException;
import parser.Parser;
import tasks.Task;
import tasks.TaskList;


/**
 * The Storage class handles loading and saving tasks to/from a file.
 */
public class Storage {
    private File file;

    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null;
        this.filePath = filePath;
        this.file = new File(filePath);

        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }

    /**
     * Loads tasks from the file and returns a TaskList containing them.
     *
     * @return A TaskList containing tasks loaded from the file.
     */
    public TaskList loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length >= 2) {
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    if (taskType.equals("T")) {
                        handleTodo(description, taskList, isDone);
                    } else if (taskType.equals("D")) {
                        handleDeadline(description, taskList, isDone, parts);
                    } else if (taskType.equals("E")) {
                        handleEvent(description, taskList, isDone, parts);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        } catch (FishronException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________");
        }
        return new TaskList(taskList);
    }

    /**
     * Handles the todo task.
     * @param description Description of the todo task.
     * @param taskList Task list to be added to.
     * @param isDone Boolean indicating whether the task is done.
     */
    private void handleTodo(String description, ArrayList<Task> taskList, boolean isDone) {
        Task task = Parser.parseTodo(description.trim());
        if (isDone) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    /**
     * Handles the deadline task.
     * @param description Description of the deadline task.
     * @param taskList Task list to be added to.
     * @param isDone Boolean indicating whether the task is done.
     * @param parts An array containing parts of the input line, split by the '|' character.
     */
    private void handleDeadline(String description, ArrayList<Task> taskList, boolean isDone, String[] parts)
            throws FishronException {
        String by = parts[3];
        Task task = Parser.parseDeadline(description.trim(), by.trim());
        if (isDone) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    /**
     * Handles the Event task.
     * @param description Description of the Event task.
     * @param taskList Task list to be added to.
     * @param isDone Boolean indicating whether the task is done.
     * @param parts An array containing parts of the input line, split by the '|' character.
     */
    private void handleEvent(String description, ArrayList<Task> taskList, boolean isDone, String[] parts)
            throws FishronException {
        String from = parts[3];
        String to = parts[4];
        Task task = Parser.parseEvent(description.trim(), from.trim(), to.trim());
        if (isDone) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    /**
     * Saves the provided task list to the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            PrintWriter printWriter = new PrintWriter(filePath);
            for (Task task : taskList) {
                printWriter.println(task.toFileString());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}
