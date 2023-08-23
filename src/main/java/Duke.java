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
            str = str + (i + 1) + ". " + storeList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    private static void handleMarking(String commandNum, String status) throws DukeException {

        int index = Integer.parseInt(commandNum) - 1;

        if (index > store.size() - 1 || index < 0) {
            throw new DukeException("Invalid Task Index provided!");
        }
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

    private static void handleDelete(String commandNum, String status) throws DukeException {

        int index = Integer.parseInt(commandNum) - 1;

        if (index > store.size() - 1 || index < 0) {
            throw new DukeException("Invalid Task Index provided!");
        }
        Task selectedTask = store.remove(index);
        System.out.println(formatOutput("Noted. I've removed this task:\n   " +
                INDENTATION + selectedTask + "\n " + INDENTATION + "Now you have " +
                store.size() + " tasks in the list."));
    }

    private static void handleToDo(String task) throws DukeException {

        if (task.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }
        ToDo item = new ToDo(task);
        store.add(item);
        System.out.println(formatOutput("Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                store.size() + " tasks in the list."));
    }

    private static void handleDeadline(String task) throws DukeException {
        if (task.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }
        String[] arr = task.split(" /by ");
        if (arr.length != 2) {
            throw new DukeException("Hey, the Deadline given is Invalid! " +
                    "Make sure that you follow this format:\n" +
                    INDENTATION + "'taskDescription /by time'");
        }
        Deadline item = new Deadline(arr[0], arr[1]);
        store.add(item);
        System.out.println(formatOutput("Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                store.size() + " tasks in the list."));
    }

    private static void handleEvent(String task) throws DukeException {
        if (task.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }
        String[] arr = task.split(" /from ");
        if (arr.length != 2) {
            throw new DukeException("Hey, the Event given is Invalid!" +
                    " Make sure that you follow this format:\n" +
                    INDENTATION + " 'eventDescription /from startTime /to endTime'");
        }
        String desc = arr[0];

        String[] startEnd = arr[1].split(" /to ");
        if (startEnd.length != 2) {
            throw new DukeException("Hey, the Event given is Invalid!" +
                    " Make sure that you follow this format:\n" +
                    INDENTATION + " 'eventDescription /from startTime /to endTime'");
        }
        String start = startEnd[0];
        String end = startEnd[1];

        Event item = new Event(desc, start, end);
        store.add(item);
        System.out.println(formatOutput("Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                store.size() + " tasks in the list."));
    }

    private static void checkCommandArguments(String[] commandArr) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException("Hey, the description of a " + commandArr[0] + " cannot be empty!");
        }
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String command, commandString;
        String[] commandArray;

        while (true) {
            commandString = sc.nextLine();

            if (commandString.length() == 0) {
                System.out.println(formatOutput("Hey, Type Something!"));
                continue;
            }

            commandArray = commandString.split(" ", 2);
            command = commandArray[0];

            try {
                if (command.equals("bye")) {
                    System.out.println(formatOutput("Bye. Hope to see you again soon!"));
                    break;
                } else if (command.equals("list")) {
                    System.out.println(formatOutput(formatList(store)));
                    continue;
                }

                if (command.equals("mark") || command.equals("unmark")) {
                    checkCommandArguments(commandArray);
                    handleMarking(commandArray[1], command);
                } else if (command.equals("delete")) {
                    checkCommandArguments(commandArray);
                    handleDelete(commandArray[1], command);
                } else if (command.equals("todo")) {
                    checkCommandArguments(commandArray);
                    handleToDo(commandArray[1]);
                } else if (command.equals("deadline")) {
                    checkCommandArguments(commandArray);
                    handleDeadline(commandArray[1]);
                } else if (command.equals("event")) {
                    checkCommandArguments(commandArray);
                    handleEvent(commandArray[1]);
                } else if (command.equals("blah")) {
                    System.out.println(formatOutput("I don't understand what you're saying."));
                } else {
                    store.add(new Task(commandString));
                    System.out.println(formatOutput("added: " + commandString));
                }

            } catch (DukeException e) {
                System.out.println(formatOutput(e.getMessage()));
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
        handleCommand();

    }
}
