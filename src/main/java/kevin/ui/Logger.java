package kevin.ui;

/**
 * A class responsible to output to the console.
 */
public class Logger {
    public static final String BOT_NAME = "Kevin";
    /**
     * Prints the text wrapped in horizontal lines.
     * @param str
     */
    public String log(String str) {
        return str;
    }

    /**
     * Prints the hello message when the program first runs.
     */
    public static String hello() {
        String welcomeMessage = "Hello! I'm " + BOT_NAME + "!\n" + "What can I do for you?";
        return welcomeMessage;
    }

    /**
     * Prints the bye message before the program ends.
     */
    public String bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        this.log(goodbyeMessage);
        return goodbyeMessage;
    }
}
