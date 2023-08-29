package duke;
/**
 * The DukeException Class is used when user does not follow any input format.
 */
public class DukeException extends Exception{
    public DukeException() {
        super();
    }

    /**
     * This method gives the string representation of DukeException
     *
     * @return The String representation of an DukeException
     */
    @Override
    public String toString(){
        return "☹ This is not a valid input";
    }


}
