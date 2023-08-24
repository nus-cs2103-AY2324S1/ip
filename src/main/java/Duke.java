import java.util.*;
import java.util.Map;
import java.util.ListIterator;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static String indent = "     ";
    public static String horizontalLine = "-".repeat(22);
    public static void main(String[] args) {
        Map<String, String> hashMap = Map.of(
                "endMessage", "Bye. Hope to see you again soon!",
                "endVal", "bye",
                "listVal", "list",
                "addTask", "task" );
        List<String> taskKeyVal = new ArrayList<>(Arrays.asList("ToDo", "Deadline", "Event", "Delete", "mark", "unmark"));

        ArrayList<Task> storeTask = new ArrayList(1);

        System.out.println(indent + horizontalLine);
        System.out.println(indent + "Hello! I'm Tom!" + "\n" + indent + "What can I do for you?" + "\n");
        System.out.println(indent + horizontalLine);
        Scanner userInputObject = new Scanner(System.in);
        while (true) {
            System.out.println(indent + "What would you like to do next? : ");
            String userInput = userInputObject.nextLine();
            String[] userInputList = userInput.split(" ", 2);
            String userTaskChoiceKey = userInputList[0];
            try {
                if (userInput.equals(hashMap.get("endVal"))) {
                    System.out.println("\n" + indent + hashMap.get("endMessage"));
                    break;
                } else if (userInput.equals(hashMap.get("listVal"))) {
                    int count = 0;
                    ListIterator<Task> ls = storeTask.listIterator();
                    System.out.println("\n" + indent + "Entries on memory...");
                    while (ls.hasNext()) {
                        count++;
                        System.out.println(indent + count + "." + ls.next().toString());
                    }
                } else if (userInputList[0].equals("mark") || userInputList[0].equals("unmark")) {
                    String taskNumber = userInputList[1];
                    Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
                    System.out.println(indent + taskItem.changeStatus(userInputList[0]));
                } else if (userInputList.length == 1 && taskKeyVal.contains(userTaskChoiceKey)) {
                    throw new DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
                } else if (userTaskChoiceKey.equals("ToDo")) {
                    storeTask.add(new ToDo(userInputList[1]));
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoiceKey.equals("Deadline")) {
                    String[] deadlineList = userInputList[1].split("/", 2);
                    storeTask.add(new Deadline(deadlineList[0], deadlineList[1]));
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoiceKey.equals("Event")) {
                    String[] eventList = userInputList[1].split("/", 3);
                    storeTask.add(new Event(eventList[0], eventList[1], eventList[2]));
                    System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    System.out.println(indent + horizontalLine);
                } else if (userTaskChoiceKey.equals("Delete")) {
                    Integer delUserChoice = Integer.parseInt(userInputList[1]);
                    if ((delUserChoice - 1) < 0) {
                        throw new DukeException("Invalid Task entered. Please try again...");
                    } else if (storeTask.isEmpty()) {
                        throw new DukeException("Task Scheduler is empty... Please try again!");
                    } else {
                        Task itemRemoved = storeTask.remove(delUserChoice - 1);
                        System.out.println(indent + "This task was removed..." + "\n" + itemRemoved);
                        System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                    }
                }
                  else {
                    throw new DukeException("☹ OOPS!!! Sorry, but i do not know what that means :-(");
                }
            }
            catch (NumberFormatException e) {
                System.out.println(indent + "Invalid character input");
            }
            catch (DukeException e) {
                System.out.println(indent + "Error: " + e.getMessage());
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(indent + "Invalid entry / Task not in list... Please try again...");
            }
        }
    }
}

