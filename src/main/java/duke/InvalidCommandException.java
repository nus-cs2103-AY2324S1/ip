package duke;

public class InvalidCommandException extends DukeException {
    public static String line = "\t____________________________________________________________\n";
    public InvalidCommandException() {
        System.out.println(line + " \t☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" +line);
    }
}
