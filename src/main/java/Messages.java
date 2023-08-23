import java.util.Scanner;
import java.util.ArrayList;

public class Messages {

    public static final String GREET = "Hello! I'm Chatty!\nWhat can I do for you?";
    public static final String EXIT = "Bye. Hope to see you again soon!";
    private static ArrayList<String> commandsList = new ArrayList<>();

    public static void startChatting() {
        String exitCommand = "bye";
        String listCommand = "list";

        Scanner scanner = new Scanner(System.in);

        String userCommands;
        do {
            userCommands = scanner.nextLine();
            if (!userCommands.equalsIgnoreCase(exitCommand)) {
                if (userCommands.equalsIgnoreCase(listCommand)) {
                    listOutCommand();
                } else {
                    addToList(userCommands);
                }
            }
        } while (!userCommands.equalsIgnoreCase(exitCommand));

        System.out.println(EXIT);
    }

    private static void listOutCommand() {
        if (commandsList.isEmpty()) {
            System.out.println("There is currently no command in the list.");
        } else {
            System.out.println("Here is the list of commands:");
            for (int i = 0; i < commandsList.size(); i++) {
                System.out.println((i + 1) + ". " + commandsList.get(i));
            }
        }
    }

    private static void addToList(String command) {
        commandsList.add(command);
        System.out.println("I have added \"" + command + "\" to the list. ");
    }
}
