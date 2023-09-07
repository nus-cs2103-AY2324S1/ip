package duke;

import exception.DukeException;
import task.Task;

import java.io.InputStream;
import java.util.Scanner;

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
     * get user Input and pass the input to Parser class.
     *
     * @param tasks store the current list of tasks
     */
    public void handleUserInput(TaskList tasks) {
        try {
            Parser.replyUser(this.in.nextLine(), tasks, this);
        } catch (DukeException e){
            this.showErrorMsg(e);
        }
    }

    /**
     * Output the initial greeting to user.
     *
     * @param name of the chatBot
     */
    public String printGreeting(String name) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Hello! I'm " + name + "!");
        result.append("\n");
        result.append("What can I do for you?");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output the greeting before chatBot terminates.
     */
    public String exitGreeting() {
        this.exit = true;
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Bye. Hope to see you again soon!");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output message that indicate mark function is successful.
     * @param task store the current list of tasks.
     */
    public String markSuccess(Task task) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Nice! I've marked this task as done: \n" + task);
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output message that indicate unMark function is successful.
     * @param task store the current list of tasks.
     */
    public String unMarkSuccess(Task task) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("OK, I've marked this task as not done yet: \n" + task);
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output message that indicate adding todo is successful.
     * @param task store the current list of tasks.
     */
    public String toDoSuccess(Task task, int size) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output message that indicate adding deadline is successful.
     * @param task store the current list of tasks.
     */
    public String deadLineSuccess(Task task, int size) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output message that indicate adding event is successful.
     * @param task store the current list of tasks.
     */
    public String eventSuccess(Task task, int size) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output message that indicate delete task is successful.
     * @param task store the current list of tasks.
     */
    public String deleteSuccess(Task task, int size) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Noted. I've removed this task: \n" + task +
                "\nNow you have " + size + " tasks in the list.");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output all the user input.
     */
    public String outputList(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append(DIVIDER);
        output.append("\n");
        output.append("Here are the tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }
        output.append("\n");
        output.append(DIVIDER);

        return output.toString();
    }

    /**
     * Output customized reply for specific userInput.
     */
    public String customReply() {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append("Hi barbie!");
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Output exception msg in a formatted output.
     * @param e exception.
     */
    public String showErrorMsg(Exception e) {
        StringBuilder result = new StringBuilder();
        result.append(DIVIDER);
        result.append("\n");
        result.append(e.getMessage());
        result.append("\n");
        result.append(DIVIDER);
        return result.toString();
    }

    /**
     * Return Exit variable.
     *
     * @return {@link this.exit}
     */
    public boolean isExit() {
        return this.exit;
    }

    public String findSuccess(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append(DIVIDER);
        output.append("\n");
        output.append("Here are the matching tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }

        output.append("\n");
        output.append(DIVIDER);

        return output.toString();
    }
}
