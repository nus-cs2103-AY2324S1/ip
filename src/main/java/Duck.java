import java.util.Scanner; // Import the Scanner class

public class Duck {

    /**
     * Default tab spacing
     */
    private static String TAB = "     ";
    /**
     * Default Welcome Message
     */
    private static String WELCOME_MESSAGE = TAB + " Quack Quack! I am a duck named Quack\r\n"
            + TAB + " What can I do for you?\r\n";

    /**
     * Default Exit Message
     */
    private static String GOODBYE_MESSAGE = TAB + " Quack Quack! Quack hopes to see you again soon!\r\n";

    /**
     * Line Break
     */
    private static String LINE_BREAK = "    ____________________________________________________________\r\n";

    /**
     * App LOGO
     */
    private static String LOGO = "\r\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n"
            +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░████░░██████████░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░████░░██▒▒▒▒▒▒██░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░██░░██░░░░░░░░██░░░░░░░░░░░░░░██▒▒▒▒▒▒██░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░██░░░░██░░░░░░██░░░░░░░░░░░░░░████████░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░██░░░░░░██░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░████████████░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░████████████████░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\r\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░";

    public static void main(String[] args) {
        new Duck().run();
    }

    /**
     * Entry point of the software
     */
    public void run() {
        // Welcome Message
        System.out.println(Duck.LOGO);
        System.out.println(Duck.LINE_BREAK + Duck.WELCOME_MESSAGE + Duck.LINE_BREAK);

        this.collectCommand();

        // Good bye Message
        System.out.println(Duck.LINE_BREAK + Duck.GOODBYE_MESSAGE + Duck.LINE_BREAK);
    }

    /**
     * Handles the collection of the command
     */
    public void collectCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println(Duck.LINE_BREAK);
            this.handleInput(input);
            System.out.println(Duck.LINE_BREAK);
            input = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Handles the execution of command
     * 
     * @param command - the command being executed
     */
    public void handleInput(String command) {
        System.out.println(Duck.TAB + command);
    }

}
