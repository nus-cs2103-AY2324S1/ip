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
        Scanner userInputObject = new Scanner(System.in);
        while (true) {
            System.out.println(indent + "What would you like to do next? : ");
            String userInput = userInputObject.nextLine();
            String[] userInputList = userInput.split(" ", 2);
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
            } else if (userInputList[0].equals(markAsDone) || userInputList[0].equals(unmarkAsDone)) {
                //System.out.println(indent + "Please enter the task number to change status: ");
                //int statusChange = new Scanner(System.in).nextInt();
                String taskNumber = userInputList[1];
                Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
                System.out.println(indent + taskItem.changeStatus(userInputList[0]));
            }
            else if (userInput.equals("task")){
                System.out.println(indent + "What task would you like to add? : ");
                String userTaskChoice = userInputObject.nextLine();
                String[] userTaskChoiceList = userTaskChoice.split(" ", 2);
                if (userTaskChoiceList[0].equals("ToDo")) {
                    //storeTask.add(ToDo.taskDescriptor());
                    storeTask.add(new ToDo(userTaskChoiceList[1]));
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoiceList[0].equals("Deadline")) {
                    String[] deadlineList = userTaskChoiceList[1].split("/", 2);
                    storeTask.add(new Deadline(deadlineList[0], deadlineList[1]));
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoiceList[0].equals("Event")) {
                    String[] eventList = userTaskChoiceList[1].split("/", 3);
                    storeTask.add(new Event(eventList[0], eventList[1], eventList[2]));
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
