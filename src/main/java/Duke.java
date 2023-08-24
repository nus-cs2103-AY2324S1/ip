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

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            lineSplitter();
            System.out.println(input + "\n");
            lineSplitter();
        }
        sc.close();
    }

    public static void main(String[] args) {
        greet();
        echo();
        bye();
    }
}
