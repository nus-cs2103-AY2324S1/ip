import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LOGO = ",------.,--.              ,--.  \n"
            + "|  .---\'|  |,-.,--.,--. ,-|  |  \n"
            + "|  `--, |     /|  ||  |' .-. |   \n"
            + "|  `---.|  \\\\  \\\\  ''  '\\\\ `-\'   \n"
            + "`------'`--'`--'`----'  `---' \n";
    private static final String LINE = "-".repeat(60);
    private static List<Task> tasks = new ArrayList<>();

    public enum CommandType {
        LIST, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        handleUserInput();
        printFarewellMessage();
    }

    private static void handleUserInput() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    break;
                }
                handleCommand(input);
            }
        } catch (Exception e) {
            printErrorMessage(new DukeException("An unexpected error occurred: " + e.getMessage()));
        }
    }

    private static void handleCommand(String command) {
        
        CommandType commandType = parseCommandType(command);

        switch (commandType) {
        case LIST:
            printList();
            break;
        case MARK:
            markTask(command);
            break;
        case DELETE:
            deleteTask(command);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            addTask(command);
            break;
        case UNKNOWN:
            printErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));
            break;
        }
    }

    private static CommandType parseCommandType(String command) {
        if (command.startsWith("list")) {
            return CommandType.LIST;
        } else if (command.startsWith("mark")) {
            return CommandType.MARK;
        } else if (command.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (command.startsWith("todo")) {
            return CommandType.TODO;
        } else if (command.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (command.startsWith("event")) {
            return CommandType.EVENT;
        }
        else {
            return CommandType.UNKNOWN;
        }
    }
    

    private static void printList() {
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static void addTask(String task) {
        try {
            Task newTask = null;
            if (task.startsWith("todo")) {
                newTask = ToDo.createToDoFromCommand(task);
            } else if (task.startsWith("deadline")) {
                newTask = Deadline.createDeadlineFromCommand(task);
            } else if (task.startsWith("event")) {
                newTask = Event.createEventFromCommand(task);
            }

            if (newTask != null) {
                tasks.add(newTask);
                printAddedTaskConfirmation(newTask);
            } 

        } catch (DukeException e) {
            printErrorMessage(e);
        }
    }
    

    private static void markTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                printErrorMessage(new DukeException("Invalid task index"));
                return;
            }
            Task task = tasks.get(index);
            task.markAsDone();
            System.out.println(LINE);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + task);
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            printErrorMessage(new DukeException("Invalid command format"));
        }
    }

    private static void deleteTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                printErrorMessage(new DukeException("Invalid task index"));
                return;
            }
            Task task = tasks.remove(index);
            System.out.println(LINE);
            System.out.println("Noted. I've removed this task: ");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            printErrorMessage(new DukeException("Invalid command format"));
        }
    }
    

    private static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm \\n");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void printFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void printAddedTaskConfirmation(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void printErrorMessage(DukeException e) {
        System.out.println(LINE);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(LINE);
    }

}
