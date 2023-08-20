import java.util.Scanner;

public class Dot {
    // TODO: Create new folders utility & views, for constants etc., and UI
    // Constants - Display, UI
    public static String HORIZONTAL_RULE = "_".repeat(60) + "\n";

    @SuppressWarnings("unused")
    public static String LOGO = " ____          _\n"
            + "|  _ \\ _____ _| |___\n"
            + "| | | | | | | | ____|\n"
            + "| |_| | |_| | | |___\n"
            + "|____/ \\___/  \\____/\n";

    // Constants - Utility

    // Class methods - Display, UI
    public static void welcome() {
        System.out.printf("%s%sHello! I'm Dot, " +
                "let me help you finish your tasks on the dot!\n" +
                "What can I do(t) for you?\n%s\n",
                HORIZONTAL_RULE, LOGO, HORIZONTAL_RULE);
    }

    public static void goodbye() {
        System.out.printf("%sBye! DOnT forget to finish your tasks!\n%s",
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
