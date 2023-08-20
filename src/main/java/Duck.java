public class Duck {

    /**
     * Default Welcome Message
     */
    private static String WELCOME_MESSAGE = " Quack Quack! I am a duck named Quack\r\n What can I do for you?\r\n";

    /**
     * Default Exit Message
     */
    private static String GOODBYE_MESSAGE = " Quack Quack! Quack hopes to see you again soon!\r\n";

    /**
     * Line Break
     */
    private static String LINE_BREAK = "____________________________________________________________\r\n";

    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        // Welcome Message
        System.out.println(LINE_BREAK + WELCOME_MESSAGE + LINE_BREAK);

        // Good bye Message
        System.out.println(GOODBYE_MESSAGE + LINE_BREAK);
    }
}
