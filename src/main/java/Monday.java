import java.util.Scanner;

public class Monday {
    public static void main(String[] args) {
        String chatBotName = "Monday";
        Scanner scanner = new Scanner(System.in);

        greet(chatBotName);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                exit();
                break;
            }
            echo(userInput);
        }
    }

    private static void echo(String word) {
        printSeparator();
        System.out.println(word);
        printSeparator();
    }

    private static void greet(String chatBotName) {
        printSeparator();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        printSeparator();
    }

    private static void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
