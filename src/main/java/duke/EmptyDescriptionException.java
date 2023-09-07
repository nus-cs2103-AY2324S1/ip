package duke;

/**
 * Thrown if an empty description is given.
 */
public class EmptyDescriptionException extends DukeException {
    private static String line = "\t____________________________________________________________\n";
    public EmptyDescriptionException(String task) {
        //System.out.println( line + "\t☹ OOPS!!! The description of a " + task + " cannot be empty." + "\n" + line);
    }

}
