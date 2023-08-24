import java.util.Scanner;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return Duchess.matchesRegex(s, "^bye$", true);
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
     * Checks whether the given string matches the given regex pattern.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @param caseInsensitive - whether the match should be performed ignoring the case.
     * @return                  whether the string matches the pattern.
     */
    private static boolean matchesRegex(String s, String patternString, boolean caseInsensitive) {
        return Duchess.parseRegex(s, patternString, caseInsensitive).matches();
    }

    /**
     * Checks whether the given string matches the given regex pattern. Defaults to case-sensitive matching.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @return                  whether the string matches the pattern.
     */
    private static boolean matchesRegex(String s, String patternString) {
        return Duchess.parseRegex(s, patternString).matches();
    }

    /**
     * Returns the regex groups that is parsed from the regex pattern.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @param caseInsensitive - whether the match should be performed ignoring the case.
     * @return                  the matcher containing the parsed regex groups.
     */
    private static Matcher parseRegex(String s, String patternString, boolean caseInsensitive) {
        Pattern pattern;

        if (caseInsensitive) {
            pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        }
        else {
            pattern = Pattern.compile(patternString);
        }

        Matcher matcher = pattern.matcher(s);
        matcher.find();
        return matcher;
    }

    /**
     * Returns the regex groups that is parsed from the regex pattern. By default, is case sensitive.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @return                  the matcher containing the parsed regex groups.
     */
    private static Matcher parseRegex(String s, String patternString) {
        return Duchess.parseRegex(s, patternString, false);
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
        return Duchess.matchesRegex(s, "^list$");
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

    /**
     * Returns true if the command is recognized as a "mark task" command.
     *
     * @param s - the command to check for "mark task" command.
     * @return    whether the command is a "mark task" command.
     */
    private static boolean isMarkTaskCommand(String s) {
        return Duchess.matchesRegex(s, "^mark[ ]*([0-9]+)$");
    }

    /**
     * Parses a mark task command, returning the index of the task to be marked.
     *
     * @param s - the command to parse for "mark task" command.
     * @return    an integer describing the index of the task to be marked.
     */
    private static int parseMarkTaskCommand(String s) {
        Matcher m = Duchess.parseRegex(s, "^mark[ ]*([0-9]+)$");
        return Integer.parseInt(m.group(1));
    }

    /**
     * Marks a task.
     *
     * @param i - the index of the task to be marked.
     */
    private static void markTask(int index) {
        Duchess.duchessPrint("Task has been marked!! (＾▽＾)");
        Duchess.storedTasks.get(index).changeStatus(TaskStatus.MARKED);
        duchessPrint(String.format("%d: %s", index, Duchess.storedTasks.get(index).toString()));
    }

    /**
     * Returns true if the command is recognized as an "unmark task" command.
     *
     * @param s - the command to check for "unmark task" command.
     */
    private static boolean isUnmarkTaskCommand(String s) {
        return Duchess.matchesRegex(s, "^unmark[ ]*([0-9]+)$");
    }

    /**
     * Parses an unmark task command, returning the index of the task to be unmarked.
     *
     * @param s - the command to parse for "unmark task" command.
     * @return    an integer describing the index of the task to be unmarked.
     */
    private static int parseUnmarkTaskCommand(String s) {
        Matcher m = Duchess.parseRegex(s, "^unmark[ ]*([0-9]+)$");

        return Integer.parseInt(m.group(1));
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void unmarkTask(int index) {
        Duchess.duchessPrint("Task has been unmarked!! (＾▽＾)");
        Duchess.storedTasks.get(index).changeStatus(TaskStatus.UNMARKED);
        duchessPrint(String.format("%d: %s", index, Duchess.storedTasks.get(index).toString()));
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

            // Check if this command is a mark task command.
             if (Duchess.isMarkTaskCommand(userInput)) {
                 int index = Duchess.parseMarkTaskCommand(userInput);
                 Duchess.markTask(index);
                 continue;
             }

            // Check if this command is a mark task command.
             if (Duchess.isUnmarkTaskCommand(userInput)) {
                 int index = Duchess.parseUnmarkTaskCommand(userInput);
                 Duchess.unmarkTask(index);
                 continue;
             }

            // Otherwise, store the command.
            Duchess.storeTasks(userInput);
        }

        sc.close();
        Duchess.printFarewell();
    }
}
