package dukeuielements;
import duke.TaskList;
import task.Task;
import java.util.ListIterator;

public class Ui {
    private static String indent = "     ";

    public static String printList(ListIterator<Task> ls) {
        int count = 0;
        String listString = "Entries on memory..." + "\n";
        while (ls.hasNext()) {
            count++;
            listString = listString + count + "." + ls.next().toString() + "\n";
        }
        return listString;
    }
    public static String printNumberOfEntries() {
        return Ui.indent + "Now you have " + TaskList.getTaskSize() + " tasks in your task scheduler...";
    }
    public static String invalidDateTimeEntry() {
        return "Invalid date/time entry. Please give in yyyy-mm-dd hh:mm format...";
    }
    public static String emptyList() {
        return "Duke could not find your query! Please try again...";
    }
    public static String deleteTaskPrint(Task itemRemoved) {
        String removedTask = indent + "This task was removed..." + "\n" + itemRemoved + "\n";
        removedTask = indent + "Now you have " + TaskList.getTaskSize() + " tasks in your task scheduler...";
        return removedTask;
    }
    public static String findTaskPrint(ListIterator<Task> ls) {
        String findString = indent + "Tasks that may match your search result..." + "\n";
        findString += printList(ls);
        return findString;
    }

    public static String miscMsgPrint(String msg) {
        return msg;
    }
    public static String endDukeMsg() {
        return "bye";
    }
}