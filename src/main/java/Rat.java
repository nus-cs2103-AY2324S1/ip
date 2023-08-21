/**
 * This class encapsulates my version of Duke, called Rat.
 * @author Keagan
 */
import java.util.Scanner;
import rat.print.RatPrinter;
import rat.storage.*;
import rat.inputs.RatInput;
public class Rat {
    static RatStorage ratStorage;
    static RatInput ratInput;
    static Scanner sc;

    public static void initialise() {
        RatPrinter.printWelcome();
        ratStorage = new RatStorage();
        sc = new Scanner(System.in);
        ratInput = new RatInput(sc, ratStorage);
    }

    public static void main(String[] args) {
        Rat.initialise();
        ratInput.handleInput();
        sc.close();
    }
}


