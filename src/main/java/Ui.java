import java.util.ListIterator;
import java.util.Map;

public class Ui {

    public static String indent = "     ";
    public static String horizontalLine = "-".repeat(22);
    Map<String, String> hashMap = Map.of(
            "endMessage", "Bye. Hope to see you again soon!",
            "endVal", "bye",
            "listVal", "list");

    public void showGreeting() {
        System.out.println(indent + horizontalLine);                                        //Helps with readability
        System.out.println(indent + "Hello! I'm Tom!" + "\n" + indent + "What can I do for you?" + "\n");
        System.out.println(indent + horizontalLine);
    }

    public void userInputUsher() {
        System.out.println(indent + "What would you like to do next? Enter date in yyyy-mm-dd hh:mm format: ");
    }

    public void endDukeMsg() {
        System.out.println("\n" + indent + hashMap.get("endMessage"));
    }
    public static void printList(ListIterator<Task> ls) {
        int count = 0;
        System.out.println("\n" + indent + "Entries on memory...");
        while (ls.hasNext()) {
            count++;
            System.out.println(indent + count + "." + ls.next().toString());
        }
    }
    public static void printNumberOfEntries() {
        System.out.println(Ui.indent + "Now you have " + TaskList.storeTask.size() + " tasks in your task scheduler...");
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        System.out.println(indent + horizontalLine);
    }
}
