package dukeUiElements;
import duke.TaskList;
import task.Task;
import java.util.ListIterator;
import java.util.Map;

public class Ui {
    public static String indent = "     ";
    public static String horizontalLine = "-".repeat(22);
    static Map<String, String> hashMap = Map.of(
            "endMessage", "Bye. Hope to see you again soon!",
            "endVal", "bye",
            "listVal", "list");

    public void showGreeting() {
        System.out.println(indent + horizontalLine);
        System.out.println(indent + "Hello! I'm Tom!" + "\n" + indent + "What can I do for you?" + "\n");
        System.out.println(indent + horizontalLine);
    }

    public void userInputUsher() {
        System.out.println(indent + "What would you like to do next? Enter date in yyyy-mm-dd hh:mm format: ");
    }
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
    public static void printHorizontalLine() {
        System.out.println(indent + horizontalLine);
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

    public static void miscMsgPrint(String msg) {
        System.out.println(msg);
    }
    public static String endDukeMsg() {
        return "bye";
    }
}
