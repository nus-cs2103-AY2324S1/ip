package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents user interface of the chatbot.
 */
public class Ui {
    private Scanner scanner;
    private String message;
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public Ui () {
        this.scanner = new Scanner(System.in);
        this.message = "";
    }

    /**
     * Returns the next line of user input from the user interface.
     *
     * @return Next line of user input.
     */
    public String nextCommand() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    /**
     * Prints the entry message of the chatbot.
     */
    public void printEntryMessage() {
        String entryMessage = HORIZONTAL_LINE
                + "Hello! I'm Chad \n"
                + "What can I do for you? \n"
                + HORIZONTAL_LINE;
        System.out.println(entryMessage);
    }

    /**
     * Prints the exit message of the chatbot.
     */
    public void addExitMessage() {
        this.message = "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints the current message in the Ui object and clears the message.
     */
    public void printMessage() {
        System.out.print(HORIZONTAL_LINE);
        System.out.print(this.message);
        System.out.println(HORIZONTAL_LINE);
        this.message = "";
    }

    /**
     * Adds all the String representation of Tasks in a given TaskList.
     *
     * @param taskList The given TaskList.
     */
    public void addTaskList(TaskList taskList) {
        this.message += taskList.toString();
    }

    /**
     * Adds a message to indicate that a given task has been successfully added.
     */
    public void addAddMessage() {
        this.message += "Got it. I've added this task:\n";
    }

    /**
     * Adds a message to indicate that a given task has been successfully deleted.
     */
    public void addDeleteMessage() {
        this.message += "Noted. I've removed this task:\n";
    }

    /**
     * Adds a message to indicate that a given task has been successfully marked.
     */
    public void addMarkMessage() {
        this.message += "Nice! I've marked this task as done:\n";
    }

    /**
     * Adds a message to indicate that a given task has been successfully unmarked.
     */
    public void addUnmarkMessage() {
        this.message += "OK, I've marked this task as not done yet:\n";
    }

    /**
     * Add the error message from a given Exeception.
     *
     * @param e The Exception thrown from the execution of a method.
     */
    public void addErrorMessage(Exception e) {
        this.message += "Oops! we encountered an error\n" + e.getMessage() + "\n";
    }

    /**
     * Adds the error message when the parser cannot find a matching command.
     */
    public void addCommandNotFound() {
        this.message += "Oops! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Adds the message to indicate the find query is successful.
     */
    public void addFindMessage() {
        this.message += "Here are the matching tasks in your list:\n";
    }

    /**
     * Adds the String representation of the given Task into the message.
     *
     * @param task The given Task to be added into the message of the UI.
     */
    public void addTaskMessage(Task task) {
        this.message += task.toString() + "\n";
    }

    /**
     * Adds the message indicating the size of the TaskList.
     *
     * @param taskList The given TaskList.
     */
    public void addTaskListSizeMessage(TaskList taskList) {
        this.message += "Now you have " + taskList.size() + " tasks in the list." + "\n";
    }
}
