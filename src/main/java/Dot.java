import java.util.Scanner;

public class Dot {
    // TODO: Create new folders utility & views, for constants etc., and UI
    // Constants - Display, UI
    public static String HORIZONTAL_RULE = "_".repeat(60) + "\n";

    @SuppressWarnings("unused")
    public static String logo = " ____          _\n"
            + "|  _ \\ _____ _| |___\n"
            + "| | | | | | | | ____|\n"
            + "| |_| | |_| | | |___\n"
            + "|____/ \\___/  \\____/\n";

    // Constants - Utility

    // Class methods - Display, UI
    public static void welcome() {
        System.out.printf("%sHello! I'm Dot\n" +
                "What can I do for you?\n", HORIZONTAL_RULE);
    }

    public static void goodbye() {
        System.out.printf("%sBye. Hope to see you again soon!\n%s",
                HORIZONTAL_RULE, HORIZONTAL_RULE);
    }

    public static void echo(String msg) {
        System.out.printf("%s%s\n%s\n", HORIZONTAL_RULE, msg, HORIZONTAL_RULE);
    }

    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            }
            echo(input);
        }
        goodbye();
    }
}
