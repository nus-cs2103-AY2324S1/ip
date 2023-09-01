package duke.ui;

/**
 * Deals with interactions with user.
 */
public class Ui {

    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Prints a horizontal line.
     */
    public void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }


    /**
     * Print the welcome message when the user launch the program.
     */
    public void printStart() {
        String name = "BOB";
        printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Print the goodbye message when the user logs out.
     */
    public void printEnd() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you soon again soon!");
        printHorizontalLine();
    }



}
