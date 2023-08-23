import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static String indent = "     ";
    public static String horizontalLine = "-".repeat(22);
    public static void main(String[] args) {
        String endMessage = "Bye. Hope to see you again soon!";
        String endVal = "bye";
        String listVal = "list";

        String markAsDone = "mark";
        String unmarkAsDone = "unmark";
        String addTask = "task";
        ArrayList<Task> storeTask = new ArrayList(1);

        System.out.println(indent + horizontalLine);
        System.out.println(indent + "Hello! I'm Tom!" + "\n"  + indent + "What can I do for you?" + "\n");
        System.out.println(indent + horizontalLine);

        while (true) {
            System.out.print(indent + "What would you like to do next? : ");
            Scanner userInputObject = new Scanner(System.in);
            String userInput = userInputObject.nextLine();
            if (userInput.equals(endVal)) {
                System.out.println("\n" + indent + endMessage);
                break;
            } else if (userInput.equals(listVal)) {
                int count = 0;
                ListIterator<Task> ls = storeTask.listIterator();
                System.out.println("\n" + indent + "Entries on memory...");
                while (ls.hasNext()) {
                    count++;
                    System.out.println(indent + count + "." + ls.next().toString());
                }
            } else if (userInput.equals(markAsDone) || userInput.equals(unmarkAsDone)) {
                System.out.print(indent + "Please enter the task number to change status: ");
                int statusChange = new Scanner(System.in).nextInt();
                Task taskItem = storeTask.get(statusChange - 1);
                System.out.println(indent + taskItem.changeStatus(userInput));
            }
            else if (userInput.equals("task")){
                System.out.print(indent + "What type of task would you like to add? : ");
                String userTaskChoice = new Scanner(System.in).next();
                if (userTaskChoice.equals("ToDo")) {
                    storeTask.add(ToDo.taskDescriptor());
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoice.equals("Deadline")) {
                    storeTask.add(Deadline.deadlineDescriptor());
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoice.equals("Event")) {
                    storeTask.add(Event.eventDescriptor());
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else {
                    System.out.println("INVALID ENTRY. TRY AGAIN!");
                    System.out.println(indent + horizontalLine);
                }
            } else {
                System.out.println(indent + "INVALID ENTRY. TRY AGAIN!");
                System.out.println(indent + horizontalLine);
            }
        }
    }
}
