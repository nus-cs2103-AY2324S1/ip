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
    public void formatString(String s) {
        System.out.println(s);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String chatbotName = "Miles";

        formatString("Hey! I'm " + chatbotName + "!");
        formatString("What can I do for you, my friend?");
    }

    /**
     * Prints the task that was just added.
     * @param task the task that was just added
     * @param n    the number of tasks currently in the list
     */
    public void printAddedTask(Task task, int n) {
        formatString("Gotcha. I've added this task:");
        formatString(task.toString());
        formatString("Now you have " + n + " tasks in the list.");
    }

    /**
     * Prints the task that was just deleted.
     * @param task the task that was just deleted
     * @param n    the number of tasks currently in the list
     */
    public void printDeletedTask(Task task, int n) {
        formatString("Noted. I've removed this task:");
        formatString(task.toString());
        formatString("Now you have " + n + " tasks in the list.");
    }
    
    /**
     * Prints the error message when an exception is raised.
     * @param errorMsg error message to be printed
     */
    public void printErrorMsg(String errorMsg) {
        formatString(errorMsg);
    }

    /**
     * Prints the error message when the task number is invalid.
     * @param number the task number that is invalid
     */
    public void printInvalidTaskNumber(int number) {
        formatString("There is no task " + number + ", friend.");
    }

    /**
     * Exits the program.
     */
    public void exit() {
        formatString("Stay safe my friend. See you again soon man.");
    }
}
