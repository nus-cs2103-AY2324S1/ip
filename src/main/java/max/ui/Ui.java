package max.ui;

import max.tasks.Task;
import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with user.
 */
public class Ui {
    private static String line = "____________________________________________________________";

    /**
     * Initialises UI.
     */
    public Ui() {
    }

    /**
     * Prints greeting message.
     */
    public String showGreeting() {
//        System.out.println("     Hello from");
//        System.out.println("       /\\/\\   __ ___  __");
//        System.out.println("      /    \\ / _` \\ \\/ /");
//        System.out.println("     / /\\/\\ \\ (_| |>  <");
//        System.out.println("     \\/    \\/\\__,_/_/\\_\\");
//        System.out.println("     How may I assist you?");
//        System.out.println(line);
        return "Helloooo I am Max your favorite chatbot";
    }

    /**
     * Opens a scanner to read user input.
     *
     * @return the user input.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Prints exit message.
     */
    public String exit() {
        return "Bye! Please come again!";
    }

    /**
     * Prints line divider.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Prints error message when no tasks found.
     */
    public String showLoadingError() {
        return "No tasks were found! Making you a new list real quick xd";
    }

    /**
     * Prints message after task marked.
     *
     * @param task task that was marked.
     */
    public String showMark(Task task) {
        return "Good job on completing your task! \n" + task;
    }

    /**
     * Prints message after task unmarked.
     *
     * @param task task that was unmarked.
     */
    public String showUnmark(Task task) {
        return "Okay, I've marked this as not done yet: \n" + task;
    }

    /**
     * Prints message after task added.
     *
     * @param task task that was added.
     * @param size number of tasks in list.
     */
    public String showAdd(Task task, int size) {
        return "I gotchu. I've added this task: \n" + task
                + String.format("\nNow you have %d task(s) in the list.", size);
    }

    /**
     * Prints message after task deleted.
     *
     * @param task task that was deleted.
     * @param size number of tasks in list.
     */
    public String showDelete(Task task, int size) {
        return "Aights mate. I've killed this task:\n" + task
                + String.format("     Now you have %d task(s) left.", size);
    }

    /**
     * Prints list of all tasks.
     *
     * @param list List containing tasks
     */
    public String showList(List list) {
        if (list.isEmpty()) {
            return "Hohoho, you have no tasks.";
        } else {
            String ls = "Here are all your tasks:\n";

            // Iterate through ArrayList of tasks and enumerate them
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                ls += index + ". " + list.get(i) +"\n";
            }
            return ls;
        }
    }

    /**
     * Prints error message.
     *
     * @param msg Error message.
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * Prints help message such as command syntax.
     *
     */
    public String showHelp() {
        return "Here are some commands you can try:\n"
                + "  list -- show full task list\n"
                + "  mark <int> -- mark task as completed\n"
                + "  unmark <int> -- mark task as not done yet\n"
                + "  find xx xx -- filter specific task descriptions\n"
                + "  todo <item> -- create new todo\n"
                + "  deadline <item> /by <yyyy-mm-dd> -- create new deadline task\n"
                + "  event <item> /from <yyyy-mm-dd> /to <yyyy-mm-dd> -- create new event task\n"
                + "  help -- bruh need say more?";
    }
}
