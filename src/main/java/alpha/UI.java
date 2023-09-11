package alpha;

import java.util.Scanner;

/**
 * Class that handles ChatBot Alpha's inputs and outputs with the user.
 * Author: Wong Joon Hung
 */
public class UI {

    private String intro =
            " Hello! I'm Alpha\n" +
            " What can I do for you?\n";

    // Outro message
    private String end =
            " Bye. Hope to see you again soon!\n";

    public UI() {
    }

    /**
     * Outputs Chatbot Alpha's Introduction message.
     */
    public String introduce() {
        return intro;
    }

    /**
     * Outputs Chatbot Alpha's Goodbye message.
     *
     * @return
     */
    public String goodbye() {
        return end;
    }

    /**
     * Outputs a message to inform the user that a task was added.
     *
     * @param task Task that is being added.
     * @param size Current size of the task list.
     */
    public String taskAdded(Task task, int size) {
        return "Alright! I've added this task:\n " + " " + task
                + "\nNow you have " + size + " tasks in the list.\n";
    }

    /**
     * Outputs a message to tell the user a task has been marked.
     *
     * @param task Task that is being marked.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n" + "  " + task;
    }

    /**
     * Outputs a message to tell the user a task has been unmarked.
     *
     * @param task Task that is being unmarked.
     */
    public String unmark(Task task) {
        return "Cool! I've marked this task as not done yet:\n" + task;

    }

    /**
     * Outputs a message to tell the user a task has been deleted and the size of the remaining list.
     *
     * @param task Task that is being marked.
     * @param size Remaining size of the task list.
     */
    public String delete(Task task, int size) {
        return "Noted. I've removed this task:\n" + "  " + task + "\n Now You have " + size +
                " tasks in the list.";
    }

    /**
     * Outputs a display of the current list.
     *
     * @param taskList Current taskList.
     */
    public String list(TaskList taskList) {
        int size = taskList.size();
        String res = "";
        if (size == 0) {
            res = "There are no tasks in your list.";
        } else {
            assert taskList.size() > 0;
            res = "Here are the tasks in your list:\n";
            for (int i = 0; i < size; i++) {
                int plusOne = i + 1; // Increment by one so starting display index is 1
                res += plusOne + ". " + taskList.getTask(i) + "\n";
            }
        }
        return res;
    }

}
