import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String divider = "____________________________________________________________";
    private static String indent = "     ";
    private static ArrayList<String> commands = new ArrayList<>();

    public static void formatString(String s) {
        System.out.println(indent + s);
    }

    public static void printDivider() {
        formatString(divider);
    }

    public static void greet() {
        String chatbotName = "Miles";

        printDivider();
        formatString("Hey! I'm " + chatbotName + "!");
        formatString("What can I do for you, my friend?");
        printDivider();
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String add = "added: ";

        while (true) {
            String input = scanner.nextLine();
            
            if (!input.equals("bye") && !input.equals("list")) {
                // store each item in the arraylist
                commands.add(input);
                String output = " " + add + input;
                printDivider();
                formatString(output);
                printDivider();
            } else if (input.equals("list")) {
                printList();
            } else {
                break;
            }
        }
    }

    public static void printList() {
        int n = commands.size();

        printDivider();
        for (int i = 0; i < n; i += 1) {
            String currentCommand = commands.get(i);
            int currentNum = i + 1;
            String output = " " + currentNum + ". " + currentCommand;
            formatString(output);
        }
        printDivider();
    }

    public static void exit() {
        printDivider();
        formatString("Stay safe my friend. Hope to see you again soon man.");
        printDivider();
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.echo();
        Duke.exit();
    }
}
