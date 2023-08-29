package task;

import ui.Reply;

import storage.Database;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Class for manipulating the list of tasks
 */
public class TaskList {
    private static TaskList obj;
    private ArrayList<Task> list;
    private final Reply reply = Reply.init();

    /**
     * private constructor
     */
    private TaskList() {
        list = Database.loadData();
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
    public void addTask(Task task) {
        list.add(task);
        Database.save(this.list);

        StringBuilder dialog = new StringBuilder("Got it. I've added this task:\n       ")
                .append(task)
                .append("\n     ")
                .append("Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        reply.printDialog(dialog.toString());
    }

    /**
     * Prints all the tasks in the list
     */
    public void printTasks() {
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

        reply.printDialog(dialog.toString());
    }

    /**
     * Takes in an integer index and marks the task associated with the index as done
     * @param index index of the task
     */
    public void markDone(int index) {
        Task element = list.get(index - 1);
        element.markAsDone();

        Database.save(list);

        StringBuilder dialog = new StringBuilder();
        dialog.append("Nice! I've marked this task as done:\n")
                .append("       ")
                .append(element);
        reply.printDialog(dialog.toString());
    }

    /**
     * Takes in an integer index and marks the task associated with the index as not done
     * @param index index of the task
     */
    public void unmarkDone(int index) {
        Task element = list.get(index - 1);
        element.markAsNotDone();

        Database.save(list);

        StringBuilder dialog = new StringBuilder();
        dialog.append("OK! I've marked this task as not done yet:\n")
                .append("       ")
                .append(element);
        reply.printDialog(dialog.toString());
    }

    /**
     * Takes in an integer index and deletes the task associated with the index
     * @param index index of the task
     */
    public void deleteTask(int index) {

        Task element = list.get(index - 1);
        list.remove(index - 1);

        Database.save(list);

        StringBuilder dialog = new StringBuilder();
        dialog.append("Noted. I've removed this task:\n")
                .append("       ")
                .append(element)
                .append("\n")
                .append("     Now you have ")
                .append(list.size())
                .append(" tasks in the list.");
        reply.printDialog(dialog.toString());
    }

    /**
     * Finds all tasks which contains the keyword and prints it
     * @param keyword
     */
    public void findTask(String keyword) {
        StringBuilder dialog = new StringBuilder("Here are the matching tasks in your list with its correct " +
                "corresponding index numbers: \n     ");

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
        reply.printDialog(dialog.toString());
    }
}
