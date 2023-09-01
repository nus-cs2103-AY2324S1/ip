package duke;
import duke.task.Task;

import java.util.Scanner;

public class Ui {

    static final String LOGO = "   /\\_/\\  \n" +
            "  ( o.o ) \n" +
            "   > ^ <\n";
    static final String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";

    static final String EXIT = "Bye(T.T), Hope to see you again soon!";
    private Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the command from user input.
     * @return String that read by scanner.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(GREETING);
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodByeMessage() {
        System.out.println(EXIT);
    }

    /**
     * Prints the task list with certain format.
     * @param taskList Task list that going to be printed.
     * @throws DukeException If there is a exception when accessing task list.
     */
    public void printList(TaskList taskList) throws DukeException
    {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    /**
     * Prints the mark message when mark a task.
     * @param task Task that going to be marked.
     */
    public void printMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" +
                "    " + task.toString());
    }

    /**
     * Prints the unmark message when unmark a task.
     * @param task Task that going to be unmarked.
     */
    public void printUnmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "    " + task.toString());
    }

    /**
     * Prints the add message when add a task inside the task list.
     * @param task Task that going to be added into task list.
     * @param taskList Task list that stored the task.
     */
    public void printAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it, I've added this task:\n    "  +
                task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the delete message when deleting a task.
     * @param task Task that going to be deleted.
     * @param taskList Task list that stored the task.
     */
    public void printDeleteMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");

    }

    /**
     * Prints the matching task list in certain format.
     * @param taskList Task list that going to be printed.
     * @throws DukeException When there is an exception.
     */
    public void printMatchingList(TaskList taskList) throws DukeException

    {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
    }


    /**
     * Shows erroe message.
     * @param s The error message.
     */
    public void showErrorMessage(String s) {
        System.out.println(s);
    }

    /**
     * Prints the line.
     */
    public void showLine() {
        System.out.println("_____________________________________________________________________________________");
    }

}
