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
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
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
            } else if ("list".equalsIgnoreCase(input)) {
                printList();
            } else if (input.startsWith("todo ")) {
                tasks.add(new ToDo(input.substring(5)));
                printTaskAdded(tasks.get(tasks.size() - 1));
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks.add(new Deadline(parts[0], parts[1]));
                printTaskAdded(tasks.get(tasks.size() - 1));
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ");
                String[] timeParts = parts[1].split(" /to ");
                tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
                printTaskAdded(tasks.get(tasks.size() - 1));
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks.get(taskNumber - 1).markAsDone();
                printHorizontalLine();
                printIndented("Nice! I've marked this task as done:");
                printIndented("  " + tasks.get(taskNumber - 1));
                printHorizontalLine();
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks.get(taskNumber - 1).unmark();
                printHorizontalLine();
                printIndented("OK, I've marked this task as not done yet:");
                printIndented("  " + tasks.get(taskNumber - 1));
                printHorizontalLine();
            } else {
                tasks.add(new Task(input));
                printTaskAdded(tasks.get(tasks.size() - 1));
            }
        }

        scanner.close();
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        echoMessages();
    }
}
