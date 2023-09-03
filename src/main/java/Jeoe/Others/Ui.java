package Jeoe.Others;

import java.util.Scanner;

/**
 * This class encapsulates the Ui class.
 * The Ui helps to manage the inputs & outputs of the user.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Ui {

    /** Scanner to scan for inputs by user */
    private Scanner scanner;

    /** Constructor for a Ui object. */
    public Ui() {
        scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
    }

    /**
     * Returns the next input line by the user.
     *
     * @return Next input line by the user.
     */
    public String nextLine() {
        return scanner.nextLine();
    }

    /** Displays the opening string of the Jeoe program. */
    public void showOpenString() {
        String openingStr = "____________________________________________________________\n"
                + " Hello! I'm JEOE\n"
                + " What can I do for you?\n"
                + " type :\n"
                + " list => to list out items in storage\n"
                + " _Anything else_ => store in storage\n"
                + "____________________________________________________________\n";
        System.out.println(openingStr);
    }

    /** Displays the ending string of the Jeoe program. */
    public void showEndString() {
        String endStr = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(endStr);
        scanner.close();
    }

    /**
     * Displays an output message to the user base on the input reply.
     *
     * @param reply Input reply by the execution of a command.
     */
    public void displayReply(String reply) {
        System.out.println("____________________________________________________________\n"
                + reply
                + "____________________________________________________________\n");
    }
}
