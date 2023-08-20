import java.util.Locale;
import java.util.Scanner;
public class Duke {
    static String HORIZONTAL_LINE = "    ________________________________________"; //40 underscores.
    static String INDENT = "    "; //4 spaces.

    /**
     * Function to greet the User.
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Hello! I'm SeeWhyAre Bot!");
        System.out.println("    What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to say goodbye to the User and end the program.
     */
    public static void farewell() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    You are closing the SeeWhyAre chat bot.");
        System.out.println("    Bye bye. Please use me again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void echo(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(String.format("    %s", input));
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        greet();
        try (Scanner scanner = new Scanner(System.in)) {
            Boolean repeatFlag = true;
            while (repeatFlag) {
                String userInput = scanner.nextLine();
                String formattedInput = userInput.toLowerCase();
                if (formattedInput.equals("bye")) {
                    farewell();
                    repeatFlag = false;
                } else {
                    echo(userInput);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid command! Please enter valid commands");
            System.out.println(HORIZONTAL_LINE);
        }


    }
}

/**
 * String logo = " ____        _        \n"
 *                 + "|  _ \\ _   _| | _____ \n"
 *                 + "| | | | | | | |/ / _ \\\n"
 *                 + "| |_| | |_| |   <  __/\n"
 *                 + "|____/ \\__,_|_|\\_\\___|\n";
 *         System.out.println("Hello from\n" + logo);
 */
