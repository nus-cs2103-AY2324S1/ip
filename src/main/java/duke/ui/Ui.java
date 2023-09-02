package duke.ui;

import duke.commands.Command;
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
     */
    public static void addTaskReply(String str, TaskList tasks) {
        System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n " +
                "You have like %d tasks now", str, tasks.getSize()));
    }

    /**
     * Displays a reply after marking or unmarking a task.
     *
     * @param taskIndex The index of the task.
     * @param tasks     The list of tasks.
     * @param command   The command (MARK or UNMARK).
     */
    public static void markTaskReply(int taskIndex, TaskList tasks, Command command) {
        switch (command) {
        case MARK:
            System.out.println(String.format("Ding: Okay, I marked this task as done, " +
                    "but I have no idea what that means:\n %s", tasks.getTask(taskIndex)));
            break;
        case UNMARK:
            System.out.println(String.format("Ding: Okay, I marked this task as undone, " +
                    "but I have no idea what that means:\n %s", tasks.getTask(taskIndex)));
        }
    }

    /**
     * Displays a reply after deleting a task.
     *
     * @param task  The task that was deleted.
     * @param tasks The list of tasks.
     */
    public static void deleteTaskReply(Task task, TaskList tasks) {
        System.out.println(String.format("Ding: Okay, I've forgotten this task, " +
                "so don't expect me to remember it:\n %s", task));
        System.out.println(String.format("Ding: Right so now you have like %d tasks left", tasks.getSize()));
    }

    /**
     * Displays a horizontal line as a separator.
     */
    public static void horizontalLineReply() {
        System.out.println("\n____________________________________________________________\n");
    }

    /**
     * Displays a reply when exiting the chatbot.
     */
    public static void exitReply() {
        System.out.println(
                "____________________________________________________________\n" +
                "Ding: Bye. Hopefully I get to see you again soon!\n" +
                "____________________________________________________________");
    }

    /**
     * Displays an introductory message when starting the chatbot.
     */
    public static void introReply() {
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm Ding!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public static void printListReply(TaskList tasks) {
        System.out.println(String.format("Ding: These are your tasks... " +
                "If I remember correctly:\n%s", tasks));
    }

    /**
     * Displays a reply for finding of Tasks.
     * @param str The user input containing the keyword.
     * @param tasks The list of tasks to display.
     */
    public static void findTaskReply(String str, TaskList tasks) {
        System.out.println(String.format("I will help you %s\n", str));
        printListReply(tasks);
    }

    /**
     * Displays a reply for an illegal argument exception.
     */
    public static void illegalArgumentExceptionReply() {
        System.out.println("Ding: I seriously have no idea what I need to do here");
        System.out.println("Ding: No way you forgot to even input a proper command...");
        System.out.println("Ding: Available commands are 'todo', 'deadline', 'event', " +
                "'list', 'mark', 'unmark', 'delete', 'bye'");
    }

    /**
     * Displays a reply for an invalid task description exception.
     */
    public static void invalidDescriptionExceptionReply() {
        System.out.println("Ding: I may be forgetful but you're so bad you even forgot the task description...");
        System.out.println("Ding: For ToDos, input 'todo (task)'");
        System.out.println("Ding: For Deadlines, input 'deadline (task) /by (date or time)");
        System.out.println("Ding: For Events, input 'event (task) /from (date or time) /to (date or time)");
    }

    /**
     * Displays a reply for an invalid task index exception.
     *
     * @param tasks The list of tasks.
     */
    public static void invalidTaskIndexExceptionReply(TaskList tasks) {
        System.out.println("Ding: Oh wait it's not lost, " +
                "the task number you gave just doesn't exist in your list...");
        if (tasks.getSize() > 0) {
            System.out.println(String.format("Ding: Please input a task number from 1 to %d", tasks.getSize()));
        } else {
            System.out.println("Ding: You have nothing in your task list... " +
                    "What are you even trying to get me to do?");
        }
    }

    /**
     * Displays a reply for a missing task index exception.
     */
    public static void missingTaskIndexExceptionReply() {
        System.out.println("Ding: I don't quite understand what you want to do...");
        System.out.println("Ding: Please input '(command) (task number)'...");
    }

    /**
     * Displays a reply for an invalid date-time format exception.
     */
    public static void invalidDateTimeExceptionReply() {
        System.out.println("Ding: I already told you... " +
                "please enter the timestamps in this format 'YYYY-MM-DD HH:mm'");
    }

    /**
     * Displays a reply for an invalid keyword exception.
     */
    public static void invalidKeywordExceptionReply() {
        System.out.println("Ding: Please use the format 'find (keyword)' so I that can help you look around");
    }
}
