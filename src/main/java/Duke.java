import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Initiate Scanner to handle user input
        Scanner scanner = new Scanner(System.in);

        // Introduction Message
        System.out.println(logo);
        System.out.println("Hello! I'm Chad");
        System.out.println("What can I do for you?");
        System.out.println();

        // Echoing User Input
        while (true) {
            String userInput = scanner.nextLine();

            // If user types bye, goodbye message is printed without echoing the command
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }
            else {
                // Command is echoed if command is not "bye"
                System.out.println(userInput);
            }
        }
        scanner.close();
    }
}
