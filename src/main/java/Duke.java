import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "________________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(horizontalLine);
            System.out.println(" " + userInput);
            System.out.println(horizontalLine);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }
        }
        scanner.close();
    }
}
