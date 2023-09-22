package duke;

/**
 * The Ui class handles user interface-related functionality, such as displaying
 * messages, the application logo, and providing an introduction message.
 */
public class Ui {
    private static final String DIVIDER = "________________________________________________________\n";
    private static final String BYE_MESSAGE = "B... b...bye bye!... And ... see you! （//▽//）\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructs a new Ui instance and displays an introduction message.
     */
    public Ui() {
        introduction();
    }

    /**
     * Displays an introduction message when the application starts.
     *
     * @return The introduction message.
     */
    public String introduction() {
        return DIVIDER + "Hello from\n" + LOGO + "Ko...ko...ko..nichi...wa!!! I... I am Gotoh... Hitori desu!\n"
                + "You... can call me... Bocchi. They usually... call me Bocchi chan...\n"
                + "What can... can I do for you?\n" + DIVIDER;
    }

    /**
     * Displays a goodbye message when the user exits the application.
     *
     * @return The goodbye message.
     */
    public static String sayBye(){
        return BYE_MESSAGE;
    }
}
