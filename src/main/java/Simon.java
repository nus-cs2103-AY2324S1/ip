import java.util.ArrayList;
import java.util.Scanner;

public class Simon {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String SPACE = "____________________________________________________________";
    private static final String NSPACE = "\n____________________________________________________________";
    private static final String SPACEN = "____________________________________________________________\n";

    enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Start Program
        System.out.println(SPACEN + SimonAscii.toStr());
        System.out.println("Hello! I'm Simon\nWhat can I do for you?\n" + SPACE);

        while (true) {
            String inData = scan.nextLine();
            Command command = parseCommand(inData.split(" ")[0]);

            try {
                switch (command) {
                    case LIST:
                        listTasks();
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        addTask(inData, command);
                        break;
                    case UNMARK:
                        markTask(inData, false);
                        break;
                    case MARK:
                        markTask(inData, true);
                        break;
                    case DELETE:
                        deleteTask(inData);
                        break;
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!" + NSPACE);
                        return;
                    case UNKNOWN:
                    default:
                        throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (SimonException se) {
                System.out.println(se.getMessage() + NSPACE);
            }
        }
    }

    private static Command parseCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(SPACE);
    }

    private static void addTask(String inData, Command commandType) throws SimonException {
        Task task;
        switch (commandType) {
            case TODO:
                String description = inData.split("todo ").length > 1 ? inData.split("todo ")[1] : "";
                if (description.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(description);
                break;

            case DEADLINE:
                String[] deadlineParts = inData.split("deadline ");
                if (deadlineParts.length <= 1 || !inData.contains(" /by ")) {
                    throw new SimonException("☹ OOPS!!! The format for deadline is incorrect. Expected format: 'deadline [task description] /by [deadline]'.");
                }
                String nameDeadline = deadlineParts.length > 1 ? deadlineParts[1].split("/by ")[0] : "";
                if (nameDeadline.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String endDate = deadlineParts[1].split(" /by ").length > 1 ? deadlineParts[1].split(" /by ")[1] : "";
                if (endDate.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The deadline date cannot be empty.");
                }
                task = new Deadline(nameDeadline, endDate);
                break;



            case EVENT:
                String[] eventParts = inData.split("event ");
                if (eventParts.length <= 1 || !inData.contains("/from ") || !inData.contains("/to ")) {
                    throw new SimonException("☹ OOPS!!! The format for event is incorrect. Expected format: 'event [event description] /from [start date] /to [end date]'.");
                }

                String[] fromToParts = eventParts[1].split("/from ");
                if (fromToParts.length < 2) {
                    throw new SimonException("☹ OOPS!!! The format for event is missing 'from' information.");
                }

                String eventName = fromToParts[0].trim();
                if (eventName.isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of an event cannot be empty.");
                }

                String[] toParts = fromToParts[1].split("/to ");
                if (toParts.length < 2) {
                    throw new SimonException("☹ OOPS!!! The format for event is missing 'to' information.");
                }

                String startDate = toParts[0].trim();
                String endDateEvent = toParts[1].trim();

                task = new Event(eventName, startDate, endDateEvent);
                break;


            default:
                throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        tasks.add(task);
        System.out.println(SPACEN + "Got it. I've added this task:\n" + " " +
                task + String.format("\nNow you have %d %s in the list.",
                tasks.size(), tasks.size() > 1 ? "tasks" : "task") + NSPACE);
    }

    private static void markTask(String inData, boolean markAsDone) throws SimonException {
        String[] split = inData.split(" ");

        int index;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }

        if (tasks.isEmpty()) {
            throw new SimonException("There are no tasks to mark.");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }

        if (markAsDone) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n[X] " + tasks.get(index) + NSPACE);
        } else {
            tasks.get(index).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n[ ] " + tasks.get(index) + NSPACE);
        }
    }

    private static void deleteTask(String inData) throws SimonException {
        String[] split = inData.split(" ");

        int index = 0;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }

        if (tasks.isEmpty()) {
            throw new SimonException("There are no tasks to delete.");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }

        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n" + task + String.format("\nNow you have %d %s in the list.",
                tasks.size(), tasks.size() - 1 > 1 ? "tasks" : "task") + NSPACE);
    }
}
