import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String INDENT = "    ";
    private static final List<Task> tasks = new ArrayList<>();;
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

    public static void echoMessages() {
        Scanner scanner = new Scanner(System.in);
        String input;

        printGreeting();

        while (true) {
            input = scanner.nextLine();

            if ("bye".equalsIgnoreCase(input)) {
                printExit();
                break;
            }

            printHorizontalLine();
            try {
                if ("list".equalsIgnoreCase(input)) {
                    printList();
                } else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new DukeException("☹ OOPS!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(input.substring(5)));
                    printTaskAdded(tasks.get(tasks.size() - 1));
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 2) throw new DukeException("☹ OOPS!!! Deadline format is incorrect.");
                    tasks.add(new Deadline(parts[0], parts[1]));
                    printTaskAdded(tasks.get(tasks.size() - 1));
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split(" /from ");
                    String[] timeParts = parts[1].split(" /to ");
                    if (parts.length < 2 || timeParts.length < 2) throw new DukeException("Event format is incorrect.");
                    tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
                    printTaskAdded(tasks.get(tasks.size() - 1));
                } else if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(taskNumber - 1).markAsDone();
                    printIndented("Nice! I've marked this task as done:");
                    printIndented("  " + tasks.get(taskNumber - 1));
                } else if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(taskNumber - 1).unmark();
                    printIndented("OK, I've marked this task as not done yet:");
                    printIndented("  " + tasks.get(taskNumber - 1));
                } else if (input.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if (taskNumber > tasks.size() || taskNumber <= 0) {
                        throw new DukeException("☹ OOPS!!! The task number is out of range.");
                    }
                    Task removedTask = tasks.remove(taskNumber - 1);
                    printIndented("Noted. I've removed this task:");
                    printIndented("  " + removedTask);
                    printIndented("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printIndented(e.getMessage());
            }
            printHorizontalLine();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        echoMessages();
    }
}
