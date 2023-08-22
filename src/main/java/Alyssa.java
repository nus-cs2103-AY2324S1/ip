import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main program.
 */
public class Alyssa {
    private static final String line = "____________________________________________________________";
    private static final String logo = " ___  __    __   __  ____   ____    ___\n"
            + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
            + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
            + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
            + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
            + "|   | |_____| |_|    |____| |____| |   |\n";
    private static List<Task> taskList;
    private static void greet() {
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    private static void goodbye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    private static void listTasks() {
        int counter = 1;
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(counter + "." + task.toString());
            counter++;
        }
        System.out.println(line);
    }
    private static void markTask(int index) throws Exception {
        if (index < 1 || index > taskList.size()) {
            throw new Exception("Invalid index");
        }
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(line);
    }
    private static void unmarkTask(int index) throws Exception {
        if (index < 1 || index > taskList.size()) {
            throw new Exception("Invalid index");
        }
        Task task = taskList.get(index - 1);
        task.markAsUndone();
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(line);
    }
    private static void addTodo(String desc) throws Exception, StringIndexOutOfBoundsException {
        Task newTodo = new Todo(desc);
        taskList.add(newTodo);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void addDeadline(String rest) throws Exception {
        String[] parsed = rest.split(" /by ");
        String desc = parsed[0];
        String by = parsed[1];
        Task newDeadline = new Deadline(desc, by);
        taskList.add(newDeadline);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void addEvent(String rest) throws Exception {
        String[] parsed = rest.split(" /from | /to ");
        String desc = parsed[0];
        String from = parsed[1];
        String to = parsed[2];
        Task newEvent = new Event(desc, from, to);
        taskList.add(newEvent);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void deleteTask(int index) throws Exception {
        if (index < 1 || index > taskList.size()) {
            throw new Exception("Invalid index");
        }
        Task toDelete = taskList.get(index - 1);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDelete.toString());
        taskList.remove(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    private static void invalidTaskResponse() {
        System.out.println(line);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }
    //CHECKSTYLE.OFF: Indentation
    private static void run(Command command, String rest) {
        switch (command) {
            case BYE:
                goodbye();
                break;
            case LIST:
                listTasks();
                break;
            case MARK:
                try {
                    markTask(Integer.valueOf(rest));
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a valid task number.");
                    System.out.println(line);
                }
                break;
            case UNMARK:
                try {
                    unmarkTask(Integer.valueOf(rest));
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a valid task number.");
                    System.out.println(line);
                }
                break;
            case TODO:
                try {
                    addTodo(rest);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a legitimate task number.");
                    System.out.println(line);
                }
                break;
            case DEADLINE:
                try {
                    addDeadline(rest);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a legitimate deadline.");
                    System.out.println(line);
                }
                break;
            case EVENT:
                try {
                    addEvent(rest);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a legitimate event.");
                    System.out.println(line);
                }
                break;
            case DELETE:
                try {
                    deleteTask(Integer.valueOf(rest));
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a legitimate task to delete.");
                    System.out.println(line);
                }
                break;
            default:
                invalidTaskResponse();
        }
    }
    //CHECKSTYLE.ON: Indentation
    //CHECKSTYLE.OFF: Indentation
    private static Command assignCommand(String cmd) {
        switch (cmd) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "todo":
                return Command.TODO;
            case "event":
                return Command.EVENT;
            case "deadline":
                return Command.DEADLINE;
            case "delete":
                return Command.DELETE;
            default:
                return Command.INVALID;
        }
    }
    //CHECKSTYLE.ON: Indentation
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();
        boolean isRunning = true;
        Command command;
        greet();
        while (isRunning) {
            String nextInput = sc.nextLine();
            String[] parsedInput = nextInput.split(" ", 2);
            String commandString = parsedInput[0];
            command = assignCommand(commandString);
            String rest = parsedInput.length > 1 ? parsedInput[1] : "";
            run(command, rest);
            if (command == Command.BYE) {
                isRunning = false;
            }
        }
    }
}
