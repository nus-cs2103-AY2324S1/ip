import java.util.Scanner;

public class Duke {
    private static String divider = "____________________________________________________________";
    private static String indent = "     ";

    public static void printDivider() {
        System.out.println(indent + divider);
    }

    public static void greet() {
        String chatbotName = "Miles";

        printDivider();
        System.out.println(indent + "Hey! I'm " + chatbotName + "!");
        System.out.println(indent + "What can I do for you, my friend?");
        printDivider();
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            
            if (!input.equals("bye")) {
                printDivider();
                System.out.println(indent + " " + input);
                printDivider();
            } else {
                break;
            }
        }
    }

    public static void exit() {
        printDivider();
        System.out.println(indent + "Stay safe my friend. Hope to see you again soon man.");
        printDivider();
    }
    public static void main(String[] args) {
        Duke.greet();
        Duke.echo();
        Duke.exit();
    }
}
