import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "\t__________________________________";

    public static void greet(String name) {
        printDivider();
        printLogo();
        System.out.println("\tHi there! I'm " + name);
        System.out.println("\tHow can I help you today?");
        printDivider();
    }

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

    public static void takeInstructions(Storage storage) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            printDivider();
            if (input.equals("bye")) {
                break;
            }
            Parser.parse(input, storage);
            printDivider();
        }
        sc.close();
    }
}
