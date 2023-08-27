import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Rock is the name of and the main program used
 * to run the chatbot for ip.
 * 
 * @author Alvis Ng (supermii2)
 */
public class Rock {
    public static List<Task> taskList;
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
     * Used when message is not the end of
     * interaction.
     * @param words Words to be printed.
     */
    public static void say(String words) {
        System.out.println("\t" + words);
    }
    /**
     * Prints input string given and creates 
     * a line break afterwards. Used to 
     * indicate the end of chatbot output and
     * that user can input again.
     * @param words Words to be printed.
     */
    public static void respond(String words) {
        String response = words + "\n" + LINE_BREAK;
        say(response.replaceAll("\n", "\n\t"));
    }
    /**
     * Subroutine called on startup of chatbot.
     */
    private static void onReady() {
        // Instantiate scanner object
        scanner = new Scanner(System.in);
        //Initiate Task List
        taskList = new ArrayList<>();
        // Intro Message
        say(LOGO);
        respond("How can I help you?"); 
    }
    /**
     * Subroutine called to terminate chatbot.
     */
    public static void terminate() {
        // Sets necessary fields to closed.
        isTerminated = true;
        scanner.close();
        //Exit Message
        respond("Bye. Hope to see you again soon");
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
            Invoker.handle(userInput);
        }
    }
}
