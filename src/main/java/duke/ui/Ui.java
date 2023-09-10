package duke.ui;

import duke.TaskList;
import duke.task.Task;

/**
 * The Ui class is responsible for user interaction and displaying messages to the user.
 */
public class Ui{
    static String hello = "Hello muggle! I'm " + "Harry Potter!" + "\n" + "Introducing the Wizarding World Organizer: Your Trusted Guide" +
            " to Efficient & Effective Magical Planning!";
    static String bye = "\t" + "Expelliarmus! Hope to see you again muggle! :D";

    /**
     * Displays a welcome message to the user.
     */
    public static String printHello() {
        return hello;
    }

    /**
     * Displays a goodbye essage to the user.
     */
    public static String printBye() {
        return bye;
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param done The task that was marked as done.
     */
    public static String printDone(Task done) {
        return "\t" + "Nice! I've marked this task " +
                "as done:" + "\n" +
                "\t " + done.taskString();
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param notDone The task that was marked as not done.
     */
    public static String printNotDone(Task notDone) {
        return "\t" + "OK, I've marked this task " +
                "as not done yet:" + "\n" + "\t" + " " +
                notDone.taskString();
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param toBeDeleted The task that was deleted.
     * @param tasks       The task list.
     */
    public static String printDelete(Task toBeDeleted, TaskList tasks) {
        return "\tNoted. I've removed this task:\n\t " + toBeDeleted.taskString()
                + "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints a message when a task is added to the list.
     *
     * @param task  The task that was added.
     * @param tasks The task list.
     */
    public static String printAddTask(Task task, TaskList tasks) {
        int len = tasks.getSize();
        String output = "\tGot it. I've added this task:\n\t\t"
                + task.taskString();
        String listLength = len == 1 ? "Now you have " + len + " task in the list." :
                "Now you have " + len + " tasks in the list.";
        return output + "\n\t" + listLength;
    }


    /**
     * Prints a custom message for an exception.
     *
     * @param message The custom exception message.
     */
    public static String printException(String message) {
        return message;
    }

    /**
     * Prints a default exception message for EventDateTime Exception.
     */
    public static String printException() {
        return"Accio error! I don't understand what the input means D:" +
                " Please input a valid date in the format yyyy-MM-dd HHmm " +
                "(the time in the 24-hour format).";
    }

    /**
     * Lists the tasks in the task list.
     * @param tasks The task list to be printed.
     */
    public static String listTasks(TaskList tasks) {
        String answer = "\tHere are the tasks in your list:";
        int i = 1;
        for (int j = 0; j < tasks.getSize(); j++) {
            answer += "\n\t" + i + "." + tasks.getTask(j).taskString();
            i++;
        }
        return answer;
    }

    /**
     * Lists the tasks in the task list
     * containing tasks with matching keywords
     * @param tasks The task list to be printed.
     */
    public static String printMatchingTasks(TaskList tasks){
        String answer = "\tHere are the tasks in your list:";
        int i = 1;
        for (int j = 0; j < tasks.getSize(); j++) {
            answer += "\n\t" + i + "." + tasks.getTask(j).taskString();
            i++;
        }
        return answer;
    }

}
