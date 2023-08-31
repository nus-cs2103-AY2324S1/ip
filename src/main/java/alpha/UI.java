package alpha;

import java.util.Scanner;

/**
 * Class that handles ChatBot Alpha's inputs and outputs with the user.
 * Author: Wong Joon Hung
 */
public class UI {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private String intro = LINE_SEPARATOR + "\n" +
            " Hello! I'm Alpha\n" +
            " What can I do for you?\n" + LINE_SEPARATOR;

    // Outro message
    private String end = LINE_SEPARATOR + "\n" +
            " Bye. Hope to see you again soon!\n" +
            LINE_SEPARATOR;

    public UI() {
    }

    /**
     * Outputs Chatbot Alpha's Introduction message.
     */
    public void introduce() {
        System.out.println(intro);
    }

    /**
     * Outputs Chatbot Alpha's Goodbye message.
     */
    public void goodbye() {
        System.out.println(end);
    }

    /**
     * Outputs a message to inform the user that a task was added.
     *
     * @param task Task that is being added.
     * @param size Current size of the task list.
     */
    public void taskAdded(Task task, int size) {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Alright! I've added this task:\n " + " " + task
                + "\nNow you have " + size + " tasks in the list.\n" +
                LINE_SEPARATOR);
    }

    /**
     * Outputs a message to tell the user a task has been marked.
     *
     * @param task Task that is being marked.
     */
    public void mark(Task task) {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Nice! I've marked this task as done:\n" + "  " +
                task +
                "\n" + LINE_SEPARATOR);
    }

    /**
     * Outputs a message to tell the user a task has been unmarked.
     *
     * @param task Task that is being unmarked.
     */
    public void unmark(Task task) {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Cool! I've marked this task as not done yet:\n" + "  " +
                task +
                "\n" + LINE_SEPARATOR);

    }

    /**
     * Outputs a message to tell the user a task has been deleted and the size of the remaining list.
     *
     * @param task Task that is being marked.
     * @param size Remaining size of the task list.
     */
    public void delete(Task task, int size) {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Noted. I've removed this task:\n" + "  " + task + "\n Now You have " + size +
                " tasks in the list." + "\n" + LINE_SEPARATOR);
    }

    /**
     * Outputs a display of the current list.
     *
     * @param taskList Current taskList.
     */
    public void list(TaskList taskList) {
        int size = taskList.size();
        if (size == 0) {
            System.out.println(LINE_SEPARATOR + "\n" + "There are no tasks in your list." + "\n" + LINE_SEPARATOR);
        } else {
            System.out.println(LINE_SEPARATOR + "\n" +
                    "Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                int plusOne = i + 1; // Increment by one so starting display index is 1
                System.out.println(plusOne + ". " + taskList.getTask(i));
            }
            System.out.println(LINE_SEPARATOR);
        }
    }

    /**
     * Returns the current user input.
     *
     * @return the current user input.
     */
    public String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
