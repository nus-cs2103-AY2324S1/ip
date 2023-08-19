import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet user
        System.out.println("Hello I'm Oscar! Your friendly chatbot :)");
        System.out.println("What can I do for you?");
        System.out.println();

        while (true) {
            // Obtain command entered by user
            String userInput = scanner.nextLine();
            // Exit programme if user enters bye command
            if (Objects.equals(userInput, "bye")) {
                System.out.println("Goodbye for now. Hope to see you again soon!");
                break;
            }
            // Echo user's command otherwise
            System.out.println("Oscar says " + userInput);
            System.out.println();
        }
    }
}
