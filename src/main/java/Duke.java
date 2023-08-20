import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constructor for Duke
    public Duke() {
        this.list = new ArrayList<>();
    }

    // Constants
    private static final String name = "Beep Boop";
    private static final String line = "─".repeat(100);

    // Fields
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        System.out.println(line);
    }

    public void greet() {
        String greeting = String.format("Hello! I'm %s\n\tWhat can I do for you?\n", name);
        System.out.println(line);
        printMessage(greeting);
    }

    public void exit() {
        String exiting = "Bye. Hope to see you again soon! Beep Boop!\n";
        printMessage(exiting);
    }

    public void addToList(String input) {
        Task task = new Task(input);
        list.add(task);
        String addMessage = String.format("added: %s", input);
        printMessage(addMessage);
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i =0; i < list.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, list.get(i));
        }
        System.out.println(line);
    }

    public void runDuke() {
        greet();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                isDone = true;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.replaceFirst("mark", "").trim()) - 1;
                list.get(index).markDone();
                String markDoneMessage = String.format("\t%s", list.get(index).toString());

                System.out.println("\tBeep Boop Beep! I've marked this task as done:");
                printMessage(markDoneMessage);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.replaceFirst("unmark", "").trim()) - 1;
                list.get(index).markNotDone();
                String markNotDoneMessage = String.format("\t%s", list.get(index).toString());

                System.out.println("\tBeep Boop Boop! I've marked this task as not done yet:");
                printMessage(markNotDoneMessage);
            } else {
                addToList(input);
            }
        }

        exit();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
