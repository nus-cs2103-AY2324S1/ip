/**
 * This class encapsulates my version of Duke, called Rat.
 * @author Keagan
 */
import java.util.Scanner;
import rat.print.RatPrinter;
import rat.storage.*;
public class Rat {
    static RatPrinter ratPrinter = new RatPrinter();
    static RatStorage ratStorage;
    static Scanner sc;

    public static void initialise() {
        ratPrinter.printWelcome();
        ratStorage = new RatStorage();
        sc = new Scanner(System.in);
    }

    public static void exit() {
        ratPrinter.printExit();
    }

    public static void handleInputs() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                ratStorage.listItems();
            } else {
                ratStorage.addItem(new Task(input));
            }
            input = sc.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        Rat.initialise();
        Rat.handleInputs();
    }
}


