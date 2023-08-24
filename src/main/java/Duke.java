import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "    ____________________________________________________________";
    public static ArrayList<Task> taskArray = new ArrayList<>();
    public static int numTask = 0;
    enum TASK { MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, BYE, LIST, INVALID}
    public static void greet() {
        System.out.println(horizontalLine + "\n"
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine + "\n"
                           + "     Bye. Hope to see you again soon!\n"
                           + horizontalLine);
    }
    public static void unmark(String i) {
        int taskId = Integer.parseInt(i.substring(7)) - 1;
        taskArray.get(taskId).markAsUndone();
    }
    public static void mark(String i) {
        int taskId = Integer.parseInt(i.substring(5)) - 1;
        taskArray.get(taskId).markAsDone();
    }
    public static void deleteTask(String i) {
        int deleteTask = Integer.parseInt(i.substring(7)) - 1;
        Task removed = taskArray.get(deleteTask);
        taskArray.remove(deleteTask);
        numTask--;
        System.out.println("     Noted. I've removed this task:\n"
                           + "     " + removed.printDesc() + "\n"
                           + "     Now you have " + numTask +" tasks in the list.");
    }
    public static void listTask(String i) {
        System.out.println("     Here are the tasks in your list:\n");
        for (int a = 0; a < numTask; a++) {
            System.out.println("     " + (a + 1) + ". " + taskArray.get(a).printDesc());
        }
    }
    public static void todoTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            taskArray.add(new Todo(taskDetails[1]));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                               + "     OOPS!!! The description of todo cannot be empty :(.\n"
                               + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static void deadlineTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            String[] deadlineDetails = taskDetails[1].split("/by", 2);
            taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                               + "     OOPS!!! The description of deadline is incomplete.\n"
                               + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static void eventTask(String i) {
        String[] taskDetails = i.split(" ", 2);
        try {
            String[] eventDetails = taskDetails[1].split("/", 3);
            taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                    eventDetails[2].substring(3)));
            taskArray.get(numTask).printMessage(numTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(horizontalLine + "\n"
                               + "     OOPS!!! The description of event is incomplete :(.\n"
                               + horizontalLine);
            numTask--;
        }
        numTask++;
    }
    public static TASK commandCheck(String command) {
        switch(command) {
            case "bye":
                return TASK.BYE;
            case "unmark":
                return TASK.UNMARK;
            case "mark":
                return TASK.MARK;
            case "delete":
                return TASK.DELETE;
            case "list":
                return TASK.LIST;
            case "todo":
                return TASK.TODO;
            case "deadline":
                return TASK.DEADLINE;
            case "event":
                return TASK.EVENT;
            default:
                return TASK.INVALID;
        }
    }
    public static void printCommand(TASK command, String info) throws DukeException {
        switch(command) {
            case BYE:
                exit();
                break;
            case UNMARK:
                System.out.println(horizontalLine);
                unmark(info);
                System.out.println(horizontalLine);
                break;
            case MARK:
                System.out.println(horizontalLine);
                mark(info);
                System.out.println(horizontalLine);
                break;
            case DELETE:
                System.out.println(horizontalLine);
                deleteTask(info);
                System.out.println(horizontalLine);
                break;
            case LIST:
                System.out.println(horizontalLine);
                listTask(info);
                System.out.println(horizontalLine);
                break;
            case TODO:
                todoTask(info);
                break;
            case EVENT:
                eventTask(info);
                break;
            case DEADLINE:
                deadlineTask(info);
                break;
            default:
                throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            String[] findCommand = command.split(" ", 2);
            TASK order = commandCheck(findCommand[0]);
            try {
                printCommand(order, command);
            } catch (DukeException message) {
                System.out.println(horizontalLine + "\n" + message.getMessage() + "\n" + horizontalLine);
            }
        }
    }
}
