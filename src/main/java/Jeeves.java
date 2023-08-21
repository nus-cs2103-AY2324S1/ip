import java.util.Scanner;

public class Jeeves {

    /**
     * Main process.
     * Greets the user and waits for user input.
     *
     * @param args Optional command line arguments.
     */
    public static void main (String[] args) {
        System.out.println("Greetings, Master. Jeeves at your service");
        System.out.println("How may I serve you today?\n");

        // Waits for user input and process it accordingly
        while (true) {
            Scanner sc = new Scanner(System.in);
            // Reads the user input
            String currentCommand = sc.nextLine();
            // Performs a different action depending on the input received
            // By default the program always waits for another command unless
            // the termination command is received.
            switch (currentCommand) {
            case "bye":
                // Displays the farewell message and terminates the application
                System.out.println("I bid you farewell, Master");
                System.exit(0);
            default :
                // By default, echo the command then wait for the next command
                System.out.println(currentCommand + "\n");
                break;
            }
        }
    }
}
