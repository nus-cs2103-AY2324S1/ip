import java.util.Scanner;
import java.util.ArrayList;

public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * An ArrayList to store text in.
     */
    private static final ArrayList<Task> storedTasks = new ArrayList<>();

    /**
     * Prints text, but with a consistent formatting in Duchess style.
     */
    private static void duchessPrint(String s) {
        System.out.println(String.format("$>  %s", s));
    }

    /**
     * Prints greeting to the user and returns.
     */
    private static void printGreeting() {
        Duchess.duchessPrint("(^O^)／ Heya~");
        Duchess.duchessPrint("I am ~~Duchess~~!!");
        Duchess.duchessPrint("What can I do for you!! o_O");
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
        Duchess.duchessPrint("Bye bye!! Hope to see you again (＾▽＾)");
    }

    /**
     * Outputs the same string parameter that was passed in.
     *
     * @param s - the string to be echoed.
     */
    private static void echo(String s) {
        Duchess.duchessPrint(s);
    }

    /**
     * Stores the provided String into an internal ArrayList.
     *
     * @param s - the string to be stored.
     */
    private static void storeTasks(String s) {
        Task t = new Task(s);
        Duchess.storedTasks.add(t);

        Duchess.duchessPrint("Added successfully!! d(*⌒▽⌒*)b");
        Duchess.duchessPrint(t.toString());
    }

    /**
     * Returns true if the command is recognized as a "list text" command.
     *
     * @param s - the command to check for "list text" command.
     */
    private static boolean isListTasksCommand(String s) {
        return s.toLowerCase().equals("list");
    }

    /**
     * Prints all stored text.
     *
     * @param s - the string to be stored.
     */
    private static void listTasks() {
        Duchess.duchessPrint("Here are the things you said!! ヽ(^o^)丿");
        for (int i = 0; i < Duchess.storedTasks.size(); i++) {
            Duchess.duchessPrint(String.format("%d: %s", i, Duchess.storedTasks.get(i).toString()));
        }
    }

    public static void main(String[] args) {
        Duchess.printGreeting();

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while (!Duchess.isExitCommand(userInput)) {
            userInput = sc.nextLine();

            // Check if this command is an exit.
            if (Duchess.isExitCommand(userInput)) {
                break;
            }

            // Check if this command is a list.
            if (Duchess.isListTasksCommand(userInput)) {
                Duchess.listTasks();
                continue;
            }

            // Otherwise, store the command.
            Duchess.storeTasks(userInput);
        }

        sc.close();
        Duchess.printFarewell();
    }
}
