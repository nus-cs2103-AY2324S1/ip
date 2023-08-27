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
        System.out.println(String.format("[D]: %s", s));
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
     * @return    whether the command is an exit command.
     */
    private static boolean isExitCommand(String s) {
        return Duchess.matchesRegex(s, "^bye$", true) || Duchess.matchesRegex(s, "^exit$", true);
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
        return Duchess.parseRegex(s, patternString, caseInsensitive).find(0);
    }

    /**
     * Checks whether the given string matches the given regex pattern. Defaults to case-sensitive matching.
     *
     * @param s               - the string to check if it matches the pattern.
     * @param patternString   - the pattern to match against.
     * @return                  whether the string matches the pattern.
     */
    private static boolean matchesRegex(String s, String patternString) {
        return Duchess.parseRegex(s, patternString).find(0);
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
        matcher.find(0);
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
     * Returns true if the command is recognized as a "list text" command.
     *
     * @param s - the command to check for "list text" command.
     * @return    whether the command is a list tasks command.
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
            Duchess.duchessPrint(String.format("%d: %s", i + 1, Duchess.storedTasks.get(i).toString()));
        }
    }

    /**
     * Returns true if the command is recognized as a "mark task" command.
     *
     * @param s - the command to check for "mark task" command.
     * @return    whether the command is a mark task command.
     */
    private static boolean isMarkTaskCommand(String s) {
        return Duchess.matchesRegex(s, "^mark");
    }

    /**
     * Parses a mark task command, returning the index of the task to be marked.
     *
     * @param s - the command to parse for "mark task" command.
     * @return    an integer describing the index of the task to be marked.
     */
    private static int parseMarkTaskCommand(String s) throws DuchessException {
        Matcher m = Duchess.parseRegex(s, "^mark( [0-9]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, I don't know which task to mark... ;-;");
        }

        return Integer.parseInt(m.group(1).trim());
    }

    /**
     * Marks a task.
     *
     * @param i - the index of the task to be marked.
     */
    private static void markTask(int index) {
        // Within bounds
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Duchess.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Duchess.storedTasks.get(index).changeStatus(TaskStatus.MARKED);
        Duchess.duchessPrint("Task has been marked!! (＾▽＾)");
        duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.get(index).toString()));
    }

    /**
     * Returns true if the command is recognized as an "unmark task" command.
     *
     * @param s - the command to check for "unmark task" command.
     * @return    whether the command is an unmark task command.
     */
    private static boolean isUnmarkTaskCommand(String s) {
        return Duchess.matchesRegex(s, "^unmark");
    }

    /**
     * Parses an unmark task command, returning the index of the task to be unmarked.
     *
     * @param s - the command to parse for "unmark task" command.
     * @return    an integer describing the index of the task to be unmarked.
     */
    private static int parseUnmarkTaskCommand(String s) throws DuchessException {
        Matcher m = Duchess.parseRegex(s, "^unmark( [0-9]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, I don't know which task to unmark... ;-;");
        }

        return Integer.parseInt(m.group(1).trim());
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void unmarkTask(int index) {
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Duchess.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Duchess.storedTasks.get(index).changeStatus(TaskStatus.UNMARKED);
        Duchess.duchessPrint("Task has been unmarked!! (＾▽＾)");
        duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.get(index).toString()));
    }

    /**
     * Returns true if the command is recognized as an "delete task" command.
     *
     *
     * @param s - the command to check for "delete task" command.
     * @return    whether the command is an delete task command.
     */
    private static boolean isDeleteTaskCommand(String s) {
        return Duchess.matchesRegex(s, "^delete");
    }

    /**
     * Parses a delete task command, returning the index of the task to be deleted.
     *
     * @param s - the command to parse for "delete task" command.
     * @return    an integer describing the index of the task to be deleted.
     */
    private static int parseDeleteTaskCommand(String s) throws DuchessException {
        Matcher m = Duchess.parseRegex(s, "^delete( [0-9]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, I don't know which task to delete... ;-;");
        }

        return Integer.parseInt(m.group(1).trim());
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void deleteTask(int index) {
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Duchess.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Task task = Duchess.storedTasks.remove(index);
        Duchess.duchessPrint("Deleted successfully!! d(*⌒▽⌒*)b");
        duchessPrint(String.format("%d: %s", index + 1, task.toString()));
        Duchess.duchessPrint("");
        Duchess.duchessPrint(String.format("Now you have %d task(s)!! ヽ(´▽`)/", Duchess.storedTasks.size()));
    }

    /**
     * Returns true if the command is recognized as a "todo" command.
     *
     * @param s - the command to check for "todo" command.
     * @return    whether the command is recognized as a ToDo command.
     */
    private static boolean isToDoCommand(String s) {
        return Duchess.matchesRegex(s, "^todo");
    }

    /**
     * Parses a ToDo command, returning a ToDo task that was parsed from the command.
     *
     * @param s - the command to parse for "todo" command.
     * @return    the ToDo task.
     */
    private static ToDo parseToDoCommand(String s) throws DuchessException {
        Matcher m = Duchess.parseRegex(s, "^todo( [A-Za-z0-9 ]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, todo names cannot be empty... ;-;");
        }

        return new ToDo(m.group(1).trim());
    }

    /**
     * Returns true if the command is recognized as an "deadline" command.
     *
     * @param s - the command to check for "deadline" command.
     * @return    whether the command is recognized as a Deadline command.
     */
    private static boolean isDeadlineCommand(String s) {
        // This mmatches the start of a string, then the word "deadline", then anything afterwards.
        return Duchess.parseRegex(s, "^deadline").find(0);
    }

    /**
     * Parses a deadline command, returning the deadline object parsed from the command.
     *
     * @param s - the command to parse for "deadline" command.
     * @return    the Deadline task.
     */
    private static Deadline parseDeadlineCommand(String s) throws DuchessException {
        Matcher m = Duchess.parseRegex(s, "^deadline( [A-Za-z0-9 ]+)?( /by ([A-Za-z0-9 ]+)?)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, deadline names cannot be empty... ;-;");
        }
        if (m.group(2) == null || m.group(3) == null) {
            throw new DuchessException("(´；ω；`) Sorry, deadlines must have a deadline... ;-;");
        }

        return new Deadline(m.group(1).trim(), m.group(3).trim());
    }

    /**
     * Returns true if the command is recognized as an "event" command.
     *
     * @param s - the command to check for "event" command.
     * @return    whether the command is recognized as an "event" command.
     */
    private static boolean isEventCommand(String s) {
        return Duchess.matchesRegex(s, "^event");
    }

    /**
     * Parses a event command, returning the event object parsed from the command.
     *
     * @param s - the command to parse for "event" command.
     * @return    the Event task.
     */
    private static Event parseEventCommand(String s) throws DuchessException {
        Matcher m = Duchess.parseRegex(s, "^event( [A-Za-z0-9 ]+)?( /from( [A-Za-z0-9 ]+)?)?( /to( [A-Za-z0-9 ]+)?)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, event names cannot be empty... ;-;");
        }
        if (m.group(2) == null || m.group(3) == null) {
            throw new DuchessException("(´；ω；`) Sorry, events must have a start time... ;-;");
        }
        if (m.group(4) == null || m.group(5) == null) {
            throw new DuchessException("(´；ω；`) Sorry, events must have an end time... ;-;");
        }

        return new Event(m.group(1).trim(), m.group(3).trim(), m.group(5).trim());
    }

    /**
     * Adds a new Event task with the specified title.
     *
     * @param event - the Event object to be added
     */
    private static void addTask(Task task) {
        Duchess.storedTasks.add(task);

        Duchess.duchessPrint("Added successfully!! d(*⌒▽⌒*)b");
        Duchess.duchessPrint(String.format("%d: %s", Duchess.storedTasks.indexOf(task) + 1, task.toString()));
        Duchess.duchessPrint("");
        Duchess.duchessPrint(String.format("Now you have %d task(s)!! ヽ(´▽`)/", Duchess.storedTasks.size()));
    }



    public static void main(String[] args) {
        Duchess.printGreeting();

        // Create the save file, if it does not exist.
        Data.createSaveFile();

        // Load tasks from the save file.
        Data.loadTasksFromFile(Duchess.storedTasks);

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while (!Duchess.isExitCommand(userInput)) {
            System.out.println();
            System.out.print("$>  ");
            userInput = sc.nextLine().trim();
            System.out.println();

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
                try {
                    int index = Duchess.parseMarkTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.markTask(index);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("mark [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Duchess.isUnmarkTaskCommand(userInput)) {
                try {
                    int index = Duchess.parseUnmarkTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.unmarkTask(index);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("unmark [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Duchess.isDeleteTaskCommand(userInput)) {
                try {
                    int index = Duchess.parseDeleteTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.deleteTask(index);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("delete [task number]");
                }
                continue;
            }

            // Check if this command is a ToDo command.
            if (Duchess.isToDoCommand(userInput)) {
                try {
                    ToDo todo = Duchess.parseToDoCommand(userInput);
                    Duchess.addTask(todo);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("todo [name]");
                }
                continue;
            }

            // Check if this command is a Deadline command.
            if (Duchess.isDeadlineCommand(userInput)) {
                try {
                    Deadline deadline = Duchess.parseDeadlineCommand(userInput);
                    Duchess.addTask(deadline);
                }
                catch(DuchessException e) {                    
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("deadline [name] /by [date]");
                }
                continue;
            }

            // Check if this command is a Event command.
            if (Duchess.isEventCommand(userInput)) {
                try {
                    Event event = Duchess.parseEventCommand(userInput);
                    Duchess.addTask(event);
                }
                catch(DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("event [name] /from [time] /to [time]");
                }
                continue;
            }

            Duchess.duchessPrint("(´；ω；`) Oopsies... I don't know what that means ;-;");
        }

        sc.close();
        
        // Save the tasks.
        Data.saveTasksToFile(Duchess.storedTasks);

        Duchess.printFarewell();
    }
}
