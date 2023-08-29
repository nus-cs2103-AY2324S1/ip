import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final String INDENT = "    ";
    private static final List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/duke.txt";

    private enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, UNKNOWN
    }

    public static void printGreeting() {
        printHorizontalLine();
        printIndented("Hello! I'm Davidson");
        printIndented("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExit() {
        printHorizontalLine();
        printIndented("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.print(INDENT);
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printIndented(String message) {
        System.out.println(INDENT + message);
    }

    public static void printList() {
        printHorizontalLine();
        printIndented("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printIndented((i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }

    public static void printTaskAdded(Task task) {
        printHorizontalLine();
        printIndented("Got it. I've added this task:");
        printIndented("  " + task);
        printIndented("Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    private static Command parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) return Command.BYE;
        if (input.equalsIgnoreCase("list")) return Command.LIST;
        if (input.startsWith("todo")) return Command.TODO;
        if (input.startsWith("deadline")) return Command.DEADLINE;
        if (input.startsWith("event")) return Command.EVENT;
        if (input.startsWith("mark")) return Command.MARK;
        if (input.startsWith("unmark")) return Command.UNMARK;
        if (input.startsWith("delete")) return Command.DELETE;
        return Command.UNKNOWN;
    }

    private static void ensureDirectoryExists() {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private static void saveTasks() {
        try {
            ensureDirectoryExists();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            printIndented("Error saving tasks to file.");
        }
    }

    private static void loadTasks() {
        try {
            ensureDirectoryExists();
            File f = new File(FILE_PATH);
            if (!f.exists()) return;
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskData = s.nextLine();
                Task task = parseFileTask(taskData);
                tasks.add(task);
            }
            s.close();
        } catch (IOException e) {
            printIndented("Error loading tasks from file.");
        } catch (DukeException e) {
            printIndented("Data file is corrupted: " + e.getMessage());
        }
    }

    private static Task parseFileTask(String taskData) throws DukeException {
        String[] parts = taskData.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type: " + parts[0]);
        }
    }

    public static void echoMessages() {
        Scanner scanner = new Scanner(System.in);
        String input;

        loadTasks();
        printGreeting();

        while (true) {
            input = scanner.nextLine();
            Command command = parseCommand(input);

            printHorizontalLine();

            try {
                switch (command) {
                    case BYE:
                        printExit();
                        scanner.close();
                        return;

                    case LIST:
                        printList();
                        break;

                    case TODO:
                        ToDo todo = new ToDo(input.substring(5));
                        tasks.add(todo);
                        printTaskAdded(todo);
                        saveTasks();
                        break;

                    case DEADLINE:
                        String[] parts = input.substring(9).split(" /by ");
                        if (parts.length < 2) {
                            throw new DukeException("Deadline format is incorrect.");
                        }
                        Deadline deadline = new Deadline(parts[0], parts[1]);
                        tasks.add(deadline);
                        printTaskAdded(deadline);
                        saveTasks();
                        break;

                    case EVENT:
                        String[] eventParts = input.substring(6).split(" /from ");
                        String[] timeParts = eventParts[1].split(" /to ");
                        if (eventParts.length < 2 || timeParts.length < 2) {
                            throw new DukeException("Event format is incorrect.");
                        }
                        Event event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                        tasks.add(event);
                        printTaskAdded(event);
                        saveTasks();
                        break;

                    case MARK:
                        int taskNumberMark = Integer.parseInt(input.split(" ")[1]);
                        tasks.get(taskNumberMark - 1).markAsDone();
                        printIndented("I've marked this task as done:");
                        printIndented("  " + tasks.get(taskNumberMark - 1));
                        saveTasks();
                        break;

                    case UNMARK:
                        int taskNumberUnmark = Integer.parseInt(input.split(" ")[1]);
                        tasks.get(taskNumberUnmark - 1).unmark();
                        printIndented("I've marked this task as not done:");
                        printIndented("  " + tasks.get(taskNumberUnmark - 1));
                        saveTasks();
                        break;

                    case DELETE:
                        int taskNumberDelete = Integer.parseInt(input.split(" ")[1]);
                        Task removedTask = tasks.remove(taskNumberDelete - 1);
                        printIndented("Noted. I've removed this task:");
                        printIndented("  " + removedTask);
                        printIndented("Now you have " + tasks.size() + " tasks in the list.");
                        saveTasks();
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printIndented(e.getMessage());
            }

            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        echoMessages();
    }
}
