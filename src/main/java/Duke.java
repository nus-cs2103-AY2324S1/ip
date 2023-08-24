import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String NAME = "Duke";

    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            echo(input);
        }

        bye();

        sc.close();
    }

    public static void hello() {
        printWithIndentation(HORIZONTAL_LINE);
        System.out.println(LOGO);
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

    private static void printWithIndentation(String input) {
        System.out.println("    " + input);
    }
}
