import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Duke {
    public static final int DEADLINEOFFSET = 9;
    public static final int EVENTOFFSET = 6;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskStorage = new Task[100];
        int storageIndex = 0;

        String chatBotName = "Benedit Cucumber Badge";
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");


        boolean dontTerminate = true;
        while (dontTerminate) {
            String input = scanner.nextLine();
            Command command = Command.getCommand(input);
            // Split the string based on spaces
            String[] splitString = input.split(" ");
            switch (command) {
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    dontTerminate = false;
                    break;
                case LIST:
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storageIndex; i++) {
                        int itemNumber = i + 1;
                        System.out.println(itemNumber + ". " + taskStorage[i].toString());
                    }
                    break;
                case UNMARK:
                    // Order matters, if we check for marks first, unmark falls under mark loop
                    try {
                        // Split the string based on spaces
                        // The input after the mark word should be task no (which should be index 1)
                        int taskNumber = Integer.parseInt(splitString[1]);
                        // The above should throw a NumberFormatException
                        Task selectedTask = taskStorage[taskNumber - 1];
                        selectedTask.undoTask();
                    } catch (NumberFormatException e) {
                        System.out.println("You can't mark anything else other than a valid task number");
                    }
                    break;
                case MARK:
                    try {
                        // The input after the mark word should be task no (which should be index 1)
                        int taskNumber = Integer.parseInt(splitString[1]);
                        // The above should throw a NumberFormatException
                        Task selectedTask = taskStorage[taskNumber - 1];
                        selectedTask.completeTask();
                    } catch (NumberFormatException e) {
                        System.out.println("You can't un-mark anything else other than a valid task number");
                    }
                    break;
                case ADD:
                    taskStorage[storageIndex] = new Task(input);
                    taskStorage[storageIndex].taskAdded(storageIndex + 1);
                    storageIndex++;
                    break;
                case TODO:
                    // for To-Dos anything after the command is task name
                    taskStorage[storageIndex] = new ToDos(splitString[1]);
                    taskStorage[storageIndex].taskAdded(storageIndex + 1);
                    storageIndex++;
                    break;
                case DEADLINE:
                    // for Deadline is slightly more complex, we will split by /by
                    String[] segementedString = input.split(" /by ");
                    // We should only have 2 segments for the Array, before and after
                    String deadline = segementedString[1];
                    // Hardcoded because we know how words are positioned
                    String taskNameDeadline = segementedString[0].substring(DEADLINEOFFSET);
                    Task deadlineTask = new Deadline(taskNameDeadline, deadline);
                    taskStorage[storageIndex] = deadlineTask;
                    taskStorage[storageIndex].taskAdded(storageIndex + 1);
                    storageIndex++;
                    break;
                case EVENT:
                    // Event, split string twice to get relevant component
                    String[] segmentedViaBy = input.split(" /from ");
                    String taskNameEvent = segmentedViaBy[0].substring(EVENTOFFSET);
                    String[] segmentedViaTo = segmentedViaBy[1].split(" /to ");
                    String start = segmentedViaTo[0];
                    String end = segmentedViaTo[1];
                    Task event = new Event(taskNameEvent, start, end);
                    taskStorage[storageIndex] = event;
                    taskStorage[storageIndex].taskAdded(storageIndex + 1);
                    storageIndex++;
                    break;
            }

            /*
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                dontTerminate = false;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < storageIndex; i++) {
                    int itemNumber = i + 1;
                    System.out.println(itemNumber + ". " + taskStorage[i].toString());
                }
            } else if (input.contains("unmark")) {
                // Order matters, if we check for marks first, unmark falls under mark loop
                try {
                    // Split the string based on spaces
                    String[] splitString = input.split(" ");
                    // The input after the mark word should be task no (which should be index 1)
                    int taskNumber = Integer.parseInt(splitString[1]);
                    // The above should throw a NumberFormatException
                    Task selectedTask = taskStorage[taskNumber - 1];
                    selectedTask.undoTask();
                } catch (NumberFormatException e) {
                    System.out.println("You can't mark anything else other than a valid task number");
                }
            } else if (input.contains("mark")) {
                try {
                    // Split the string based on spaces
                    String[] splitString = input.split(" ");
                    // The input after the mark word should be task no (which should be index 1)
                    int taskNumber = Integer.parseInt(splitString[1]);
                    // The above should throw a NumberFormatException
                    Task selectedTask = taskStorage[taskNumber - 1];
                    selectedTask.completeTask();
                } catch (NumberFormatException e) {
                    System.out.println("You can't un-mark anything else other than a valid task number");
                }
            }

            else {
                taskStorage[storageIndex] = new Task(input);
                storageIndex++;
                taskStorage[storageIndex].taskAdded(storageIndex);
            }

            */
        }





    }
}
