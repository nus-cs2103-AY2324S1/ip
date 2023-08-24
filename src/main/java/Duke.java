import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> arr = new ArrayList<>();
    private static final String HORIZONTAL_LINE = "    _______________________________________________________________";

    public static void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void printList() {
        int index = 0;
        System.out.println("    Here are the tasks in your list:");
        for (Task task: arr) {
            System.out.println("    " + (++index) + "." + task.toString());
        }
    }

    public static void addTask(String task) {
        if (task.startsWith("todo")) {
            System.out.println("    Got it. I've added this task:");
            arr.add(new Todo(task.substring(5)));
            System.out.println("      " + arr.get(arr.size() - 1).toString());
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
        }
        else if (task.startsWith("deadline")) {
            System.out.println("    Got it. I've added this task:");
            String description = "";
            String by = "";
            for (int i = 9; i < task.length(); ++i) {
                if (task.charAt(i) == '/') {
                    description = task.substring(9, i - 1);
                    by = task.substring(i + 4);
                    break;
                }
            }
            arr.add(new Deadline(description, by));
            System.out.println("      " + arr.get(arr.size() - 1).toString());
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
        }
        else if (task.startsWith("event")) {
            System.out.println("    Got it. I've added this task:");
            int slash1 = -1;
            int slash2 = -1;
            for (int i = 0; i < task.length(); ++i) {
                if (task.charAt(i) == '/') {
                    if (slash1 == -1) slash1 = i;
                    else slash2 = i;
                }
            }
            String description = task.substring(6, slash1 - 1);
            String from = task.substring(slash1 + 6, slash2 - 1);
            String to = task.substring(slash2 + 4);
            arr.add(new Event(description, from, to));
            System.out.println("      " + arr.get(arr.size() - 1).toString());
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
        }
    }

    public static void markTaskDone(int index) {
        arr.get(index - 1).markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + arr.get(index - 1).toString());
    }

    public static void markTaskNotDone(int index) {
        arr.get(index - 1).markAsNotDone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + arr.get(index - 1).toString());
    }

    public static void processCommand(String command) {
        if (command.equals("bye")) {
            bye();
        }
        else if (command.equals("list")) {
            printList();
        }
        else if (command.startsWith("mark")) {
            int index = 0;
            for (int i = 5; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            markTaskDone(index);
        }
        else if (command.startsWith("unmark")) {
            int index = 0;
            for (int i = 7; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            markTaskNotDone(index);
        }
        else {
            addTask(command);
        }
    }

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Hello! I'm Thinh's chatbot\n    What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        Scanner in = new Scanner(System.in);
        String inputStr;
        do {
            inputStr = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            processCommand(inputStr);
            System.out.println(HORIZONTAL_LINE);
        } while (!inputStr.equals("bye"));
    }
}
