/**
 * This class encapsulates my version of Duke, called Rat.
 * @author Keagan
 */
import java.util.Scanner;
import rat.print.RatPrinter;
import rat.storage.*;
import rat.inputs.RatInput;
import rat.inputs.throwables.RatExitThrowable;
public class Rat {
    static RatPrinter ratPrinter = new RatPrinter();
    static RatStorage ratStorage;
    static RatInput ratInput = new RatInput();
    static Scanner sc;

    public static void initialise() {
        ratPrinter.printWelcome();
        ratStorage = new RatStorage();
        sc = new Scanner(System.in);
    }

    public static void exit() {
        ratPrinter.printExit();
    }


    public static void main(String[] args) {
        Rat.initialise();
        try {
            ratInput.handleInput(sc, ratStorage);
        } catch (RatExitThrowable e) {
            Rat.exit();
        } finally {
            sc.close();
        }
    }
}


