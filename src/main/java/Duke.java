import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static ArrayList<String> lists = new ArrayList<String>();

    public static final String NAME = "Duke";

    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        Boolean isRunning = true;
        while (isRunning) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    printList();
                    break;
                case "bye":
                    isRunning = false;
                    break;
                default:
                    addToList(input);
                    break;
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
        for (int i = 0; i < lists.size(); i++) {
            printWithIndentation((i + 1) + ". " + lists.get(i));
        }
        printWithIndentation(HORIZONTAL_LINE);
    }

    public static void addToList(String input) {
        printWithIndentation(HORIZONTAL_LINE);
        printWithIndentation("added: " + input);
        lists.add(input);
        printWithIndentation(HORIZONTAL_LINE);
    }

    private static void printWithIndentation(String input) {
        System.out.println("    " + input);
    }
}
