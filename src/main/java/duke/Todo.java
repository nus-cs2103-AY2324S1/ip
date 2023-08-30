package duke;
/**
 * Encapsulates a t0d0 task without time/date dependencies.
 */
public class Todo extends Item {
    public Todo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        String rtnVal = "";
        if (super.isCompleted()) {
            rtnVal += "[T][X] ";
        } else {
            rtnVal += "[T][ ] ";
        }
        return rtnVal + super.getName();
    }
}
