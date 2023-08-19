import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] echoList = new String[100];
        int counter = 0;

        // Greet user
        System.out.println("Hello I'm Oscar! Your friendly chatbot :)");
        System.out.println("What can I do for you?");
        System.out.println();

        while (true) {
            // Obtain command entered by user
            String userInput = scanner.nextLine();
            // Exit programme if user enters "bye" command
            if (Objects.equals(userInput, "bye")) {
                System.out.println("Goodbye for now. " +
                        "Hope to see you again soon!");
                break;
            } else if (Objects.equals(userInput, "list")) {
                // Display text stored by user in chronological order if
                // user enters "list" command
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ". " + echoList[i - 1]);
                }
                System.out.println();
            } else {
                // Store user's command in echoList otherwise
                echoList[counter] = userInput;
                counter++;
                System.out.println("Oscar added: " + userInput);
                System.out.println();
            }
        }
    }
}
