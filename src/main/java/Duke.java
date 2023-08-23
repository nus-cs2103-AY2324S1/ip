import java.util.Scanner;
public class Duke {
    private static String name = "WallE";
    public static void printDivider(boolean isIndented) {
        if (isIndented)
            System.out.println('\t' + "_________________________________________");
        else
            System.out.println("_________________________________________");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printDivider(true);
        System.out.println("\tHello! I'm " + name + "!");
        System.out.println("\tWhat can I do for you?");
        printDivider(true);
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                printDivider(true);
                System.out.println('\t' + input);
                printDivider(true);
            } else {
                break;
            }
        } while (true);

        printDivider(true);
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider(true);
    }
}
