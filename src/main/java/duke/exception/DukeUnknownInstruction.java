package duke.exception;

/**
 * AN exception thrown when the instruction is not understandable
 * for the Parser.
 */
public class DukeUnknownInstruction extends DukeException {

    /**
     * Constructor for the exception.
     */
    public DukeUnknownInstruction() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\n");
    }
}
