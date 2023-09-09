package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * The Ui, aka Userinterface that handles all the outputs shows to user.
 */
public class Ui {
    /**
     * Instantiates a new Ui.
     */
    public Ui() {}

    /**
     * Displays the welcome message.
     */
    public void introMessage() {
        System.out.println("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the contents in the TaskList list.
     *
     * @param list the list
     */
    public void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    /**
     * Displays the message when a Task is marked as done by user.
     *
     * @param task the task
     */
    public void markedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message when the user does not type in what Task is to be marked.
     */
    public void invalidMark() {
        System.out.println("Enter a valid number to mark");
    }

    /**
     * Displays the message when a Task is successfully added to the TaskList list.
     *
     * @param task the task
     */
    public void addedMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @param list the list
     */
    public void listMessage(ArrayList<Task> list)  {
        System.out.println("Now you have "  + list.size() + " tasks in the list.");
    }

    /**
     * Displays the message when a Task is successfully removed from the TaskList list.
     *
     * @param task the task
     */
    public void removedMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message when the user does not type in what Task is to be marked.
     */
    public void validNumberMessage() {
        System.out.println("Enter a valid number to mark");
    }

    /**
     * Displays the goodbye message when the program is about to terminate expectedly.
     */
    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void findmsg() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public String introMessageString() {
        return ("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM" + "%n" + "What can I do for you?");
    }

    /**
     * Displays the contents in the TaskList list.
     *
     * @param list the list
     */
    public String printListString(ArrayList<Task> list) {
        String out = "";
        for (int i = 0; i < list.size(); i++) {
            out += i + 1 + "." + list.get(i) + "%n";
        }
        return out;
    }

    /**
     * Displays the message when a Task is marked as done by user.
     *
     * @param task the task
     */
    public String markedMessageString(Task task) {
        return ("Nice! I've marked this task as done:" + "%n" + "  " + task);
    }

    /**
     * Displays the message when the user does not type in what Task is to be marked.
     */
    public String invalidMarkString() {
        return "Enter a valid number to mark";
    }

    /**
     * Displays the message when a Task is successfully added to the TaskList list.
     *
     * @param task the task
     */
    public String addedMessageString(Task task) {
        return ("Got it. I've added this task:" + "%n" + "  " + task.toString());
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @param list the list
     */
    public String listMessageString(ArrayList<Task> list)  {
        return ("Now you have "  + list.size() + " tasks in the list.");
    }

    /**
     * Displays the message when a Task is successfully removed from the TaskList list.
     *
     * @param task the task
     */
    public String removedMessageString(Task task) {
        return ("Noted. I've removed this task:" + "%n" + "  " + task);
    }

    /**
     * Displays the message when the user does not type in what Task is to be marked.
     */
    public String validNumberMessageString() {
        return "Enter a valid number to mark";
    }

    /**
     * Displays the goodbye message when the program is about to terminate expectedly.
     */
    public String byeMessageString() {
        return "Bye. Hope to see you again soon!";
    }

    public String findmsgString() {
        return "Here are the matching tasks in your list:";
    }
}
