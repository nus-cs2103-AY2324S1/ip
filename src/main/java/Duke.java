import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        System.out.println("Hello! \n" + logo);

        // Create a scanner object to read commands entered by the user
        Scanner scanner = new Scanner(System.in);

        // Store tasks entered by the user in tasks
        List<String> tasks = new ArrayList<>();

        // Start command loop
        while (true) {
            // Read the next line of input
            String userInput = scanner.nextLine();

            // Exit if user types "bye"
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                for (int i=0; i < tasks.size(); i++) {
                    System.out.println((i+1) + ". " + tasks.get(i));
                }
            } else {
                // Add input to tasks
                tasks.add(userInput);

                // Echo the user's input
                System.out.println("added: " + userInput);
            }
        }
    }
}