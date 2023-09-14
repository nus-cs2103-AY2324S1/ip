package phi;

/**
 * Represents user interactions
 */
public class Ui {
    /**
     * Greeting message procedure
     */
    public void greeting() {
        String logo = " ___ _  _ ___\n"
                + "| _ \\ || |_ _|\n"
                + "|  _/ __ || | \n"
                + "|_| |_||_|___| \n";
        String greetingMsg = "Hellos! I'm PHI (Programmed Human Interaction)\nWhat can I do for you?\n";

        System.out.println(logo + greetingMsg);
    }

    /**
     * Exit message procedure
     */
    public void goodbye() {
        String exitMsg = "okk THANKS FOR COMING BYE!";
        System.out.println(exitMsg);
    }

    /**
     * List of all accepted user commands
     */
    public static String helpMsg() {
        return "Here's a list of commands:\n"
                + "1. list (prints out a list of all tasks)\n"
                + "2. todo [TASK] (creates a to-do with body \"TASK\")\n"
                + "3. deadline [TASK] /by [yyyy-MM-dd] (creates a corresponding deadline with body \"TASK\")\n"
                + "4. event [TASK] /from [yyyy-MM-dd] /to [yyyy-MM-dd]"
                + " (creates a corresponding event with body \"TASK\")\n"
                + "5. mark [X] (marks task X in the list)\n"
                + "6. unmark [X] (unmarks task X in the list)\n"
                + "7. delete [X] (removes task X from the list)\n"
                + "8. find [KEYWORD] (searches for tasks that contain corresponding KEYWORD)\n"
                + "9. bye (exits the program)";
    }
}
