import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    private static final String greetingMessage = "Hello! I'm EnPassant\n"
                                                + "What can I do for you?\n";
    private static final String exitMessage = "Great heavens! Hope to see you again soon!\n";

    private static void printWithLines(String message) {
        System.out.print(line + message + line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printWithLines(greetingMessage);
        while (true) {
            String str = sc.nextLine();
            if (!str.equals("bye")) {
                printWithLines(str + '\n');
            } else {
                printWithLines(exitMessage);
                break;
            }
        }
    }
}
