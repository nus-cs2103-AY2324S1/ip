/**
 * This class encapsulates my version of Duke, called Rat.
 * @author Keagan
 */
import java.util.Scanner;
import rat.print.Printer;

public class Rat {
    static Printer ratPrinter = new Printer();

    public static void initialise() {
        ratPrinter.printWelcome();
    }

    public static void exit() {
        ratPrinter.printExit();
    }

    public static void main(String[] args) {
        initialise();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else {
                ratPrinter.printWithLines(input);
            }
        }
    }
}


