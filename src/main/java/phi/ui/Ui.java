package phi.ui;

/**
 * Represents user interactions
 */
public class Ui {
    /**
     * Greeting message procedure
     */
    public static String greeting() {
        String logo = " ___ _  _ ___\n"
                + "| _ \\ || |_ _|\n"
                + "|  _/ __ || | \n"
                + "|_| |_||_|___| \n";
        String greetingMsg = "Wassup nerd! I'm PHI (Programmed Human Interaction)\nWhat can I do for you?\n";

        return logo + "\n" + greetingMsg;
    }

    /**
     * Exit message procedure
     */
    public static String goodbye() {
        return "okay see ya later, nerd";
    }

    /**
     * List of all accepted user commands
     */
    public static String helpMsg() {
        return "Here's a list of stuff I can do:\n"
                + "1. list (prints out a list of all tasks)\n"
                + "2. todo [TASK] (creates a task with body \"TASK\")\n"
                + "3. deadline [TASK] /by [yyyy-MM-dd]\n"
                + "(creates a task with corresponding deadline)\n"
                + "4. event [TASK] /from [yyyy-MM-dd] /to [yyyy-MM-dd]\n"
                + "(creates an event with start and end dates)\n"
                + "5. mark [X] (marks task X in the list)\n"
                + "6. unmark [X] (unmarks task X in the list)\n"
                + "7. delete [X] (removes task X from the list)\n"
                + "8. find [KEYWORD] (searches for tasks that contain corresponding KEYWORD)\n"
                + "9. bye (exits the program)";
    }

}
