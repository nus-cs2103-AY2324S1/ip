package duke;
public class EmptyDescriptionException extends DukeException {
    public static String line = "\t____________________________________________________________\n";
    public EmptyDescriptionException(String task) {
        //System.out.println( line + "\t☹ OOPS!!! The description of a " + task + " cannot be empty." + "\n" + line);
    }

}
