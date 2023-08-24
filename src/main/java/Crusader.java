import java.util.ArrayList;
import java.util.Scanner;

public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String logo =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|";

    /**
     * A list of tasks for the chatbot.
     */
    private static final ArrayList<String> tasks = new ArrayList<>();

    /**
     * Describes the list of tasks in the chatbot.
     */
    private static void tasksToString() {
        for (int x = 0; x < tasks.size(); x++) {
            System.out.printf("%d. %s\n", x + 1, tasks.get(x));
        }
        addDivider();
    }

    /**
     * Displays the logo for the chatbot.
     */
    private static void showLogo() {
        System.out.println(logo);
        addDivider();
    }

    /**
     * Generates a horizontal line to divide parts of the conversation.
     */
    private static void addDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Makes the chatbot say something.
     * Also adds a divider.
     */
    private static void say(String line) {
        System.out.println(line);
        addDivider();
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        say("Hi, I am CRUSADER\nWhat can I do for you?");
    }

    /**
     * Says goodbye to the user.
     */
    private static void farewell() {
        say("Bye!\nHave a great day!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean notEnded = true;
        addDivider();
        showLogo();
        greet();
        while (notEnded) {
            String currentPrompt = sc.nextLine();
            addDivider();
            switch (currentPrompt) {
                case "bye":
                    notEnded = false;
                    break;
                case "list":
                    tasksToString();
                    break;
                default:
                    say("added: " + currentPrompt);
                    tasks.add(currentPrompt);
                    break;
            }
        }
        farewell();
    }
}
