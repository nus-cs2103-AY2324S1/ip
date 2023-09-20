package kevin.ui;

/**
 * A class responsible to output to the console.
 */
public class Logger {
    public static final String BOT_NAME = "Kevin";
    /**
     * Returns the text wrapped in horizontal lines.
     * @param str
     */
    public String log(String str) {
        return str;
    }

    /**
     * Returns the hello message when the program first runs.
     */
    public static String hello() {
        String welcomeMessage = "Hello! I'm " + BOT_NAME + "!\n" + "What can I do for you?";
        return welcomeMessage;
    }

    /**
     * Returns the bye message before the program ends.
     */
    public String bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        return goodbyeMessage;
    }
}
