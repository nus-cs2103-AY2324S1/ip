package sidtacphi.ui;

/**
 * Ui is the class that interacts with the user.
 */
public class Ui {
    private static final String LOGO = " _______  ___   ______   _______  _______  _______  _______  __   __  ___  \n"
                + "|       ||   | |      | |       ||   _   ||       ||       ||  | |  ||   | \n"
                + "|  _____||   | |  _    ||_     _||  |_|  ||       ||    _  ||  |_|  ||   | \n"
                + "| |_____ |   | | | |   |  |   |  |       ||       ||   |_| ||       ||   | \n"
                + "|_____  ||   | | |_|   |  |   |  |       ||      _||    ___||       ||   | \n"
                + " _____| ||   | |       |  |   |  |   _   ||     |_ |   |    |   _   ||   | \n"
                + "|_______||___| |______|   |___|  |__| |__||_______||___|    |__| |__||___| \n";

    private Ui() {
    }


    /**
     * Prints the intro message of the bot.
     */
    public static void printIntro() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + LOGO + "\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message of the bot.
     */
    public static void printGoodbye() {
        System.out.println("\nSidtacphi: Goodbye non-Euclidean life form.");
        System.out.println("\n____________________________________________________________");
    }

    /**
     * Returns the goodbye message of the bot.
     */
    public static String goodbye() {
        return "Sidtacphi: Goodbye non-Euclidean life form.";
    }
}
