package duke;

/**
 * Exception when an illegal argument is found.
 */
class WrongInput extends DukeException {
    /**
     * Constructor for the WrongInput class.
     */
    public WrongInput() {
        super("-------------------------------\n"
                +  "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                +  "-------------------------------\n");
    }
}
