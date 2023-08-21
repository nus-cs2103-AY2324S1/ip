import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static String name = "Dook";

    public static void main(String[] args) {

        GreetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.next();
            if (Objects.equals(input, "bye")) {
                BidFarewell();
                break;
            } else {
                PrintMessage(input);
            }
        }
    }

    private static void GreetUser() {
        PrintMessage(String.format("%s here.\nWhat can I do for you?", name));
    }

    public static void PrintMessage(String msg) {
        PrintDivider();
        System.out.println(msg);
        PrintDivider();
    }
    public static void PrintDivider() {
        System.out.println("_______________________________________");
    }
    private static void BidFarewell() {
        PrintMessage("Goodbye.");
    }
}
