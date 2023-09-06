package task;

import java.io.IOException;
import java.util.ArrayList;

import storage.Database;

/**
 * Class for manipulating the list of tasks
 */
public class TaskList {
    private static TaskList obj;
    private ArrayList<Task> list;

    /**
     * private constructor
     */
    private TaskList() {
        try {
            list = Database.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    /**
     * factory method to enforce one instance of the list manager
     * @return an instance of the list manager
     */
    public static TaskList init() {
        if (obj == null) {
            obj = new TaskList();
        }
        return obj;
    }

    /**
     * Takes in a task, adds it into the list and saves it into the database
     * @param task task to be added
     */
    public String addTask(Task task) {
        list.add(task);

        try {
            Database.save(list);
        } catch (IOException e) {
            return "Oops! Sorry! There is an issue with the file database. "
                    + "You are required to delete the file and recreate one with the same name.";
        }

        StringBuilder dialog = new StringBuilder("Got it. I've added this task:\n       ")
                .append(task)
                .append("\n     ")
                .append("Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        return dialog.toString();
    }

    /**
     * Prints all the tasks in the list
     */
    public String printTasks() {
        Task[] tasks = list.toArray(new Task[0]);
        StringBuilder dialog = new StringBuilder("Here are the tasks in your list:\n     ");

        for (int i = 0; i < tasks.length; i++) {
            int listIndex = i + 1;
            Task task = tasks[i];
            dialog.append(listIndex)
                    .append(".");

            if (i < tasks.length - 1) {
                dialog.append(task)
                        .append("\n     ");
            } else {
                dialog.append(task);
            }
        }

        return dialog.toString();
    }

    /**
     * Takes in an integer index and marks the task associated with the index as done
     * @param index index of the task
     */
    public String markDone(int index) {
        Task element = list.get(index - 1);
        element.markAsDone();

        try {
            Database.save(list);
        } catch (IOException e) {
            return "Oops! Sorry! There is an issue with the file database. "
                    + "You are required to delete the file and recreate one with the same name.";
        }

        StringBuilder dialog = new StringBuilder();
        dialog.append("Nice! I've marked this task as done:\n")
                .append("       ")
                .append(element);
        return dialog.toString();
    }

    /**
     * Takes in an integer index and marks the task associated with the index as not done
     * @param index index of the task
     */
    public String unmarkDone(int index) {
        Task element = list.get(index - 1);
        element.markAsNotDone();

        try {
            Database.save(list);
        } catch (IOException e) {
            return "Oops! Sorry! There is an issue with the file database. "
                    + "You are required to delete the file and recreate one with the same name.";
        }

        StringBuilder dialog = new StringBuilder();
        dialog.append("OK! I've marked this task as not done yet:\n")
                .append("       ")
                .append(element);
        return dialog.toString();
    }

    /**
     * Takes in an integer index and deletes the task associated with the index
     * @param index index of the task
     */
    public String deleteTask(int index) {

        Task element = list.get(index - 1);
        list.remove(index - 1);

        try {
            Database.save(list);
        } catch (IOException e) {
            return "Oops! Sorry! There is an issue with the file database. "
                    + "You are required to delete the file and recreate one with the same name.";
        }

        StringBuilder dialog = new StringBuilder();
        dialog.append("Noted. I've removed this task:\n")
                .append("       ")
                .append(element)
                .append("\n")
                .append("     Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        return dialog.toString();
    }

    /**
     * Finds all tasks which contains the keyword and prints it
     * @param keyword
     */
    public String findTask(String keyword) {
        StringBuilder dialog = new StringBuilder("Here are the matching tasks in your list with its correct "
                + "corresponding index numbers: \n     ");

        Task[] tasks = list.toArray(new Task[0]);

        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            int listIndex = i + 1;

            if (task.description.contains(keyword)) {
                dialog.append(listIndex)
                        .append(".")
                        .append(task)
                        .append("\n     ");
            }
        }
        return dialog.toString();
    }
}
