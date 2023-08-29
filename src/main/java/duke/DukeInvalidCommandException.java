package duke;

/**
 * Represents the different commands that the user can input.
 */
public class DukeInvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " I'm sorry, but I don't know what that means :-(";
    }
}
