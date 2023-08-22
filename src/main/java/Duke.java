import java.util.Scanner;

public class Duke {
    private final String DIVIDER = "\t____________________________________________________________";
    private final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private final String GREETING = "\tHello! I'm Chatty.\n\tWhat can I do for you?";
    private final String FAREWELL = "\tBye. Have \"fun\" in school!";

    public static void main(String[] args) {
        // get new duke instance
        Duke duke = new Duke();

        // introduction
        System.out.println("\nWelcome to Chatty.\n" + duke.LOGO);
        duke.sendGreeting();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println();
            String input = sc.nextLine().trim();

            while (!input.equals("bye")) {
                duke.echoAndDisplayDivider(input);
                System.out.println();
                input = sc.nextLine().trim();
            }
        }
        // say bye
        duke.sendFarewell();
    }

    private void sendGreeting() {
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    private void echoAndDisplayDivider(String message) {
        System.out.println(DIVIDER);
        System.out.println('\t' + message);
        System.out.println(DIVIDER);
    }

    private void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }
}
