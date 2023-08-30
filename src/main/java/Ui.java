/**
 * Class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints a welcome greeting for the user.
     */
    public void printGreetings() {
        showLine();
        System.out.println("Sup bro! I'm Brobot");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Bids farewell to the user.
     */
    public void printExitMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon bro!");
        showLine();
    }

    /**
     * Prints the dukeException message.
     *
     * @param e The dukeException to be printed.
     */
    public void printExceptionMessage(DukeException e) {
        e.printException();
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println("-----------------------------------------------");
    }


}
