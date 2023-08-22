import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "    ";
    private static ArrayList<Task> store = new ArrayList<Task>();

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    private static String formatList(ArrayList storeList) {
        String str = "";
        int len = storeList.size();
        for (int i = 0; i < len; i++) {
            str = str + Integer.toString(i + 1) + ". " + storeList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    private static void handleMarking(int index, String status) {
        if (index > store.size() || index < 0) {
            System.out.println(formatOutput("Invalid Index!"));
        } else {
            Task selectedTask = store.get(index);
            if (status.equals("mark")) {
                selectedTask.markTask();
                System.out.println(formatOutput("Nice! I've marked the task as done:\n   " +
                        INDENTATION + selectedTask));
            } else if (status.equals("unmark")) {
                selectedTask.unmarkTask();
                System.out.println(formatOutput("OK, I've marked this task as not done yet:\n   " +
                        INDENTATION + selectedTask));
            }
        }
    }

    private static void handleToDo(String task) {
        ToDo item = new ToDo(task);
        store.add(item);
        System.out.println(formatOutput("Got it. I've added this task: \n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                store.size() +  " tasks in the list."));
    }

    private static void handleDeadline(String task) {
        String[] arr = task.split(" /by ");
        if (arr.length != 2) {
            System.out.println(formatOutput("Invalid Deadline!"));
            return;
        }
        Deadline item = new Deadline(arr[0], arr[1]);
        store.add(item);
        System.out.println(formatOutput("Got it. I've added this task: \n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                store.size() +  " tasks in the list."));
    }

    private static void handleEvent(String task) {
        String[] arr = task.split(" /from ");
        if (arr.length != 2) {
            System.out.println(formatOutput("Invalid Event!"));
            return;
        }
        String desc = arr[0];

        String[] startEnd = arr[1].split(" /to ");
        if (startEnd.length != 2) {
            System.out.println("Invalid Start / End!");
            return;
        }
        String start = startEnd[0];
        String end = startEnd[1];

        Event item = new Event(desc, start, end);
        store.add(item);
        System.out.println(formatOutput("Got it. I've added this task: \n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                store.size() +  " tasks in the list."));
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String command, commandString;
        String[] commandArray;
        int commandIndex = 0;

        while (true) {
            commandString = sc.nextLine();
            commandArray = commandString.split(" ");
            command = commandArray[0];

            if (command.equals("bye")) {
                System.out.println(formatOutput("Bye. Hope to see you again soon!"));
                break;
            } else if (command.equals("list")) {
                System.out.println(formatOutput(formatList(store)));
            } else if (command.equals("mark") || command.equals("unmark")) {
                commandIndex = Integer.parseInt(commandArray[1]) - 1;
                handleMarking(commandIndex, command);
            } else if (command.equals("todo")) {
                String newStr = commandString.replaceFirst(command + " ", "");
                handleToDo(newStr);
            } else if (command.equals("deadline")) {
                String newStr = commandString.replaceFirst(command + " ", "");
                handleDeadline(newStr);
            } else if (command.equals("event")) {
                String newStr = commandString.replaceFirst(command + " ", "");
                handleEvent(newStr);
            }
            else {
                store.add(new Task(commandString));
                System.out.println(formatOutput("added: " + commandString));
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
        handleCommand();

    }
}
