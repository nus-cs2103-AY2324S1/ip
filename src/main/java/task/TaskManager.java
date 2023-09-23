package task;

import java.io.IOException;
import java.util.ArrayList;

import storage.Database;

/**
 * Class for manipulating the list of tasks
 */
public class TaskManager {
    private static TaskManager obj;
    private static final String FILE_ERROR_MSG = "There is an issue with the file database. "
            + "You are required to delete the file in your User/[username]/EvanData folder "
            + "and rerun the program.";
    private ArrayList<Task> list;

    /**
     * Private constructor for TaskManager
     */
    private TaskManager() {
        try {
            list = Database.loadTasks();
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    /**
     * Factory method to enforce one instance of the list manager
     * @return an instance of the list manager
     */
    public static TaskManager init() {
        if (obj == null) {
            obj = new TaskManager();
        }
        return obj;
    }

    /**
     * Takes in a task, adds it into the list and saves it into the database
     * @param task task to be added
     * @return string message log of adding the task
     */
    public String addTask(Task task) {
        list.add(task);

        try {
            Database.saveTasks(list);
        } catch (IOException e) {
            return FILE_ERROR_MSG;
        }

        StringBuilder dialog = new StringBuilder("Got it. I've added this task:\n")
                .append(task)
                .append("\n")
                .append("Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        return dialog.toString();
    }

    /**
     * Gets a string report of all the tasks in the list
     * @return String message log of all tasks in the list
     */
    public String getAllTasks() {
        Task[] tasks = list.toArray(new Task[0]);
        StringBuilder dialog = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.length; i++) {
            int listIndex = i + 1;
            Task task = tasks[i];
            dialog.append(listIndex)
                    .append(".");

            if (i < tasks.length - 1) {
                dialog.append(task)
                        .append("\n");
            } else {
                dialog.append(task);
            }
        }

        return dialog.toString();
    }

    /**
     * Takes in an integer index and marks the task associated with the index as done
     * @param index index of the task
     * @return String message report of marking the task as done
     */
    public String markDone(int index) {
        Task element = list.get(index - 1);
        element.markAsDone();

        try {
            Database.saveTasks(list);
        } catch (IOException e) {
            return FILE_ERROR_MSG;
        }

        StringBuilder dialog = new StringBuilder();
        dialog.append("Nice! I've marked this task as done:\n")
                .append(element);
        return dialog.toString();
    }

    /**
     * Takes in an integer index and marks the task associated with the index as not done
     * @param index index of the task
     * @return String message report of marking the task as not done
     */
    public String unmarkDone(int index) {
        Task element = list.get(index - 1);
        element.markAsNotDone();

        try {
            Database.saveTasks(list);
        } catch (IOException e) {
            return FILE_ERROR_MSG;
        }

        StringBuilder dialog = new StringBuilder();
        dialog.append("OK! I've marked this task as not done yet:\n")
                .append(element);
        return dialog.toString();
    }

    /**
     * Takes in an integer index and deletes the task associated with the index
     * @param index index of the task
     * @return String message report of deleting task
     */
    public String deleteTask(int index) {

        Task element = list.get(index - 1);
        list.remove(index - 1);

        try {
            Database.saveTasks(list);
        } catch (IOException e) {
            return FILE_ERROR_MSG;
        }

        StringBuilder dialog = new StringBuilder();
        dialog.append("Noted. I've removed this task:\n")
                .append(element)
                .append("\n")
                .append("Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        return dialog.toString();
    }

    /**
     * Finds all tasks which contains the keyword and returns a string report of filtered tasks
     * @param keyword in the task description
     * @return String message report of all tasks with the keyword
     */
    public String findTask(String keyword) {
        StringBuilder dialog = new StringBuilder("Here are the matching tasks in your list with its correct "
                + "corresponding index numbers: \n");

        Task[] tasks = list.toArray(new Task[0]);

        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            int listIndex = i + 1;

            if (task.description.contains(keyword)) {
                dialog.append(listIndex)
                        .append(".")
                        .append(task)
                        .append("\n");
            }
        }
        return dialog.toString();
    }
}
