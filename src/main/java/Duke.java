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
            System.out.println("    " + (++index) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }

    public static void addTask(String task) {
        System.out.println("    added: " + task);
        arr.add(new Task(task));
    }

    public static void markTaskDone(int index) {
        arr.get(index - 1).markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [X] " + arr.get(index - 1).getDescription());
    }

    public static void markTaskNotDone(int index) {
        arr.get(index - 1).markAsNotDone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      [ ] " + arr.get(index - 1).getDescription());
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
