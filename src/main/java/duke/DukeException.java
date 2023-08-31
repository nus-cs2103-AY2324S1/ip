package duke;

public class DukeException extends Exception{
    public static class DukeDateTimeException extends DukeException {
        public DukeDateTimeException(String s) {
            super("Err: DateTime given does not fit the format. Expected: yyyy MM DD HHmm. Got "+ s);
        }
    }
    public DukeException(String s) {
        super(s);
    }
}
