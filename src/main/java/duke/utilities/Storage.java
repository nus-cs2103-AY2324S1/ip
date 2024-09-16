package duke.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * The Storage class handles saving and loading tasks from a file.
 * It provides methods to save, reload, and load tasks from a specified file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks from the TaskList to the file specified in the constructor.
     *
     * @param storedTaskList The TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList storedTaskList) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            List<Task> taskList = storedTaskList.getTaskList();
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reloads the tasks from a list and updates the file specified in the constructor.
     *
     * @param storedTaskList A list of tasks to reload.
     */
    public void reloadTasksToFile(List<Task> storedTaskList) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : storedTaskList) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified in the constructor and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     */

    public List<Task> loadTaskFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String inputLines;
            List<Task> taskList = new ArrayList<>();

            while ((inputLines = reader.readLine()) != null) {
                String [] taskSplit = inputLines.split("\\|");
                String taskType = taskSplit[0];
                String status = taskSplit[1];
                String taskDescription = taskSplit[2];

                if (taskType.equals("T")) {
                    taskList.add(new Todo(taskDescription, status));
                } else if (taskType.equals("D")) {
                    String by = taskSplit[3];
                    taskList.add(new Deadline(taskDescription, by, status));
                } else if (taskType.equals("E")) {
                    String from = taskSplit[3];
                    String to = taskSplit[4];
                    taskList.add(new Event(taskDescription, from, to, status));
                }
            }
            reloadTasksToFile(taskList);
            return taskList;
        } catch (IOException e) {
            System.out.println("IOException Error");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        } catch (Exception e) {
            System.out.println("The file to store your tasks entered cannot be accessed.");
        }
        return null;
    }

    /**
     * Clears the content of the specified file.
     *
     * @param filePath The path to the file to be cleared.
     */
    public void clearTasksInFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
