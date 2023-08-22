import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        System.out.println("Hello! \n" + logo);

        // Create a scanner object to read commands entered by the user
        Scanner scanner = new Scanner(System.in);

        // Start command loop
        while (true) {
            // Read the next line of input
            String userInput = scanner.nextLine();

            // Exit if user types "bye"
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Echo the user's input
            System.out.println(userInput);
        }
    }
}