/**
 * Rock is the name of and the main program used
 * to run the chatbot for ip.
 * 
 * @author Alvis Ng (supermii2)
 */
import java.util.Scanner;
public class Rock {
    /**
     * Standardised line break used for separate input and output messages.
     */
    public static String LINE_BREAK = "____________________________________________________________";
    /**
     * Logo used on startup.
     */
    private static String LOGO = "\r\n" +
            "__________               __                          \r\n" +
            "\\______   \\ ____   ____ |  | __ _____ _____    ____  \r\n" +
            " |       _//  _ \\_/ ___\\|  |/ //     \\\\__  \\  /    \\ \r\n" +
            " |    |   (  <_> )  \\___|    <|  Y Y  \\/ __ \\|   |  \\\r\n" +
            " |____|_  /\\____/ \\___  >__|_ \\__|_|  (____  /___|  /\r\n" +
            "        \\/            \\/     \\/     \\/     \\/     \\/ \r\n";
    /**
     * Scanner object used for detecting user input.
     */
    private static Scanner scanner;
    /**
     * Field used to determine when to terminate programme.
     */
    public static boolean isTerminated = false;
    /**
     * Prints input string given at an indent.
     * Indented lines are used to identify when
     * the printed string is an input or output.
     * @param words Word to be printed.
     */
    public static void say(String words) {
        System.out.println("\t" + words);
    }
    /**
     * Subroutine called on startup of chatbot.
     */
    private static void onReady() {
        // Instantiate scanner object
        scanner = new Scanner(System.in);
        // Intro Message
        say(LOGO);
        say("How can I help you?"); 
        say(LINE_BREAK);
    }
    /**
     * Subroutine called to terminate chatbot.
     */
    public static void terminate() {
        // Sets necessary fields to closed.
        isTerminated = true;
        scanner.close();
        //Exit Message
        say("Bye. Hope to see you again soon");
        say(LINE_BREAK);
    }
    /**
     * Main Program for running chatbot.
     * @param args
     */
    public static void main(String[] args) {
        onReady();
        while (!isTerminated) {
            String userInput = scanner.nextLine();
            say(LINE_BREAK);
            CommandInvoker.handle(userInput);
        }
    }
}
