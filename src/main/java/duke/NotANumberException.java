package duke;
public class NotANumberException extends DukeException {
    public static String line = "\t____________________________________________________________\n";

    public NotANumberException() {
        System.out.println(line + " \tThat command must be followed by a number" + "\n" +line);
    }
}
