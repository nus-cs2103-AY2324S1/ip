import java.util.Scanner;

public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * Prints greeting to the user and returns.
     */
    private static void printGreeting() {
        System.out.println("(^O^)／ Heya~");
        System.out.println("I am ~~Duchess~~!!");
        System.out.println("What can I do for you!! o_O");
    }

    /**
     * Returns true if the command would cause Duchess to exit operations.
     *
     * @param s - the command to check for exit operations
     */
    private static boolean isExitCommand(String s) {
        return s.toLowerCase().equals("bye");
    }

    /**
     * Prints farewell/exit message.
     */
    private static void printFarewell() {
        System.out.println("Bye bye!! Hope to see you again (＾▽＾)");
    }

    /**
     * Outputs the same string parameter that was passed in.
     *
     * @param s - the string to be echoed.
     */
    private static void echo(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Duchess.printGreeting();

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while(!Duchess.isExitCommand(userInput)) {
            userInput = sc.nextLine();

            // Check if this command is an exit.
            if(Duchess.isExitCommand(userInput)) {
                break;
            }

            // Otherwise, echo the command back.
            Duchess.echo(userInput);
        }
        
        Duchess.printFarewell();
    }
}
