package duke;
/**
 * Encapsulates an error thrown by the Parser.
 */
public class ParserException extends DukeException {
    public ParserException(String message) {
        super(message);
    }
    public ParserException() {
        super();
    }
}
