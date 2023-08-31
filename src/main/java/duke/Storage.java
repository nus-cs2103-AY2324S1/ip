package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
     * @param task_List The TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList task_List) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            List<Task> taskList = task_List.getTask_List();
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
     * @param task_List A list of tasks to reload.
     */
    public void reloadTasksToFile(List<Task> task_List) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : task_List) {
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
            List<Task> task_List = new ArrayList<>();

            while((inputLines = reader.readLine()) != null) {
                String [] taskSplit = inputLines.split("\\|");
                String taskType = taskSplit[0];
                String status = taskSplit[1];
                String taskDescription = taskSplit[2];

                if (taskType.equals("T")) {
                    task_List.add(new Todo(taskDescription, status));
                } else if (taskType.equals("D")) {
                    String by = taskSplit[3];
                    task_List.add(new Deadline(taskDescription, by, status));
                } else if (taskType.equals("E")) {
                    String from = taskSplit[3];
                    String to =  taskSplit[4];
                    task_List.add(new Event(taskDescription, from, to, status));
                }
            }
            reloadTasksToFile(task_List);
            return task_List;
        } catch (IOException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
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
