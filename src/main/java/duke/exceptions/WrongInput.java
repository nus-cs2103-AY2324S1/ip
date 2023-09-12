package duke.exceptions;

/**
 * Exception when an illegal argument is found.
 */
public class WrongInput extends DukeException {
    /**
     * Constructor for the WrongInput class.
     */
    public WrongInput() {
        super("-------------------------------\n"
                +  "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                +  "-------------------------------\n");
    }
}
