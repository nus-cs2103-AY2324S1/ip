import java.util.Scanner;
public class ChatBuddy {
    private static void printHorizontalLine() {
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHorizontalLine();
        System.out.println("    Hello! I'm Chat Buddy!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            printHorizontalLine();
            System.out.println("    " + userInput);
            printHorizontalLine();
            userInput = scanner.nextLine();
        }

        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
