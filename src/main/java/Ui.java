import java.util.Scanner;

/**
 * Ui handles the interactions with user.
 *
 * @author Sebastian Tay
 */
public class Ui {
    private Scanner inScanner = new Scanner(System.in);

    public String getUserInput() {
        return inScanner.nextLine();
    }

    /**
     * Terminates inScanner.
     */
    public void terminate() {
        inScanner.close();
    }
}
