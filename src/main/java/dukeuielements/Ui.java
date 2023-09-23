
package dukeuielements;

import java.util.ListIterator;

import duke.TaskList;
import task.Task;

/**
 * The type Ui.
 */
public class Ui {
    private static final String INDENT = "     ";

    /**
     * Print list string.
     *
     * @param ls the ls
     * @return the string
     */
    public static String printList(ListIterator<Task> ls) {
        int count = 0;
        String listString = "Entries on memory..." + "\n";
        while (ls.hasNext()) {
            count++;
            listString = listString + count + "." + ls.next().toString() + "\n";
        }
        return listString;
    }

    /**
     * Print number of entries string.
     *
     * @return the string
     */
    public static String printNumberOfEntries(Task task, TaskList taskList) {
        String printTask = "Added 1 new task" + "\n" + INDENT + task.toString() + "\n";
        return printTask + "\n" + "Now you have " + taskList.getTaskSize() + " tasks in your task scheduler...";
    }

    /**
     * Invalid date time entry string.
     *
     * @return the string
     */
    public static String invalidDateTimeEntry() {
        return "Invalid date/time entry. Please give in yyyy-mm-dd hh:mm format...";
    }
    /**
     * Invalid deadline format string.
     *
     * @return Message indicating wrong format.
     */
    public static String invalidDeadlineFormat() {
        return "Invalid format for deadline. Please give in deadline description/yyyy-mm-dd hh:mm format...";
    }

    /**
     * Invalid event format string.
     *
     * @return Message indicating wrong format.
     */
    public static String invalidEventFormat() {
        return "Invalid format for deadline. "
                + "Please give in event description1/yyyy-mm-dd hh:mm/yyyy-mm-dd hh:mm format...";
    }

    /**
     * Empty list string.
     *
     * @return the string
     */
    public static String emptyList() {
        return "Duke could not find your query! Please try again...";
    }

    /**
     * Delete task print string.
     *
     * @param itemRemoved the item removed
     * @return the string
     */
    public static String deleteTaskPrint(Task itemRemoved, TaskList taskList) {
        String removedTask = "This task was removed..." + "\n" + itemRemoved + "\n";
        removedTask = removedTask + "Now you have " + taskList.getTaskSize()
                + " tasks in your task scheduler...";
        return removedTask;
    }

    /**
     * Find task print string.
     *
     * @param ls the ls
     * @return the string
     */
    public static String findTaskPrint(ListIterator<Task> ls) {
        String findString = "Tasks that may match your search result..." + "\n";
        findString += printList(ls);
        return findString;
    }

    /**
     * Mark task
     *
     * @param task the task
     * @return the string representation of marked task
     */
    public static String markStringReturn(Task task) {
        if (!task.getTaskStatus()) {
            task.setFalseStatus();
            return "Nice! Task completed successfully!" + "\n" + INDENT + task;
        } else {
            return "Task already checked. Please try again..." + "\n" + INDENT + task;
        }
    }

    /**
     * Unmark task
     *
     * @param task the task
     * @return the string representation of unmarked task
     */
    public static String unmarkStringReturn(Task task) {
        if (!task.getTaskStatus()) {
            return "Task already unmarked! Please try again..." + "\n" + INDENT + task;
        } else {
            task.setFalseStatus();
            return "Sure! Task status unchecked!" + "\n" + INDENT + task;
        }
    }
    /**
     * End duke msg string.
     *
     * @return the string
     */
    public static String saveDukeMsg() {
        return "Tasks have been saved to disk!";
    }
}
