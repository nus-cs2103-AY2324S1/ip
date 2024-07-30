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
     * Retrieves and returns a welcome message to be displayed to the user.
     * @return A welcome message to be shown to the user.
     */
    public static String printHello() {
        return hello;
    }

    /**
     * Retrieves and returns a goodbye message to be displayed to the user.
     * @return A goodbye message to be shown to the user.
     */
    public static String printBye() {
        return bye;
    }

    /**
     * Generates a message to indicate that a task has been marked as done.
     *
     * @param done The task that was marked as done.
     * @return A message indicating the task has been marked as done.
     */
    public static String printDone(Task done) {
        return "\t" + "Nice! I've marked this task " +
                "as done:" + "\n" +
                "\t " + done.taskString();
    }
    /**
     * Generates a message to indicate that a task has been updated.
     *
     * @param updated The task that was updated.
     * @return A message indicating the task has been updated.
     */
    public static String printUpdatedTask(Task updated) {
        return "\t" + "Nice! I've updated this task: " +
                "\n" +
                "\t " + updated.taskString();
    }

    /**
     * Generates a message to indicate that all tasks have been marked as done.
     *
     * @return A message indicating that all tasks have been marked as done
     * and the updated task list.
     */
    public static String printAllDone(TaskList tasks) {
        String answer = "\t" + "Nice! I've marked all tasks " +
                "as done:";
        int i = 1;
        for (int j = 0; j < tasks.getSize(); j++) {
            answer += "\n\t" + i + ". " + tasks.getTask(j).taskString();
            i++;
        }
        return answer;
    }


    /**
     * Generates a message to indicate that a task has been marked as not done.
     *
     * @param notDone The task that was marked as not done.
     * @return A message indicating the task has been marked as not done.
     */
    public static String printNotDone(Task notDone) {
        return "\t" + "OK, I've marked this task " +
                "as not done yet:" + "\n" + "\t" + " " +
                notDone.taskString();
    }

    /**
     * Generates a message to indicate that all tasks have been marked as not done.
     * @return A message indicating that all tasks have been marked as not done
     * and the updated task list.
     */
    public static String printAllNotDone(TaskList tasks) {
        String answer = "\t" + "OK, I've marked all tasks " +
                "as not done yet:";
        int i = 1;
        for (int j = 0; j < tasks.getSize(); j++) {
            answer += "\n\t" + i + ". " + tasks.getTask(j).taskString();
            i++;
        }
        return answer;
    }

    /**
     * Generates a message when a task is deleted from the list.
     *
     * @param toBeDeleted The task that was deleted.
     * @param tasks       The task list from which the task was deleted.
     * @return A message indicating the task has been removed and the updated task count.
     */
    public static String printDelete(Task toBeDeleted, TaskList tasks) {
        return "\tNoted. I've removed this task:\n\t " + toBeDeleted.taskString()
                + "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Generates a message when all tasks are deleted from the list.
     *
     * @param tasks The task list from which all tasks were deleted.
     * @return A message indicating that all tasks have been removed from the list.
     */
    public static String printAllDeleted(TaskList tasks) {
        return "\tNoted. I've removed all tasks.\n\tNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints a message when a task is added to the list.
     *
     * @param task  The task that was added.
     * @param tasks The task list.
     * @return A message indicating the added task and the updated task count.
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
     * @return The message indicating the exception occurred.
     */
    public static String printException(String message) {
        return message;
    }


    /**
     * Generates a default exception message for an EventDateTime exception.
     * @return A message indicating the error on DateTime input and the expected date and time format.
     */
    public static String printException() {
        return"Accio error! I don't understand what the input means D:" +
                " Please input a valid date in the format yyyy-MM-dd HHmm " +
                "(the time in the 24-hour format).";
    }

    /**
     * Generates a help message listing available commands.
     * @return A message listing the available commands and their descriptions.
     */
    public static String printHelp() {
        return "No worries, muggle! Harry Potter is here to help :D Accio Help Guide! \n"
                + "Here are the commands for use: \n"
                + "1. help: shows this list of commands\n"
                + "2. todo <task>: specify the task to be added in the list with no start or end date\n"
                + "3. deadline <task> /by <yyyy-MM-dd HHmm>: specify the task to be added with a specific deadline\n"
                + "4. event <task> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>: specify the event to be added with both start & end dates\n"
                + "2. mark<index>: marks task at <index> in the list as done \n"
                + "4. unmark<index>: marks task at <index> in the list as not done \n"
                + "6. delete<index>: deletes task at <index> in the list\n"
                + "7. mark all/ unmark all/ delete all: modifies/removes all tasks in the list accordingly\n"
                + "8. update <index> /description <new description>: changes description of the task at specified index \n"
                + "8. update <index> /event <start/end> date <yyyy-MM-dd HHmm>: changes start/end date of the Event task at specified index \n"
                + "8. update <index> /description <new description>: changes description of the task at specified index \n"
                + "8. update <index> /deadline <yyyy-MM-dd HHmm>: changes deadline of the Deadline task at specified index \n"
                + "9. list: shows the existing list of tasks\n"
                + "10. find <keyword>: returns a new list of tasks containing the matching keyword in the task description\n"
                + "11. bye: displays a goodbye message. ";
    }

    /**
     * Generates a message listing the tasks in the given task list.
     * @param tasks The task list.
     * @return A message containing a list of tasks from the task list.
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
     * Generates a message listing all tasks that match the input keyword in the given task list.
     * @param tasks The task list.
     * @return A message containing a list of all tasks that match the input keyword.
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
