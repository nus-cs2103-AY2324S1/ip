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
        return "Here's a list of commands:\n" +
                "1. list [prints out a list of all tasks]\n" +
                "2. mark X [marks task X in the list]\n" +
                "3. unmark X [unmarks task X in the list]\n" +
                "4. todo test [creates a to-do with body \"test\"]\n" +
                "5. deadline test /by yyyy-MM-dd [creates a corresponding deadline with body \"test\"]\n" +
                "6. event test /from yyyy-MM-dd /to yyyy-MM-dd [creates a corresponding event with body \"test\"]\n" +
                "7. bye [exits the program]";
    }
}
