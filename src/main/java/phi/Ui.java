package phi;

/**
 * Represents user interactions
 */
public class Ui {
    public void greeting() {
        String logo = " ___ _  _ ___\n" +
                "| _ \\ || |_ _|\n" +
                "|  _/ __ || | \n" +
                "|_| |_||_|___| \n";
        String greetingMsg = "Hellos! I'm PHI (Programmed Human Interaction)\nWhat can I do for you?\n";

        System.out.println(logo + greetingMsg);
    }

    public void goodbye() {
        String exitMsg = "okk THANKS FOR COMING BYE!";
        System.out.println(exitMsg);
    }

    public static String helpMsg() {
        return "Here's a list of commands:\n"
                + "1. list (prints out a list of all tasks)\n"
                + "2. mark X (marks task X in the list)\n"
                + "3. unmark X (unmarks task X in the list])\n"
                + "4. todo [TASK] (creates a to-do with body \"TASK\")\n"
                + "5. deadline [TASK] /by [yyyy-MM-dd] (creates a corresponding deadline with body \"TASK\")\n"
                + "6. event [TASK] /from [yyyy-MM-dd] /to [yyyy-MM-dd]"
                + " (creates a corresponding event with body \"TASK\")\n"
                + "7. find [KEYWORD] (searches for tasks that contain corresponding KEYWORD)"
                + "8. bye (exits the program)";
    }
}
