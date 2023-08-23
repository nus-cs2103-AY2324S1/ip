import java.util.Scanner;

public class Messages {

    public static final String GREET = "Hello! I'm Chatty!\nWhat can I do for you?";
    public static final String EXIT = "Bye. Hope to see you again soon!";

    public static void startChatting() {
        String exitCommand = "bye";

        Scanner scanner = new Scanner(System.in);

        String userCommands;
        do {
            userCommands = scanner.nextLine();
            if (!userCommands.equals(exitCommand)) {
                System.out.println(userCommands);
            }
        } while (!userCommands.equals(exitCommand));

        System.out.println(EXIT);
    }
}
