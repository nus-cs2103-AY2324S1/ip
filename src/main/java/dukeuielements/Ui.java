
package dukeuielements;

import java.util.ListIterator;

import duke.TaskList;
import task.Task;

/**
 * The type Ui.
 */
public class Ui {
    private static String indent = "     ";

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
    public static String printNumberOfEntries() {
        return Ui.indent + "Now you have " + TaskList.getTaskSize() + " tasks in your task scheduler...";
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
    public static String deleteTaskPrint(Task itemRemoved) {
        String removedTask = indent + "This task was removed..." + "\n" + itemRemoved + "\n";
        removedTask = indent + "Now you have " + TaskList.getTaskSize() + " tasks in your task scheduler...";
        return removedTask;
    }

    /**
     * Find task print string.
     *
     * @param ls the ls
     * @return the string
     */
    public static String findTaskPrint(ListIterator<Task> ls) {
        String findString = indent + "Tasks that may match your search result..." + "\n";
        findString += printList(ls);
        return findString;
    }

    /**
     * Misc msg print string.
     *
     * @param msg the msg
     * @return the string
     */
    public static String miscMsgPrint(String msg) {
        return msg;
    }

    /**
     * End duke msg string.
     *
     * @return the string
     */
    public static String endDukeMsg() {
        return "bye";
    }
}