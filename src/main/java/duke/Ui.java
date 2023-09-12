package duke;

import java.io.InputStream;
import java.util.Scanner;

import task.Task;

public class Ui {

    /** Break line for each input and output */
    private static final String DIVIDER = "________________________________________________________";

    /** instance that read input from user */
    private final Scanner in;

    /** Indicator to terminate the chatBot */
    private boolean exit;

    public Ui() {
        this(System.in);
    }
    public Ui(InputStream in) {
        this.exit = false;
        this.in = new Scanner(in);
    }


    /**
     * Output the greeting before chatBot terminates.
     */
    public String exitGreeting() {
        this.exit = true;
        return addMessageFormat("Bye. Hope to see you again soon!");
    }

    /**
     * Output message that indicate mark function is successful.
     * @param task store the current list of tasks.
     */
    public String markSuccess(Task task) {
        return addMessageFormat("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Output message that indicate unMark function is successful.
     * @param task store the current list of tasks.
     */
    public String unMarkSuccess(Task task) {
        return addMessageFormat("OK, I've marked this task as not done yet: \n" + task);
    }

    /**
     * Output message that indicate adding todo is successful.
     * @param task store the current list of tasks.
     */
    public String toDoSuccess(Task task, int size) {
        return addMessageFormat("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Output message that indicate adding deadline is successful.
     * @param task store the current list of tasks.
     */
    public String deadLineSuccess(Task task, int size) {
        return addMessageFormat("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Output message that indicate adding event is successful.
     * @param task store the current list of tasks.
     */
    public String eventSuccess(Task task, int size) {
        return addMessageFormat("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Output message that indicate delete task is successful.
     * @param task store the current list of tasks.
     */
    public String deleteSuccess(Task task, int size) {
        return addMessageFormat("Noted. I've removed this task: \n" + task
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Output all the user input.
     */
    public String outputList(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }

        return addMessageFormat(output.toString());
    }

    /**
     * Output customized reply for specific userInput.
     */
    public String customReply() {
        return addMessageFormat("Hi barbie!");
    }

    /**
     * Output exception msg in a formatted output.
     * @param e exception.
     */
    public String showErrorMsg(Exception e) {
        return addMessageFormat(e.getMessage());
    }

    /**
     * Return Exit variable.
     *
     * @return {@link this.exit}
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Output list of tasks in a string format.
     *
     * @param tasks type Tasklist
     * @return String of tasks
     */
    public String findSuccess(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }
        return addMessageFormat(output.toString());
    }

    private String addMessageFormat(String message) {
        String result = DIVIDER + "\n" + message + "\n" + DIVIDER;
        return result;
    }

    public String helpGenerator() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the list of actions and format: \n");
        output.append("todo TASK \n");
        output.append("deadline TASK /by DATE \n");
        output.append("event TASK /from DATE /to DATE \n");
        output.append("mark NUMBER \n");
        output.append("unmark NUMBER \n");
        output.append("find TASK \n");
        output.append("delete NUMBER \n");
        output.append("TASK: description of user's task \n");
        output.append("DATE: date in dd/MM/yyyy HHmm \n");
        output.append("NUMBER: index number of task");
        return addMessageFormat(output.toString());
    }
}
