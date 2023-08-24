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

    private static Task addTask(String input) {
        String[] splittedInput = input.split(" ");
        if (splittedInput[0].equalsIgnoreCase("todo")) {
            return new ToDo (input.substring(5));
        }
        if (splittedInput[0].equalsIgnoreCase("deadline")) {
            return new Deadline(input.substring(9).split(" /by ")[0], input.substring(9).split(" /by ")[1]);
        }
        if (splittedInput[0].equalsIgnoreCase("event")) {
            return new Event(input.substring(6).split(" /from ")[0], input.substring(6).split(" /from ")[1].split(" /to ")[0], input.substring(6).split(" /from ")[1].split(" /to ")[1]);
        }
        return null;
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
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                lineSplitter();
                continue;
            }

            if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).setDone();
                lineSplitter();
                System.out.println("Nice! I've marked this task as done:\n" + "  " + list.get(index) + "\n");
                lineSplitter();
                continue;
            }

            if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).setUndone();
                lineSplitter();
                System.out.println("Nice! I've marked this task as undone:\n" + "  " + list.get(index) + "\n");
                lineSplitter();
                continue;
            }
            list.add(addTask(input));
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
