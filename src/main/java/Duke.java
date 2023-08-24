import java.util.Scanner;

public class Duke {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        printWithSeparator("Hello! I'm David.\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                printWithSeparator("Bye. Hope to see you again soon!");
                break;
            } else {
                printWithSeparator(input);
            }
        }
    }

    private static void printWithSeparator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }
}
