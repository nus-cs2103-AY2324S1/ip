package glen.util;

/**
 * Ui class that handles the user interface.
 */
public class Ui {

    /**
     * Returns the introduction text.
     * 
     * @return String of introduction text.
     */
    public static String intro() {
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return introText;
    }

    /**
     * Returns the exit text.
     * 
     * @return String of exit text.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!\nApplication will close in 5 seconds...";
    }
}
