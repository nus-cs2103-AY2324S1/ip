import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Send welcome message
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm BbabBBB\n" +
                "What can I do for you?\n" +
                "____________________________________________________________");

        // Implement function to read user input via keyboard
        Scanner scan = new Scanner(System.in);

        // Bot exits when user inputs "bye" and echoes when user inputs anything else
        while (true) {
            String userInput = scan.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            } else {
                System.out.println("____________________________________________________________\n" +
                        userInput +
                        "\n____________________________________________________________\n");
            }
        }
        scan.close();
    }
}
