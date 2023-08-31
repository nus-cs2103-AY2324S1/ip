import ip.utils.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        read();
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
        save();
        System.out.println("Thank you for using " + APP_NAME + ". Goodbye.");
    }

    /**
     * List function of the app. <br>
     * Prints the list of tasks in the bot.
     */
    private static void list() {
        // happy path: prints an appropriate message and exit the method.
        if (TASKS.size() == 0) {
            System.out.println("No tasks have been added to the list yet.");
            return;
        }

        System.out.println("I am tracking these tasks:");
        for (int i = 1; i < TASKS.size() + 1; i++) {
            System.out.println(i + ". " + TASKS.get(i - 1));
        }
    }

    /**
     * Function that adds a task to the app. <br>
     * Adds a To-Do, Event or Deadline task to the task list.
     * @param input The Pair&lt;Command, String&gt; of the task to add to the list.
     */
    private static void add(Pair<Command, String> input) {
        Task newTask;
        String[] segments;
        switch(input.getFirst()) {
        case TODO:
            if (input.getSecond().equals("")) {
                System.out.println("I can't track a task that doesn't have a description!");
                return;
            }
            newTask = new Todo(input.getSecond());
            break;
        case DEADLINE:
            segments = input.getSecond().split("/by");
            if (segments.length < 2) {
                System.out.println("Use 1 /by flag in the argument, followed by the deadline.");
                System.out.println("Format: deadline [description] /by [end-date]");
                return;
            } else if (segments.length > 2) {
                System.out.println("Too many flags specified! Use only 1 /by flag.");
                System.out.println("Format: deadline [description] /by [end-date]");
                return;
            }

            if (segments[0].trim().equals("")) {
                System.out.println("I can't track a task that doesn't have a description!");
                return;
            }
            if (segments[1].trim().equals("")) {
                System.out.println("Specify a deadline please.");
                return;
            }

            newTask = new Deadline(segments[0].trim(), segments[1].trim());
            break;
        case EVENT:
            // this will check for the standard format, and will also guarantee that segment length is min 3.
            if (!input.getSecond().matches("^.+ /from .+ /to .+")) {
                System.out.println("event is not used in the correct format.");
                System.out.println("Format: event [description] /from [start-date] /to [end-date]");
                return;
            }

            segments = input.getSecond().split("/from|/to");
            if (segments.length > 3) {
                System.out.println("Too many flags specified for event!");
                System.out.println("Format: event [description] /from [start-date] /to [end-date]");
                return;
            }

            if (segments[0].trim().equals("")) {
                System.out.println("I can't track a task that doesn't have a description!");
                return;
            }
            if (segments[1].trim().equals("")) {
                System.out.println("Specify a start date please.");
                return;
            }
            if (segments[2].trim().equals("")) {
                System.out.println("Specify an end date please.");
                return;
            }

            newTask = new Event(segments[0].trim(), segments[1].trim(), segments[2].trim());
            break;
        default:
            System.out.println("That wasn't supposed to happen...");
            return;
        }
        TASKS.add(newTask);

        System.out.println("Now tracking: \n  " + newTask);
    }

    /**
     * Helper function for mark - checks if the index is valid.
     * @param index The index of the list to check.
     * @return The Task object at the index, if it exists, and null otherwise.
     */
    private static Task getTask(int index) {
        // happy path: return null if out of bounds.
        if (index <= 0 || index > TASKS.size()) {
            return null;
        }
        return TASKS.get(index - 1);
    }

    /**
     * Mark function for the app. <br>
     * Attempts to mark the item in the task list as completed. If the Task is already
     * completed, or the Task does not exist, prints an appropriate error message.
     * @param index The index of the list to mark.
     */
    private static void mark(int index) {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            System.out.println("That task is not on the list!");
            System.out.println("Use \"list\" to display what I am currently tracking.");
            return;
        }

        if (task.markTask()) {
            System.out.println("This task has been marked as completed.\n  " + task);
        } else {
            System.out.println("This task has already been completed!\n  " + task);
        }
    }

    /**
     * Unmark function for the app. <br>
     * Attempts to unmark the item in the task list as incomplete. If the Task is already
     * incomplete, or the Task does not exist, prints an appropriate error message.
     * @param index The index of the list to unmark.
     */
    private static void unmark(int index) {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            System.out.println("That task is not on the list!");
            System.out.println("Use \"list\" to display what I am currently tracking.");
            return;
        }

        if (task.unmarkTask()) {
            System.out.println("The task has been marked as incomplete.\n  " + task);
        } else {
            System.out.println("This task is already in progress.\n  " + task);
        }
    }

    /**
     * Delete function for the app. <br>
     * Attempts to delete the item in the task list. If the Task does not exist,
     * prints an appropriate error message.
     * @param index The index of the list to unmark.
     */
    private static void delete(int index) {
        Task task = getTask(index);
        // happy path: the task does not exist.
        if (task == null) {
            System.out.println("That task is not on the list!");
            System.out.println("Use \"list\" to display what I am currently tracking.");
            return;
        }

        TASKS.remove(index - 1);
        System.out.println("I have removed this task off of my list.\n  " + task);
        System.out.println(TASKS.size() + " task(s) remain on my list.");
    }

    private static void read() {
        Path path = Paths.get("TrackerBot", "data.txt");
        if (Files.notExists(path)) {
            return;
        }

        try (Scanner input = new Scanner(new FileReader(path.toFile()))){
            while (input.hasNextLine()) {
                TASKS.add(Task.parseSaveLine(input.nextLine()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void save() {
        Path path = Paths.get("TrackerBot", "data.txt");
        File file = path.toFile();
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileOutputStream output = new FileOutputStream(file, false)) {
            for (int i = 1; i < TASKS.size() + 1; i++) {
                output.write(TASKS.get(i - 1).toSaveString().getBytes());
                output.write("\n".getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } // the try with resources statement auto-closes output.
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
        Pair<Command, String> input = Command.parse(str);
        Scanner scanner = new Scanner(input.getSecond());

        System.out.println(FORMAT_LINE);
        // switch used for now: to handle future input cases.
        switch(input.getFirst()) {
        case BYE:
            exit();
            return true;
            // No break - return exits case immediately.
        case LIST:
            list();
            break;
        case MARK:
            if (scanner.hasNextInt()) {
                mark(scanner.nextInt());
            } else {
                System.out.println("Compulsory parameter for mark should be a number.");
            }
            break;
        case UNMARK:
            if (scanner.hasNextInt()) {
                unmark(scanner.nextInt());
            } else {
                System.out.println("Compulsory parameter for unmark should be a number.");
            }
            break;
        case DELETE:
            if (scanner.hasNextInt()) {
                delete(scanner.nextInt());
            } else {
                System.out.println("Compulsory parameter for delete should be a number.");
            }
            break;
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            add(input);
            break;
        default:
            System.out.println("I could not recognise that keyword. Try another?");
        }
        System.out.println(FORMAT_LINE);
        scanner.close();
        return false;
    }

    public static void main(String[] args) {
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

    /**
     * Enum for the commands that a user can use.
     */
    public enum Command {
        /** Command to exit the program. **/
        BYE ("bye"),
        /** Command to list all tasks in the task list. **/
        LIST ("list"),
        /** Command to add a new to-do task to the task list. **/
        TODO ("todo"),
        /** Command to add a new deadline task to the task list. **/
        DEADLINE ("deadline"),
        /** Command to add a new event task to the task list. **/
        EVENT ("event"),
        /** Command to mark a task to be complete. **/
        MARK ("mark"),
        /** Command to mark a task as incomplete. **/
        UNMARK ("unmark"),
        /** Command to delete a task. **/
        DELETE ("delete"),
        /** Command to denote an unknown keyword call. **/
        UNKNOWN ("");

        /** The String representation of the enum. Used to parse the command into enum. **/
        private final String keyword;

        /**
         * Constructor for the enum Command. <br>
         * Enum constructors are implicitly private, so the tag is not included.
         * @param keyword The keyword of the task.
         */
        Command(String keyword) {
            this.keyword = keyword;
        }

        /**
         * Helper function. Splits the console input string into the invoking keyword
         * and its description after the invoking keyword. <br>
         * If no description exists after the keyword, an empty string is returned in the
         * second half of the Pair structure.
         *
         * @param input The unmodified console string that the user inputs.
         * @return A Pair&lt;Command, String&gt; object containing the enum and description.
         */
        public static Pair<Command, String> parse(String input) {
            Scanner scanner = new Scanner(input);
            // if the input is empty, return the unknown keyword with an empty description.
            if (!scanner.hasNext()) {
                scanner.close();
                return new Pair<>(Command.UNKNOWN, "");
            }

            String keyword = scanner.next();
            Command first = Command.UNKNOWN;

            for (Command command: Command.values()) {
                if (keyword.equals(command.keyword)) {
                    first = command;
                    break;
                }
            }

            if (first.equals(Command.UNKNOWN) || !scanner.hasNextLine()) {
                scanner.close();
                return new Pair<>(first, "");
            }

            String second = scanner.nextLine().trim();
            scanner.close();
            return new Pair<>(first, second);
        }
    }
}
