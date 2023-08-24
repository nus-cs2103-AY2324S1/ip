import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String name = "Kevin";
    private static final int splitterLength = 50;

    private static void lineSplitter() {
        for (int i = 0; i < Duke.splitterLength; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void greet() {
        lineSplitter();
        System.out.println("Hello! I'm " + Duke.name + "\n" + "What can I do for you?\n");
    }

    private static void bye() {
        lineSplitter();
        System.out.println("Bye. Hope to see you again soon!\n");
        lineSplitter();
    }

    public static void echo(List<Task> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                lineSplitter();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).getTaskStatus() + " " + list.get(i).getTaskName());
                }
                lineSplitter();
                continue;
            }

            if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).setDone();
                lineSplitter();
                System.out.println("Nice! I've marked this task as done:\n" + "  " + list.get(index).getTaskStatus() + " " + list.get(index).getTaskName() + "\n");
                lineSplitter();
                continue;
            }

            if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).setUndone();
                lineSplitter();
                System.out.println("Nice! I've marked this task as undone:\n" + "  " + list.get(index).getTaskStatus() + " " + list.get(index).getTaskName() + "\n");
                lineSplitter();
                continue;
            }
            list.add(new Task(input));
            lineSplitter();
            System.out.println("added: " + input + "\n");
            lineSplitter();
        }
        sc.close();
    }

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        greet();
        echo(list);
        bye();
    }
}
