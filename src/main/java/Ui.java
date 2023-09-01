import java.util.Scanner;

/**
 * Ui handles the interactions with user.
 *
 * @author Sebastian Tay
 */
public class Ui {
    private Scanner sc;

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Retrieves input from the user in the command line.
     *
     * @return
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Greets the user.
     */
    public void welcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Veda initialised. How may I help you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Terminates the ui and closes the scanner.
     */
    public void exit() {
        System.out.println("Terminating reader.");
        sc.close();
        System.out.println("Bye. All the best for your mission!");
    }
}
