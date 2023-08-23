import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String endMessage = "Bye. Hope to see you again soon!";
        String endVal = "bye";
        String listVal = "list";
        String indent = "     ";
        ArrayList<String> storeTask = new ArrayList(1);
        String horizontalLine = "-".repeat(22);
        System.out.println(indent + horizontalLine);
        System.out.println(indent + "Hello! I'm Tom!" + "\n"  + indent + "What can I do for you?" + "\n");
        System.out.println(indent + horizontalLine);

        while (true) {
            Scanner userInputObject = new Scanner(System.in);
            String userInput = userInputObject.nextLine();
            if (userInput.equals(endVal)) {
                System.out.println("\n" + indent + endMessage);
                break;
            } else if (userInput.equals(listVal)) {
                int count = 0;
                ListIterator<String> ls = storeTask.listIterator();
                System.out.println("\n" + indent + "Entries on memory...");
                while (ls.hasNext()) {
                    count++;
                    System.out.println(indent + count + ". " + ls.next());
                }
            } else {
                System.out.println(indent + horizontalLine);
                storeTask.add(userInput);
                System.out.println(indent + "added: " + userInput);
                System.out.println(indent + horizontalLine);

            }
        }
    }
}
