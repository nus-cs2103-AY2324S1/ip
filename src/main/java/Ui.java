import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class with methods called by the main class.
 *
 * @author Zhong Han
 */
public class Ui {
    private static final String DIVIDER = "\t__________________________________";

    /**
     * Prints the welcome message.
     *
     * @param name The name of the chatbot.
     */
    public static void greet(String name) {
        printDivider();
        printLogo();
        System.out.println("\tHi there! I'm " + name);
        System.out.println("\tHow can I help you today?");
        printDivider();
    }

    /**
     * Prints the exit message.
     */
    public static void exit() {
        System.out.println("\tBye. Have a nice day!");
        printDivider();
    }

    private static void printDivider() {
        System.out.println(Ui.DIVIDER);
    }

    private static void printLogo() {
        String logo = "\t++      ++      ++\n" +
                "\t||      ||      ||\n" +
                "\t| +----+ |      ||\n" +
                "\t| +----+ |      ||\n" +
                "\t||      ||      ||\n" +
                "\t++      ++      ++\n";
        System.out.println(logo);
    }

    /**
     * Handles the input provided and outputs corresponding messages.
     */
    public static void takeInstructions() throws FileNotFoundException, IOException {
        Storage storage = new Storage();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            printDivider();
            if (input.equals("bye")) {
                break;
            }
            try {
                Parser.parse(input, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            printDivider();
        }
        sc.close();
    }
}
