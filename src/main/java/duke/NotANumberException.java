package duke;

/**
 * Thrown if a number is not given.
 */
public class NotANumberException extends DukeException {
    private static String line = "\t____________________________________________________________\n";

    public NotANumberException() {
        System.out.println(line + " \tThat command must be followed by a number" + "\n" + line);
    }
}
