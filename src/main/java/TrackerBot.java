import ip.utils.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Program for the application. <br>
 * As of Level-0, this has been renamed from Duke to TrackerBot
 * as part of the requirements for the iP.
 * Level-5 was skipped as TrackerBot had been handling error messages throughout its lifespan.
 *
 * @author WZWren
 * @version Level-6
 */
public class TrackerBot {
    /** Name of the app. **/
    private static final String APP_NAME = "TrackerBot";

    /** Line separators for the console between paragraphs. **/
    private static final String FORMAT_LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private static TaskList taskList;

    /**
     * Task Array - as TrackerBot is not instantiated, this must be static.
     * The Task List array itself should be immutable, in case we override it
     * during runtime.
     */
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Greet function of the app. <br>
     * Prints a welcome message to the user on login.
     */
    private static void greet() {
        System.out.println(FORMAT_LINE);
        System.out.println("Greetings from " + APP_NAME + "!");
        System.out.println("How may I assist?");
        System.out.println(FORMAT_LINE);
    }

    /**
     * Exit function of the app. <br>
     * Prints an exit message to the user on logout.
     */
    private static void exit() {
        System.out.println("Thank you for using " + APP_NAME + ". Goodbye.");
    }

    /**
     * Input handler function of the app. <br>
     * Takes in a user input, and acts upon the input based on what input it gets. <br>
     * <ul>
     *   <li>If the input is "bye", exits the program by returning true.</li>
     *   <li>If the input is "list", prints the list.</li>
     *   <li>If the input is "mark X", marks the item at index X.</li>
     *   <li>If the input is "unmark X", unmarks the item at index X.</li>
     *   <li>If the input is "delete X", deletes the item at index X.</li>
     *   <li>If the input is one of the add task inputs, adds the item to the list.</li>
     * </ul>
     * @param str The input string that is given to the method.
     * @return true if the handler detects the bye keyword,
     *         false otherwise.
     */
    private static boolean handleInput(String str) {
        Pair<CommandType, String> input = Parser.parseCommand(str);
        Scanner scanner = new Scanner(input.getSecond());

        System.out.println(FORMAT_LINE);
        try {
            // switch used for now: to handle future input cases.
            switch (input.getFirst()) {
            case BYE:
                exit();
                return true;
            // No break - return exits case immediately.
            case LIST:
                taskList.list();
                break;
            case MARK:
                if (scanner.hasNextInt()) {
                    taskList.markTask(scanner.nextInt());
                } else {
                    throw new TrackerBotException("Compulsory parameter for mark should be a number.");
                }
                break;
            case UNMARK:
                if (scanner.hasNextInt()) {
                    taskList.unmarkTask(scanner.nextInt());
                } else {
                    throw new TrackerBotException("Compulsory parameter for unmark should be a number.");
                }
                break;
            case DELETE:
                if (scanner.hasNextInt()) {
                    taskList.delete(scanner.nextInt());
                } else {
                    throw new TrackerBotException("Compulsory parameter for delete should be a number.");
                }
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                taskList.add(input);
                break;
            default:
                throw new TrackerBotException("I could not recognise that keyword. Try another?");
            }
        } catch (TrackerBotException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(FORMAT_LINE);
            scanner.close();
        }
        return false;
    }

    public static void main(String[] args) {
        taskList = new TaskList();
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isBye;
        do {
            // scanner.nextLine() blocks the main thread.
            System.out.print("Format :: [keyword] [parse string] | ");
            input = scanner.nextLine();
            isBye = handleInput(input);
        } while (!isBye);
    }
}
