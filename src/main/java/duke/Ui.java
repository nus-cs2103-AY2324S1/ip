package duke;
import duke.task.Task;

public class Ui {
    private String DIVIDER = "____________________________________________________________";
    private String INDENT = "     ";

    /*
     * Adds indentation to the string given and prints it out.
     * 
     * @params s the string to be indented
     */
    public void formatString(String s) {
        System.out.println(INDENT + s);
    }

    public void showLine() {
        formatString(DIVIDER);
    }

    public void greet() {
        String chatbotName = "Miles";

        showLine();
        formatString(" Hey! I'm " + chatbotName + "!");
        formatString(" What can I do for you, my friend?");
        showLine();
    }

    public void printAddedTask(Task task, int n) {
        showLine();
        formatString(" Gotcha. I've added this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        showLine();
    }

    public void printDeletedTask(Task task, int n) {
        showLine();
        formatString(" Noted. I've removed this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        showLine();
    }

    public void exit() {
        showLine();
        formatString(" Stay safe my friend. See you again soon man.");
        showLine();
    }
}
