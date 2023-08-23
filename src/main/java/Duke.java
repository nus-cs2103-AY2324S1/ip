import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    static String softbreak = "--------------------------------------------------";
    static String hardbreak = "——————————————————————————————————————————————————";


    /**
     * Greets the user with a predefined message.
     */
    private static void greet() {
        System.out.println(hardbreak +
                "\n...Fingerprint match found. Verification complete. Welcome home.\n" +
                "PRTS, at your service. What would you like to do today?\n" + hardbreak);
    }

    /**
     * Bids the user farewell with a predefined message.
     */
    private static void exit() {
        System.out.println(hardbreak + "\nFarewell. See you again soon.\n" + hardbreak);
    }


    /**
     * Displays a list of the user's past inputs.
     * @param inputs An array containing the user's inputs.
     */
    private static void list(ArrayList<String> inputs) {

        System.out.println("Here's a log of the your past inputs:");

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println((i + 1) + ". " + inputs.get(i));
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> inputs = new ArrayList<>();

        greet();

        while (true) {
            String input = scanner.next();
            if (Objects.equals(input, "bye")) {
                break;
            }

            System.out.println(softbreak);

            if (Objects.equals(input, "list")) {
                list(inputs);
            } else {
                System.out.println("added: " + input);
                inputs.add(input);
            }

            System.out.println(hardbreak);

        }

        exit();

    }
}
