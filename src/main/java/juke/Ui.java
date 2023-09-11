package juke;

import java.util.ArrayList;

/**
 * Handles the printing of responses toward user inputs.
 *
 * @author lshaoqin
 */
public class Ui {

    /**
     * Prints the message to welcome the user.
     */
    public static String printStart() {
        return "Hello! I'm Juke!\n"
        + "What can I do for you?\n";
    }

    /**
     * Prints the error message.
     * @param error The JukeError which was encountered.
     */
    public String printError(JukeError error) {
        return error.getMessage();
    }

    /**
     * Prints bye to the user.
     */
    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the lists of tasks that the user has saved.
     * @param tasks The list of tasks to be printed.
     */
    public String printList(ArrayList<Task> tasks) {
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task task : tasks) {
            list.append(count).append(": ").append(task.toString()).append("\n");
            count++;
        }
        return list.toString();
    }

    /**
     * Prints message after a task is marked as uncompleted.
     * @param task The task which was unmarked.
     */
    public String unmark(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task.toString();
    }

    /**
     * Prints message after a task is marked as completed.
     * @param task The task which was marked.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Prints message after a task is tagged.
     * @param task The task which was tagged.
     */
    public String addTag(Task task) {
        return "Great! You have added a tag to this task: \n" + task.toString();
    }

    /**
     * Prints message after a task is deleted.
     * @param task The task which was deleted.
     * @param size The number of tasks in the TaskList.
     */
    public String delete(Task task, int size) {
        return "Noted. I've removed this task:\n"
        + "\t" + task.toString() + "\n"
        + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints message after a task is created.
     * @param task The task which was created.
     * @param size The number of tasks in the TaskList.
     */
    public String createTask(Task task, int size) {
        return "Got it. I've added this task:\n"
        + "\t" + task.toString() + "\n"
        + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints out corresponding tasks based on search term.
     * @param results The results of the search.
     */
    public String find(ArrayList<Task> results) {
        if (results.size() == 0) {
            return "No results found.";
        } else {
            int count = 1;
            StringBuilder tasks = new StringBuilder();
            for (Task task : results) {
                tasks.append(count).append(". ").append(task.toString()).append("\n");
                count++;
            }
            return tasks.toString();
        }
    }

}
