package duke.exceptions;

public class InvalidFormat extends DukeException{
    /**
     * Constructor for the InvalidFormat class.
     */
    public InvalidFormat() {
        super("-------------------------------\n"
                +  "OOPS!!! That is an invalid format." +
                " Please follow the format yyyy-mm-dd!\n"
                +  "-------------------------------\n");
    }
}
