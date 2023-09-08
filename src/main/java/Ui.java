/**
 * Ui class that handles the user interface.
 */
public class Ui {
    static final String HORLINE = "_____________________________________________________\n";

    /**
     * Returns the introduction text.
     * 
     * @return String of introduction text.
     */
    static String intro() {
        String logo = "  _____ _            \n" +
                      " / ____| |           \n" +
                      "| |  __| | ___ _ __  \n" +
                      "| | |_ | |/ _ \\  _ \\ \n" +
                      "| |__| | |  __/ | | |\n" +
                      " \\_____|_|\\___|_| |_|\n\n";
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return HORLINE + logo + introText + HORLINE;
    }

    /**
     * Returns the exit text.
     * 
     * @return String of exit text.
     */
    static String exit() {
        return HORLINE + "Bye. Hope to see you again soon!\n" + HORLINE;
    }
}
