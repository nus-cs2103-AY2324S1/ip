import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String chatbotName = "Albatross\n";
        System.out.print("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");

        // scanner to read user response
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a command");

        String userResponse = scanner.nextLine();
        while (!userResponse.equals("bye")) {
            System.out.println(userResponse);
            userResponse = scanner.nextLine();
        }

        System.out.println("Bye! Hope to see you again soon!");
    }
}
