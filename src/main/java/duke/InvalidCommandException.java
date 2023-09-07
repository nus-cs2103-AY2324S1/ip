package duke;

/**
 * Invalid command exception, thrown if the user gives an invalid command
 */
public class InvalidCommandException extends DukeException {
    private static String line = "\t____________________________________________________________\n";
    public InvalidCommandException() {
        System.out.println(line + " \t☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" + line);
    }
}
