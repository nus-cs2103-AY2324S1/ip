import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static final String NAME = "Duke";

    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        Boolean isRunning = true;
        while (isRunning) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark", 0)) {
                markAsDone(input);
            } else if (input.startsWith("unmark", 0)) {
                unmarkAsDone(input);
            } else if (input.equals("bye")) {
                isRunning = false;
            } else {
                addToList(input);
            }
        }

        bye();
        sc.close();
    }

    public static void hello() {
        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation("Hello! I'm " + NAME);
        printWithIndentation("What can I do for you?");
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void bye() {
        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation("Bye. Hope to see you again soon!");
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void echo(String input) {
        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation(input);
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void printList() {
        printWithIndentation(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            printWithIndentation((i + 1) + "." + tasks.get(i));
        }
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void addToList(String input) {
        String taskType = input.split(" ", 2)[0];
        String content = input.split(" ", 2)[1];
        String description = content.split(" /", 3)[0];

        Task task;
        if (taskType.equals("deadline")) {
                String by = content.split(" /", 3)[1].split(" ", 2)[1];
                task = new Deadline(description, by);
        } else if (taskType.equals("event")) {
                String from = content.split(" /", 3)[1].split(" ", 2)[1];
                String to = content.split(" /", 3)[2].split(" ", 2)[1];
                task = new Event(description, from, to);
        } else {
            task = new Todo(description);
        }

        tasks.add(task);

        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation("Got it. I've added this task:");
        printWithIndentation("  " + task);
        printWithIndentation("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void markAsDone(String input) {
        int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        tasks.get(index).markAsDone();

        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation("Nice! I've marked this task as done:");
        printWithIndentation("  " + tasks.get(index));
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void unmarkAsDone(String input) {
        int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        tasks.get(index).unmarkAsDone();

        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation("OK, I've marked this task as not done yet:");
        printWithIndentation("  " + tasks.get(index));
        printWithIndentation(HORIZONTAL_LINE);
    }

    private static void printWithIndentation(String input) {
        System.out.println("    " + input);
    }
}
