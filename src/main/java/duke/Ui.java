package duke;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;

/**
 * UI represents the User Interface of the chatbot and deals with interactions with the user.
 */
public class Ui {
    private String input;

    /**
     * The constructor of Ui.
     */
    public Ui() { }

    /**
     * To read the user's input into the chatbot.
     *
     * @param input The input of the user.
     */
    public void readCommand(String input) {
        this.input = input;
    }

    /**
     * A getter function to get the user's input to the chatbot.
     *
     * @return The user's input to the chatbot.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Prints out the welcome message of the chatbot.
     *
     * @return The String representation of the message to be shown when the user
     *     starts the chatbot.
     */
    public String printWelcome() {
        String msg = "\nHi Traveller! I'm Paimon!"
                + "\nWhat can I do for you?";
        return msg;
    }

    /**
     *  Prints out the goodbye message when the user exits the chatbot.
     *
     * @return The String representation of the message to be shown when the user
     *     exits the chatbot.
     */
    public String printGoodbye() {
        return "Bye Bye Traveller! See you soon :D";
    }

    /**
     * Prints out the message to see the tasks in the user's task list.
     *
     * @param tasks The user's list of tasks.
     * @return The String representation of the message to be shown to list out
     *     the tasks in the task list.
     */
    public String printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "There are currently no tasks in your list.";
        } else {
            int i = 1;
            String listMessage = "Here are the tasks in your list: \n";
            for (Task t : tasks) {
                listMessage += i + ". " + t.toString() + "\n";
                i++;
            }
            return listMessage;
        }
    }

    /**
     * Prints out the message when a task that is marked as done.
     *
     * @param task The marked task to be printed out.
     * @return The String representation of the message to be shown when
     *     a task is marked as done.
     */
    public String printMarkedTask(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Prints out the message when a task is marked as undone.
     *
     * @param task The unmarked task to be printed out.
     * @return The String representation of the message to be shown when
     *     a task is marked as undone.
     */
    public String printUnmarkedTask(Task task) {
        return "Nice! I've un-marked this task: \n" + task.toString();
    }

    /**
     * Prints out the message when a task is added.
     * @param taskList The user's task list.
     * @param task The task to be added into the task list.
     * @return The String representation of the message to be shown when
     *     the task is added.
     */
    public String printAddTask(TaskList taskList, Task task) {
        return "Got it. Task successfully added: \n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints out the message when a task is deleted.
     *
     * @param taskList The user's task list.
     * @param deletedTask The task that was deleted by the user.
     * @return The String representation of the message to be shown when
     *     a task is deleted.
     */
    public String printDeleteTask(TaskList taskList, Task deletedTask) {
        return "Noted. I've removed this task: \n"
                + deletedTask.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints out the message when the tasks with the specific keyword is
     * requested to be found.
     *
     * @param matchingTasks The list of matching tasks to the specific keyword.
     * @param keyword The keyword the user wants to search for in the tasks.
     * @return The String representation of the message to be shown when a keyword is
     *     requested to be found.
     */
    public String printFindTask(ArrayList<Task> matchingTasks, String keyword) {
        if (matchingTasks.isEmpty()) {
            return "There are no tasks in your list yet :O";
        } else {
            String msg = "Here are the tasks with " + "\"" + keyword + "\"" + ":\n";
            int i = 1;
            for (Task t : matchingTasks) {
                msg += i + ". " + t;
                i++;
            }
            return msg;
        }
    }

    /**
     * Prints out the message to see the user's sorted list of deadlines.
     *
     * @param sortedDeadlines The sorted ArrayList of Deadline objects
     *     according to their dates and times.
     * @return The String representation of the message to be shown when the user
     *     requests to see their sorted deadlines.
     */
    public String printSortedDeadlines(ArrayList<Deadline> sortedDeadlines) {
        if (sortedDeadlines.isEmpty()) {
            return "There are no deadlines in your list yet :O";
        } else {
            String msg = "Here are your sorted deadlines in ascending order: \n";
            int i = 1;
            for (Deadline deadline : sortedDeadlines) {
                msg += i + ". " + deadline.toString() + "\n";
                i++;
            }
            return msg;
        }
    }

    /**
     * Prints out the message to see the user's sorted list of events.
     *
     * @param sortedEvents The sorted ArrayList of Event objects.
     * @return The String representation of the message to be shown when the user
     *     requests to see their sorted events.
     */
    public String printSortedEvents(ArrayList<Event> sortedEvents) {
        if (sortedEvents.isEmpty()) {
            return "There are no events in your list yet :O";
        } else {
            String msg = "Here are your sorted events in ascending order: \n";
            int i = 1;
            for (Event event : sortedEvents) {
                msg += i + ". " + event.toString() + "\n";
                i++;
            }
            return msg;
        }
    }
}
