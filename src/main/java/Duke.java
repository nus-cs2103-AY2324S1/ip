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

    public static void echo(List<String> list) {
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
            list.add(input);
            lineSplitter();
            System.out.println("added: " + input + "\n");
            lineSplitter();
        }
        sc.close();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        greet();
        echo(list);
        bye();
    }
}
