package ally;

import java.util.ArrayList;
import java.util.Scanner;

import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Task;

/**
 * Ui Class for the design of the chatbot.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm ALLY\nWhat can I do for you?\n";
    private static final String BYE = "Bye. Hope to see you again soon!";

    private Scanner ipt = new Scanner(System.in);

    /**
     * Reads the next line of the input.
     *
     * @return next line of the input
     */
    public String readCommand() {
        return this.ipt.nextLine();
    }

    /**
     * Prints LINE.
     */
    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Function that provides the starting message and greeting.
     */
    public static void start() {
        showLine();
        System.out.println(GREETING);
        showLine();
    }

    /**
     * Function that provides the bye message when the user
     * ends the chatbot.
     *
     * @return
     */
    public String bye() throws AllyException {
        return "bye";
    }

    /**
     * Prints the list of tasks in allyList.
     *
     * @param allyList
     */
    public String showList(AllyList allyList) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0, len = allyList.getSize(); i < len; i++) {
            stringBuilder.append(i + 1).append(". ").append(allyList.getTask(i).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Prints the deleted tasks and total number of tasks left.
     *
     * @param task
     * @param total
     * @return
     */
    public String showDelete(Task task, int total) {
        assert task != null;
        String str1 = "Noted. I've removed this task:";
        String str2 = "\n" + "\t" + task;
        String str3 = "\n" + "Now you have " + total + " tasks in the list.";
        return str1 + str2 + str3;
    }

    /**
     * Marks the task as done and prints to inform the user the task is done.
     *
     * @param task
     */
    public String showMarked(Task task) {
        task.setMarkDone();
        assert task != null;
        String str1 = "\n Nice! I've marked this task as done:";
        String str2 = "\n" + "\t" + task;
        return str1 + str2;
    }

    /**
     * Marks the task as not done and prints to inform the user the task is not done.
     *
     * @param task
     * @return
     */
    public String showNotMarked(Task task) {
        assert task != null;
        task.setMarkNotDone();
        String str1 = "\nOK, I've marked this task as not done yet:";
        String str2 = "\n" + "\t" + task;
        return str1 + str2;
    }

    /**
     * Throws AllyException if load() function fails.
     *
     * @throws AllyException
     */
    public void showLoadingError() throws AllyException {
        throw new AllyException("Unable to load!");
    }

    /**
     * Shows error from run() function in Ally.
     *
     * @param message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows the matching tasks.
     *
     * @param tasks ArrayList
     * @return
     */
    public String showMatchingTask(ArrayList<Task> tasks) {
        assert tasks != null;
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.get(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
