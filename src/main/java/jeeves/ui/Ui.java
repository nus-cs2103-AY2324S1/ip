package jeeves.ui;

/**
 * Ui is responsible for all visual interactions with the user.
 */
public class Ui {

    /**
     * Constructor for a Ui object.
     * Currently, requires no arguments to initialize.
     */
    public Ui() {
        
    }

    /**
     * Prints the greeting message for the user to view.
     */
    public void printGreeting() {
        System.out.println("Greetings, Master. Jeeves at your service");
        System.out.println("How may I serve you today?\n");
    }

    /**
     * Prints the farewell message for the user to view.
     */
    public void printFarewell() {
        System.out.println("I bid you farewell, Master");
    }

    /**
     * Prints the invalid command message for the user to view.
     */
    public void printInvalidCommand() {
        System.out.println("Apologies Master, I am unable to understand that command.\n"
                + "I will improve myself to better serve you in the future.\n");
    }
}
