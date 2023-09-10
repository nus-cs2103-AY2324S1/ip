package miles;

import miles.task.Task;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {
    /**
     * Adds indentation to the string given and prints it out.
     * @params s the string to be indented
     */
    public void display(String s) {
        System.out.println(s);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        display("Hey! I'm Miles!");
        display("What can I do for you, my friend?");
    }

    /**
     * Prints the task that was just added.
     * @param task the task that was just added
     * @param n    the number of tasks currently in the list
     */
    public void printAddedTask(Task task, int n) {
        display("Gotcha. I've added this task:");
        display(task.toString());
        display("Now you have " + n + " tasks in the list.");
    }

    /**
     * Prints the task that was just deleted.
     * @param task the task that was just deleted
     * @param n    the number of tasks currently in the list
     */
    public void printDeletedTask(Task task, int n) {
        display("Noted. I've removed this task:");
        display(task.toString());
        display("Now you have " + n + " tasks in the list.");
    }

    /**
     * Prints a message when user inputs a duplicate task.
     * @param task
     */
    public void printDuplicateTask(Task task) {
        display("Brother, you already have this task in your list. Don't give yourself extra work man.");
    }

    /**
     * Prints the error message when an exception is raised.
     * @param errorMsg error message to be printed
     */
    public void printErrorMsg(String errorMsg) {
        display(errorMsg);
    }

    /**
     * Prints the error message when the task number is invalid.
     * @param number the task number that is invalid
     */
    public void printInvalidTaskNumber(int number) {
        display("There is no task " + number + ", friend.");
    }

    /**
     * Exits the program.
     */
    public void exit() {
        display("Stay safe my friend. See you again soon man.");
    }
}
