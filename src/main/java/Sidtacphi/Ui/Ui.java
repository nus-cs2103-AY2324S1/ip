package sidtacphi.ui;

/**
 * Ui is the class that interacts with the user.
 */
public class Ui {
    private static final String hello = "Sidtacphi: Hello! \n"
            + "Sidtacphi: What can I do for you?\n";
    private static final String goodbye = "Sidtacphi: Goodbye non-Euclidean life form.";


    private Ui() {
    }


    /**
     * Prints the intro message of the bot.
     */
    public static String getHello() {
        return hello;
    }

    /**
     * Returns the goodbye message of the bot.
     */
    public static String getGoodbye() {
        return goodbye;
    }
}
