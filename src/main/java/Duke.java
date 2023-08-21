import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        horizontalLine();
        System.out.println("Hello! I'm Snow!");
        System.out.println("What can I do for you?");
        horizontalLine();

        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        String userOutput = userInput.nextLine();  // Read user input
        while (!userOutput.equals("bye")) {
            System.out.println(userOutput);  // Output user input
            userOutput = userInput.nextLine();  // Read user input
        }
        printBye();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
