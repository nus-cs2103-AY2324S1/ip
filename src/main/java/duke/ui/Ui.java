package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The user interface for the Chat Bot.
 *
 * @author Armando Jovan Kusuma
 */
public class Ui {
    private static final String MESSAGE = " Hello! I'm ChatBot\n"
            + " What can I do for you?" + "\n\n"
            + "Enter help to view the list of commands";

    /**
     * Greets the user at the start.
     *
     * @return a greeting to the user.
     */
    public String greet() {
        return MESSAGE;
    }

    /**
     * Prints a message if a task is added to the list.
     *
     * @param input the task added to the list of tasks.
     * @param count the number of tasks in the list.
     * @return a string indicating a task has been added.
     */
    public String taskPrint(Task input, int count) {
        return "Got it. I've added this task" + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list.";
    }

    /**
     * Prints a message if a task is deleted from the list.
     *
     * @param input the task deleted from the list of tasks.
     * @param count the number of tasks in the list.
     * @return a string indicating a task has been deleted.
     */
    public String deletePrint(Task input, int count) {
        return "Noted. I've removed this task: " + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list.";
    }

    /**
     * Prints a message if a task is marked as done.
     *
     * @param task the task to be marked as done.
     * @return a string indicating a task has been marked.
     */
    public String markDonePrint(Task task) {
        return "Nice! I've marked this task as done: " + "\n" + task;
    }

    /**
     * Prints a message if a task is unmarked as done.
     *
     * @param task the task to be unmarked as done.
     * @return a string indicating a task has been unmarked.
     */
    public String unmarkDonePrint(Task task) {
        return "OK, I've marked this task as not done yet: " + "\n" + task;
    }

    /**
     * Prints a message if a task is marked as done.
     *
     * @return a farewell message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasksList the list of tasks.
     * @param count     the amount of tasks in the tasks list.
     * @return the list of tasks.
     */
    public String listOfTasks(TaskList tasksList, int count) {
        if (tasksList.getTaskCount() == 0) {
            return "You currently have no tasks!";
        }
        String output = "";
        for (int i = 0; i < count; i++) {
            output += (i + 1) + ". " + tasksList.getTask(i) + "\n";
        }
        return output;
    }

    /**
     * Prints the error message from Duke Exception.
     *
     * @param e The DukeException error to be printed.
     * @return an error message depending on where it is caught.
     */
    public String errorPrint(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints the tasks found when filtered using the specific keyword.
     *
     * @param findTasks the list of tasks.
     * @return the tasks found with the specific keyword.
     */
    public String printFoundTasks(TaskList findTasks) {
        if (findTasks.getTaskCount() == 0) {
            return "Sorry, there are no tasks with that keyword!";
        }
        String output = "";
        output += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < findTasks.getTaskCount(); i++) {
            output += (i + 1) + ". " + findTasks.getTask(i) + "\n";
        }
        return output;
    }

    public String helpPrint() {
        String todo = "* T / Todo <TASK_NAME> - To add a new Todo Task";
        String deadline = "* D / Deadline <TASK_NAME> " +
                "/by <YYYY-MM-DD - To add a Deadline Task";
        String event = "* E / Event <TASK_NAME> " +
                "/from <YYYY-MM-DD> " +
                "/to <YYYY-MM-DD> - To add a Event Task";
        String list = "* L / List - To show the list of tasks";
        String mark = "* M / Mark <INDEX> - To mark a task as done";
        String unmark = "* Um / Unmark <INDEX> - To un-mark a task";
        String delete = "* Del / Delete <INDEX> - To delete a specific task";
        String help = "* H / Help - to open up the commands";
        String find = "* F / Find <KEYWORD> - To find a task with the specific keyword";
        String bye = "* Bye - To exit the chat-bot";

        String message = "Hi! I'm here to help!" + "\n\n"
                + "Here are the list of commands you can do: " + "\n\n"
                + help + "\n\n" + todo + "\n\n" + deadline + "\n\n"
                + event + "\n\n" + list + "\n\n" + mark + "\n \n"
                + unmark + "\n\n" + find  + "\n\n" + delete + "\n\n"
                + bye + "\n\n";

        return message;
    }
}
