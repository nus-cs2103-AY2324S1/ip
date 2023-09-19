package duke;
import java.util.Scanner;

/**
 * Interface that users interact with.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints a goodbye message when users close the bot.
     */
    public String bidGoodbye() {
        return "Finally. Don't come back!";
    }


    /**
     * Prints lines to inform user that there are no saved tasks in the data.
     */
    public void showLoadingError() {
        System.out.println("Unable to find tasks in saved data.");
        System.out.println("Creating new data file with empty list of tasks.");
        System.out.println("Aren't you glad you have me to keep track of everything your tiny mind can't?");
    }

}
