/**
 * This class encapsulates my version of Duke, called Rat.
 * @author Keagan
 */
import java.util.Scanner;
import rat.print.RatPrinter;
import rat.storage.*;
import rat.inputs.RatInput;
import rat.throwables.RatExitThrowable;
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

    public static void exit() {
        RatPrinter.printExit();
    }


    public static void main(String[] args) {
        Rat.initialise();
        try {
            ratInput.handleInput();
        } catch (RatExitThrowable e) {
            Rat.exit();
        } finally {
            sc.close();
        }
    }
}


