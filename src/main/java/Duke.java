import java.util.Objects;
import java.util.Scanner;

public class Duke {

    static String softbreak = "--------------------------------------------------";
    static String hardbreak = "——————————————————————————————————————————————————";


    private static void greet() {
        System.out.println(hardbreak +
                "\n——Fingerprint match found. Verification complete. Welcome home.\n" +
                "PRTS, at your service. What would you like to do today?\n" + hardbreak);
    }

    private static void exit() {
        System.out.println(hardbreak + "\nFarewell. See you again soon.\n" + hardbreak);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input;

        greet();

        while (true) {
            input = scanner.next();
            if (Objects.equals(input, "bye")) {
                break;
            }
            System.out.println(softbreak);
            System.out.println(input);
            System.out.println(hardbreak);
        }

        exit();

    }
}
