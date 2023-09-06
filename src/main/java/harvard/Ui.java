package harvard;
import java.util.Scanner;
/**
 * Represents a user interface.
 */

public class Ui {


    /**
     * Displays the welcome message.
     */
    public String displayWelcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }
//    /**
//     * Reads the command.
//     * @return The command in String.
//     */
//    public String readCommand() {
//        return scanner.nextLine();
//    }
    /**
     * Displays a line.
     */
    public String displayLine() {
//        System.out.println("____________________________________________________________");
        return "____________________________________________________________";
    }
    /**
     * Displays the bye message.
     */
    public String displayBye() {
//        displayLine();
//        System.out.println("Bye. Hope to see you again soon!");
//        displayLine();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays adding task message.
     * @param task The task to be added.
     * @param tasks The list of tasks.
     */
    public String showAddTask(Task task, TaskList tasks) {
//        displayLine();
//        System.out.println("Got it. I've added this task:\n" + task
//                + "\nNow you have " + tasks.size() + " tasks in the list.");
//        displayLine();
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    /**
     * Displays the list of tasks.
     * @param tasks The list of tasks.
     */
    public String showList(TaskList tasks) {
//        displayLine();
//        System.out.println("Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + "." + tasks.get(i));
//        }
//        displayLine();
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n" + (i + 1) + "." + tasks.get(i);
        }
        return result;
    }

    /**
     * Shows the delete message.
     * @param task The task to be deleted.
     * @param tasks The list of tasks.
     */
    public String showDelete(Task task, TaskList tasks) {
//        displayLine();
//        System.out.println("Noted. I've removed this task:\n" + task
//                + "\nNow you have " + tasks.size() + " tasks in the list.");
//        displayLine();
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Shows the done message.
     * @param task The task to be marked as done.
     */
    public String showDone(Task task) {
//        displayLine();
//        System.out.println("Nice! I've marked this task as done:\n" + task);
//        displayLine();
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Shows the undone message.
     * @param task The task to be marked as undone.
     */

    public String showUndone(Task task) {
//        displayLine();
//        System.out.println("Ok! I've marked this task as not done yet:");
//        displayLine();
        return "Ok! I've marked this task as not done yet:";
    }
    /**
     * Shows the find message.
     * @param matchingTasks The list of matching tasks.
     */

    public String showFind(TaskList matchingTasks) {
//        displayLine();
//        System.out.println("Here are the matching tasks in your list:");
//        for (int i = 0; i < matchingTasks.size(); i++) {
//            System.out.println((i + 1) + "." + matchingTasks.get(i));
//        }
//        displayLine();
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < matchingTasks.size(); i++) {
            result += "\n" + (i + 1) + "." + matchingTasks.get(i);
        }
        return result;
    }
    /**
     * Displays the error message.
     * @param e The error.
     */
    public String displayError(DukeException e) {
//        System.out.println(e.getMessage());
        return e.getMessage();
    }
}
