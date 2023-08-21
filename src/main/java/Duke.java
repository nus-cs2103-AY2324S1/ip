import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm yourChatBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        try {
            // Future Inputs
            Scanner scanner = new Scanner(System.in);
            String userInput;

            while (true) {
                userInput = scanner.nextLine();
                System.out.println("____________________________________________________________\n"
                        + userInput
                        + "\n"
                        + "____________________________________________________________\n");

                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } finally {
            System.out.println("Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________");
        }
    }
}
