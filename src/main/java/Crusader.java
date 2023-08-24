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
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Describes the list of tasks in the chatbot.
     */
    private static void tasksToString() {
        for (int x = 0; x < tasks.size(); x++) {
            System.out.printf("%d. %s\n", x + 1, tasks.get(x).toString());
        }
        addDivider();
    }

    /**
     * Marks a task as done.
     *
     * @param i index of the task to be marked. 1-indexed.
     */
    private static void mark(int i) {
        tasks.get(i - 1).mark();
    }

    /**
     * Unmarks a task.
     *
     * @param i index of the task to be unmarked. 1-indexed.
     */
    private static void unmark(int i) {
        tasks.get(i - 1).unmark();
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
            // Match the command based on the first word of the string
            switch (currentPrompt.contains(" ")
                    ? currentPrompt.split(" ")[0]
                    : currentPrompt) {
                case "bye":
                    notEnded = false;
                    break;
                case "list":
                    tasksToString();
                    break;
                case "mark":
                    int i = Integer.parseInt(currentPrompt.split(" ")[1]);
                    mark(i);
                    break;
                case "unmark":
                    int j = Integer.parseInt(currentPrompt.split(" ")[1]);
                    unmark(j);
                    break;
                default:
                    say("added: " + currentPrompt);
                    tasks.add(new Task(currentPrompt));
                    break;
            }
        }
        farewell();
    }
}
