package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The duke.ui.Ui class is responsible for managing user interactions and displaying messages.
 */
public class Ui {

    /**
     * Displays a reply after adding a task.
     *
     * @param str    The user input string.
     * @param tasks  The list of tasks.
     * @return       The reply message.
     */
    public static String addTaskReply(String str, TaskList tasks) {
        return String.format("Ding: What does '%s' mean? I'll just add it anyway.\n " +
                "You have like %d tasks now", str, tasks.getSize());
    }

    /**
     * Displays a reply after marking a task as done.
     *
     * @param taskIndex The index of the task.
     * @param tasks     The list of tasks.
     * @return          The reply message.
     */
    public static String markTaskReply(int taskIndex, TaskList tasks) {
        return String.format("Ding: Okay, I marked this task as done, " +
                    "but I have no idea what that means:\n %s", tasks.getTask(taskIndex));
    }

    /**
     * Displays a reply after marking a task as undone.
     *
     * @param taskIndex The index of the task.
     * @param tasks     The list of tasks.
     * @return          The reply message.
     */
    public static String unmarkTaskReply(int taskIndex, TaskList tasks) {
        return String.format("Ding: Okay, I marked this task as undone, " +
                "but I have no idea what that means:\n %s", tasks.getTask(taskIndex));
    }

    /**
     * Displays a reply after deleting a task.
     *
     * @param task  The task that was deleted.
     * @param tasks The list of tasks.
     * @return      The reply message.
     */
    public static String deleteTaskReply(Task task, TaskList tasks) {
        return String.format("Ding: Okay, I've forgotten this task, \n"
                + "so don't expect me to remember it:\n %s\n"
                + "Ding: Right so now you have like %d tasks left", task, tasks.getSize());
    }


    /**
     * Displays a reply when exiting the chatbot.
     *
     * @return The exit reply message.
     */
    public static String exitReply() {
        return "____________________________________________________________\n" +
                "Ding: Bye. Hopefully I get to see you again soon!\n" +
                "____________________________________________________________";
    }

    /**
     * Displays an introductory message when starting the chatbot.
     *
     * @return The introductory reply message.
     */
    public static String introReply() {
        return "____________________________________________________________\n"
                + "Hello! I'm Ding!\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     * @return      The reply message containing the task list.
     */
    public static String printListReply(TaskList tasks) {
        return String.format("Ding: These are your tasks... " +
                "If I remember correctly:\n%s\n", tasks);
    }

    /**
     * Displays a reply for finding tasks.
     *
     * @param str   The user input containing the keyword.
     * @param tasks The list of tasks to display.
     * @return      The reply message containing the found tasks.
     */
    public static String findTaskReply(String str, TaskList tasks) {
        return String.format("I will help you %s\n", str) +
                printListReply(tasks);
    }

    /**
     * Displays a reply for an illegal argument exception.
     *
     * @return The reply message for an illegal argument exception.
     */
    public static String illegalArgumentExceptionReply() {
        return "Ding: I seriously have no idea what I need to do here\n"
                + "Ding: No way you forgot to even input a proper command...\n"
                + "Ding: Available commands are 'todo', 'deadline', 'event', \n"
                + "'list', 'mark', 'unmark', 'delete', 'find', 'bye'\n";
    }

    /**
     * Displays a reply for an invalid task description exception.
     *
     * @return The reply message for an invalid task description exception.
     */
    public static String invalidDescriptionExceptionReply() {
        return "Ding: I may be forgetful but you're so bad you even forgot the task description...\n"
                + "Ding: For ToDos, input 'todo (task)'\n"
                + "Ding: For Deadlines, input 'deadline (task) /by (date or time)\n"
                + "Ding: For Events, input 'event (task) /from (date or time) /to (date or time)";
    }

    /**
     * Displays a reply for an invalid task index exception.
     *
     * @param tasks The list of tasks.
     * @return      The reply message for an invalid task index exception.
     */
    public static String invalidTaskIndexExceptionReply(TaskList tasks) {
        String startOfReply = "Ding: Oh wait it's not lost, \n"
                + "the task number you gave just doesn't exist in your list...";
        if (tasks.getSize() > 0) {
            startOfReply += String.format("Ding: Please input a task number from 1 to %d", tasks.getSize());
        } else {
            startOfReply += "Ding: You have nothing in your task list... " +
                    "What are you even trying to get me to do?";
        }
        return startOfReply;
    }

    /**
     * Displays a reply for a missing task index exception.
     *
     * @return The reply message for a missing task index exception.
     */
    public static String missingTaskIndexExceptionReply() {
        return "Ding: I don't quite understand what you want to do...\n"
                + "Ding: Please input '(command) (task number)'...";
    }

    /**
     * Displays a reply for an invalid date-time format exception.
     *
     * @return The reply message for an invalid date-time format exception.
     */
    public static String invalidDateTimeExceptionReply() {
        return "Ding: I already told you... \n"
                + "please enter the timestamps in this format 'YYYY-MM-DD HH:mm'";
    }

    /**
     * Displays a reply for an invalid keyword exception.
     *
     * @return The reply message for an invalid keyword exception.
     */
    public static String invalidKeywordExceptionReply() {
        return "Ding: Please use the format 'find (keyword)' so I that can help you look around";
    }
}
