package task;


/**
 * Encapsulates a Task that is a to-do, lacking a date and only containing a name and completion status
 */
public class ToDo extends Task {

    /**
     * String to put at the front of string rep of this, represents the name,
     * T for [T]oDo
     */
    private static final String PREPEND = "[T]";

    /**
     * Constructor for a ToDo.
     * @param toDoName the name of this ToDo
     */
    public ToDo(String toDoName) {
        super(toDoName, false);
    }

    /**
     * The user-facing string representation of this ToDo, containing information about
     * what kind of task this is(a ToDo), its name and whether it is done
     * @return the user-facing string representation of this ToDo.
     */
    @Override
    public String toPrintString() {

        return ToDo.PREPEND + super.toPrintString();

    }
}
