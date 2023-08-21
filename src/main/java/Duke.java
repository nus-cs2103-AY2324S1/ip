import java.util.ArrayList;
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
        ArrayList<String> inputList = new ArrayList<>();
        while (!userOutput.equals("bye")) {
            if (userOutput.equals("list")) {
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(i+1 + ". " + inputList.get(i));
                }
            } else {
                System.out.println("added: " + userOutput);  // Output user input
                inputList.add(userOutput);
            }
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
