import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static enum COMMAND {
        LIST, MARK, UNMARK, DELETE, BYE, TODO, DEADLINE, EVENT
    }

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static final String NAME = "Duke";

    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        Boolean isRunning = true;
        while (isRunning) {
            String[] inputs = sc.nextLine().split(" ", 2);
            String command = inputs[0].toUpperCase();
            String argument = inputs.length == 2 ? inputs[1] : "";
            try {
                switch (COMMAND.valueOf(command)) {
                    case LIST:
                        printTasks();
                        break;
                    case MARK:
                    case UNMARK:
                    case DELETE:
                        try {
                            int index = Integer.parseInt(argument) - 1;
                            editTask(command, index);
                        } catch (NumberFormatException e) {
                            throw new DukeException("\u2639 OOPS!!! The index of a task must be a number.");
                        }
                        break;
                    case BYE:
                        isRunning = false;
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        addTask(command, argument);
                        break;
                    default:
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printFormattedMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                printFormattedMessage("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        bye();
        sc.close();
    }

    public static void hello() {
        String message = "Hello! I'm " + NAME + "\nWhat can I do for you?";
        printFormattedMessage(message);
    }

    public static void bye() {
        String message = "Bye. Hope to see you again soon!";
        printFormattedMessage(message);
    }

    public static void printTasks() {
        printWithIndentation(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            printWithIndentation((i + 1) + "." + tasks.get(i));
        }
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void addTask(String command, String argument) throws DukeException {
        if (argument.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }

        String[] inputs = argument.split(" /", 3);
        String description = inputs[0];
        Task task;

        switch (COMMAND.valueOf(command)) {
            case TODO:
                task = new Todo(description);
                break;
            case DEADLINE:
                try {
                    String by = inputs[1].split(" ", 2)[1];
                    task = new Deadline(description, by);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 OOPS!!! The deadline of a deadline cannot be empty.");
                }
                break;
            case EVENT:
                try {
                    String from = inputs[1].split(" ", 2)[1];
                    String to = inputs[2].split(" ", 2)[1];
                    task = new Event(description, from, to);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 OOPS!!! The start time or end time of an event cannot be empty.");
                }
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        tasks.add(task);

        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
        printFormattedMessage(message);
    }

    public static void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();

        String message = "Nice! I've marked this task as done:\n  " + tasks.get(index);
        printFormattedMessage(message);
    }

    public static void unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();

        String message = "OK, I've marked this task as not done yet:\n  " + tasks.get(index);
        printFormattedMessage(message);
    }

    public static void deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);

        String message = "Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
        printFormattedMessage(message);
    }

    private static void printWithIndentation(String input) {
        System.out.println("    " + input);
    }

    private static void printFormattedMessage(String input) {
        printWithIndentation(HORIZONTAL_LINE);
        String[] lines = input.split("\n");
        for (String line : lines) {
            printWithIndentation(line);
        }
        printWithIndentation(HORIZONTAL_LINE);
    }

    private static void editTask(String command, int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index of a task must be within the range of the list.");
        }

        switch (COMMAND.valueOf(command)) {
            case MARK:
                markTaskAsDone(index);
                break;
            case UNMARK:
                unmarkTaskAsDone(index);
                break;
            case DELETE:
                deleteTask(index);
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
