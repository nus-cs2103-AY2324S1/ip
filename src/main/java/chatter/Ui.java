package chatter;

import java.util.Scanner;

import chatter.task.Task;

/**
 * Represents a UI class to handle user interactions.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Ui {
    private static final String DIVIDER = "-----------------------";
    /** Scanner object that takes in user inputs. */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Prints a welcome message when user starts the chatbot.
     *
     * @return Welcome message for the user.
     */
    public String showWelcome() {
        return "Hello! I'm chatter.Chatter!\n" + "How can i help you today?\n";
    }

    /**
     * Prints a divider.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a welcome message when user exits the chatbot.
     *
     * @return Exit message for the user.
     */
    public String showExit() {
        SCANNER.close();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a welcome message when user exits the chatbot.
     */
    public void showLoadingError() {
        System.out.println(DIVIDER + "\nError reading data from file!\n" + DIVIDER);
    }

    /**
     * Reads the user input and returns a string of the raw user input.
     *
     * @return A string of the raw user input.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Prints task string when task is added to the list.
     *
     * @param task chatter.task.Task object being added to the list.
     * @param numOfTasks Number of tasks in the list after adding the new task.
     * @return Chatter's response to user adding task.
     */
    public String showAddedTask(Task task, int numOfTasks) {
        String output = "Got it. I have added this task to do:\n";
        output += "  " + task.toString() + "\n";
        output += "You now have " + numOfTasks + " task(s) in the list.\n";
        return output;
    }

    /**
     * Prints completed task string.
     *
     * @param task Task that is being marked done.
     * @return Task being marked as done message.
     */
    public String showMarkedTask(Task task) {
        String output = "Good job! I've marked this task as completed:\n";
        output += "  " + task;
        return output;
    }

    /**
     * Prints unmarked task string.
     *
     * @param task chatter.task.Task that is being unmarked.
     * @return Task being unmarked message.
     */
    public String showUnmarkedTask(Task task) {
        String output = "OK! I've marked this task as not done yet:\n";
        output += "  " + task;
        return output;
    }

    /**
     * Prints out deleted task message.
     *
     * @param task Task to be deleted.
     * @param numOfTasks Number of tasks left in the list after deletion.
     * @return String message indicating that task has been deleted.
     */
    public String showDeletedTask(Task task, int numOfTasks) {
        String output = "Noted! I have removed this task:\n";
        output += "  " + task + "\n";
        output += "You now have " + numOfTasks + " task(s) in the list.\n";
        return output;
    }

    /**
     * Prints out list of tasks to display to the user.
     *
     * @param tasks List of tasks.
     * @param numOfTasks Number of tasks in the list.
     * @return String showing the tasks in the list.
     */
    public String showListTasks(TaskList tasks, int numOfTasks) {
        String output = "These are all the task(s) in your list:\n";
        for (int i = 0; i < numOfTasks; i++) {
            output += "  " + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Prints out list of tasks with keyword to display to the user.
     *
     * @param tasks List of tasks.
     * @param numOfTasks Number of tasks in the list.
     * @param keyword Keyword that the user is finding for.
     * @return Tasks that contain the keyword.
     */
    public String showFoundTasks(TaskList tasks, int numOfTasks, String keyword) {
        int count = 1;
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < numOfTasks; i++) {
            if (tasks.getTask(i).toString().contains(keyword)) {
                output += "  " + count + "." + tasks.getTask(i).toString() + "\n";
                count++;
            }
        }
        return output;
    }

    /**
     * Returns all the commands that can be used by the user.
     *
     * @return String of commands that can be used by the user.
     */
    public String showCommands() {
        return "Available commands:\n"
                + "todo <task>            " + "\tCreate a todo task\n"
                + "deadline <task>        " + "\tCreate a deadline event\n"
                + "\t/by <deadline>\n"
                + "event <task>           " + "\tCreate an event\n"
                + "\t/from <date/time>\n"
                + "\t/to <date/time>\n"
                + "list                     " + "\tList all your events\n"
                + "done <index>       " + "\tMark done for task at index\n"
                + "delete <index>    " + "\tDelete task at index\n"
                + "find <keyword>    " + "\tFind tasks with keyword\n"
                + "help                   " + "\tShow all available commands\n"
                + "bye                    " + "\tQuit bot";
    }
}
