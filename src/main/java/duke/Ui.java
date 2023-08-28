package duke;

import duke.task.Task;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {
    private String divider = "____________________________________________________________";
    private String indent = "     ";

    /*
     * Adds indentation to the string given and prints it out.
     * 
     * @params s the string to be indented
     */
    public void formatString(String s) {
        System.out.println(indent + s);
    }

    /**
     * Shows the divider line in the UI.
     */
    public void showLine() {
        formatString(divider);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String chatbotName = "Miles";

        showLine();
        formatString(" Hey! I'm " + chatbotName + "!");
        formatString(" What can I do for you, my friend?");
        showLine();
    }

    /**
     * Prints the task that was just added.
     * 
     * @param task the task that was just added
     * @param n    the number of tasks currently in the list
     */
    public void printAddedTask(Task task, int n) {
        showLine();
        formatString(" Gotcha. I've added this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        showLine();
    }

    /**
     * Prints the task that was just deleted.
     * 
     * @param task the task that was just deleted
     * @param n    the number of tasks currently in the list
     */
    public void printDeletedTask(Task task, int n) {
        showLine();
        formatString(" Noted. I've removed this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        showLine();
    }

    /**
     * Exits the program.
     */
    public void exit() {
        showLine();
        formatString(" Stay safe my friend. See you again soon man.");
        showLine();
    }
}
