package zean;

/**
 * The class which deals with interactions with the user.
 *
 * @author Zhong Han
 */
public class Ui {
    private static final String DIVIDER = "\t__________________________________";
    private static final String ERROR_DIVIDER = "\t**********************************";

    public Ui() {

    }

    /**
     * Prints the welcome message.
     *
     * @param name The name of the chatbot.
     */
    public void greet(String name) {
        printDivider();
        printLogo();
        System.out.println("\tHi there! I'm " + name);
        System.out.println("\tHow can I help you today?");
        printDivider();
    }

    /**
     * Prints the exit message.
     */
    public void exit() {
        System.out.println("\tBye. Have a nice day!");
        printDivider();
    }

    /**
     * Prints the normal horizontal divider.
     */
    public void printDivider() {
        System.out.println(Ui.DIVIDER);
    }

    private void printLogo() {
        String logo = "\t++      ++      ++\n" +
                "\t||      ||      ||\n" +
                "\t| +----+ |      ||\n" +
                "\t| +----+ |      ||\n" +
                "\t||      ||      ||\n" +
                "\t++      ++      ++\n";
        System.out.println(logo);
    }

    /**
     * Prints the error message with a horizontal error divider.
     *
     * @param msg The error message to be printed.
     */
    public void showError(String msg) {
        System.out.println(msg);
        System.out.println(ERROR_DIVIDER);
    }
}
