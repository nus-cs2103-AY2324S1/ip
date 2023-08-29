package zean;

/**
 * The class with methods called by the main class.
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

    public void showError(String msg) {
        System.out.println(msg);
        System.out.println(ERROR_DIVIDER);
    }
}
