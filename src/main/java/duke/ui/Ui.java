package duke.ui;

import duke.TaskList;
import duke.task.Task;

/**
 * The Ui class is responsible for user interaction and displaying messages to the user.
 */
public class Ui{
    static String hello = "Hello muggle! I'm " + "Harry Potter!" + "\n" + "Introducing the Wizarding World Organizer: Your Trusted Guide" +
            " to Efficient & Effective Magical Planning! Type 'help' to find out more about my capabilities!";
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
    public static String printAllDone() {
        return "\t" + "Nice! I've marked all tasks " +
                "as done!";
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

    public static String printAllNotDone() {
        return "\t" + "OK, I've marked all tasks " +
                "as not done yet.";
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
    public static String printAllDeleted(TaskList tasks) {
        return "\tNoted. I've removed all tasks from the list.";
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

    public static String printHelp() {
        return "No worries, muggle! Harry Potter is here to help :D Accio Help Guide! \n"
                + "Here are the commands for use: \n"
                + "1. help: shows this list of commands\n"
                + "2. mark<index>: marks task at <index> in the list as done \n"
                + "3. mark all: marks all tasks in the list as done \n"
                + "4. unmark<index>: marks task at <index> in the list as not done \n"
                + "5. unmark all: marks all tasks in the list as not done \n"
                + "6. delete<index>: deletes task at <index> in the list\n"
                + "7. delete all: deletes all tasks in the list\n"
                + "8. list: shows the existing list of tasks\n"
                + "9. find <keyword>: returns a new list of tasks containing the matching keyword\n"
                + "10. todo <task>: specify the task to be added in the list with no start or end date\n"
                + "11. deadline <task> /by <yyyy-MM-dd HHmm>: specify the task to be added with a specific deadline\n"
                + "12. event <task> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>: specify the event to be added with both start & end dates\n"
                + "13. bye: displays a goodbye message";

    }

    /**
     * Lists the tasks in the task list.
     * @param tasks The task list to be printed.
     */
    public static String listTasks(TaskList tasks) {
        String answer = "\tHere are the tasks in your list:";
        int i = 1;
        for (int j = 0; j < tasks.getSize(); j++) {
            answer += "\n\t" + i + ". " + tasks.getTask(j).taskString();
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
            answer += "\n\t" + i + ". " + tasks.getTask(j).taskString();
            i++;
        }
        return answer;
    }

}
